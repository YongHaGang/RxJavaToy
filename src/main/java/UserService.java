import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import jdk.nashorn.internal.ir.RuntimeNode;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.*;

public interface UserService {
    @Streaming
    @POST("data/{id}")
    public Single<Response<User>> getUserById(@Path("id")String id, @Body RequestBody body);

    //@Streaming
    @POST("data/{id}")
    public Single<Response<MyData>> getMyDataById(@Path("id")String id, @Body RequestBody body);

    @GET("data/{id}")
    public Observable<User> getUsersById(@Path("id")String[] id);

    @GET("data/{id}")
    public Flowable<User> getUsersByIds(@Path("id")String[] id);

    @Streaming
    @GET("file/{name}")
    public Single<okhttp3.Response> downloadFile(@Path("file")String file);

    Download.Build
}
