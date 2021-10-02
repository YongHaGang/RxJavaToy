import io.reactivex.*;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.concurrent.TimeUnit;
import static org.hamcrest.MatcherAssert.assertThat;

public class RetrofitPractice {
    @Test
    public void retrofitTest() {
//        Single<Response<User>> result = UserRepository.getInstance().getUser("123");
//        result.subscribe(
//                user -> System.out.println(user.headers() + " " + user.raw()),
//                throwable -> System.out.print(throwable)
//        );

        StringBuilder stringBuilder = new StringBuilder();
        Single<Response<MyData>> result = UserRepository.getInstance().getMyData("123", "my data");
        result.subscribe(
                myDataResponse -> System.out.println(myDataResponse.headers().toString() + myDataResponse.body()),
                throwable -> System.out.print(throwable)
        );
//        result.test()
//                .assertValue(userResponse -> userResponse != null)
//                .assertNoErrors();
//        UserRepository.getInstance().getUserss(null).onBackpressureLatest().subscribe();
//
//        Flowable<Long> flowable = Flowable.interval(1, TimeUnit.MILLISECONDS);
//        flowable.doOnNext(aLong -> System.out.println("doOnNext " + aLong))
//                .onBackpressureDrop(aLong -> System.out.println("drop" + aLong))
//                //.onBackpressureBuffer(2, () -> System.out.println("overflow!!"), BackpressureOverflowStrategy.DROP_OLDEST)
//                .subscribe(user -> {Thread.sleep(1000); System.out.println("#1 " + user);});
//
//        flowable.doOnNext(aLong -> System.out.println("doOnNext " + aLong))
//                .onBackpressureBuffer(2, () -> System.out.println("overflow!!"), BackpressureOverflowStrategy.DROP_OLDEST)
//                .subscribe(user -> {/*System.out.println("#2 " + user);*/});

//        Observable.range(1, 1_000_000).toFlowable(BackpressureStrategy.DROP)
//                .subscribe(integer -> System.out.println(integer));
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
