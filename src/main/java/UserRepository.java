import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import okhttp3.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.transform.Transformer;
import java.io.*;
import java.security.cert.Certificate;
import java.util.concurrent.TimeUnit;

public class UserRepository {

    private static final String url = "http://localhost:8080";

    private static class SingletonHolder {
        public final static UserRepository instance = new UserRepository();
    }

    public static UserRepository getInstance() {
        return SingletonHolder.instance;
    }

    private UserService userService;

    private UserRepository() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(5, 3, TimeUnit.MINUTES))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                //.sslSocketFactory()
                .build();
        userService = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(UserService.class);
    }

    public Single<Response<User>> getUser(String id) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "requestBody");
        return userService.getUserById(id, body)
                .compose(applySingleScheduler());
    }


    public Single<Response<MyData>> getMyData(String id, String bodyText) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), bodyText);
        return userService.getMyDataById(id, body)
                .compose(applySingleScheduler());
    }

    public Observable<User> getUsers(String[] id) {
        return userService.getUsersById(id)
                .compose(applyObservableScheduler());
    }

    public Flowable<User> getUserss(String[] id) {
        return userService.getUsersByIds(id)
                .compose(applyFlowableScheduler());
    }

    public Subject<DownloadInfo> downloadFile(String file, File dest) {
        BehaviorSubject<DownloadInfo> subject = BehaviorSubject.create();
        subject.defaultIfEmpty(new DownloadInfo(0, dest.getName()));
        userService.downloadFile(file)
                .subscribeOn(Schedulers.io())
                .retry(1)
                .subscribe(
                        response -> writeFile(response, dest, subject),
                        throwable -> subject.onError(throwable)
                );
        return subject;
    }

    private boolean writeFile(okhttp3.Response response, File dest, Subject<DownloadInfo> subject) {
        try (InputStream is = response.body().byteStream(); OutputStream os = new BufferedOutputStream(new FileOutputStream(dest))) {
            byte[] buffer = new byte[4096];
            long length = response.body().contentLength();
            for (int current = 0 ; (length = is.read(buffer)) > 0;) {
                os.write(buffer, (int)current, (int)length);
                current += length;
                subject.onNext(new DownloadInfo((int)(100 * current / length), dest.getName()));
            }
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private <T> SingleTransformer<T,T> applySingleScheduler() {
        return (upstream) -> upstream.observeOn(Schedulers.io());
    }


    private <T> ObservableTransformer<T,T> applyObservableScheduler() {
        return (upstream) -> upstream
                .retry(3)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.trampoline());
    }


    private <T> FlowableTransformer<T,T> applyFlowableScheduler() {
        return (upstream) -> upstream
                .retry(3)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.trampoline());
    }
}
