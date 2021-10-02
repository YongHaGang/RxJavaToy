import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CombineLatestTest {

    @Test
    void combine() {
//        BehaviorSubject<Integer> observable1 = BehaviorSubject.createDefault(new Integer(999));
//        Observable<Integer> observable2 = Subject.just(new Integer(10), new Integer(20), new Integer(30));
//
//        observable1.subscribe(integer -> System.out.println("asyncSubject emit" + integer));
//        observable1.defaultIfEmpty(new Integer(1));
//
//        Observable<Integer> observable3 = Observable.zip(observable2,
//                Observable.interval(1500, TimeUnit.MILLISECONDS), (integer, aLong) -> integer);
//        observable3.subscribe(integer -> System.out.println("asyncSubject emit" + integer));
//        Observable.combineLatest(observable1
//                , observable3
//                , (integer, integer2) -> {
//            return integer + integer2;
//        }).subscribe(integer -> System.out.println(integer + " time=" + System.currentTimeMillis()));
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        observable1.onNext(new Integer(3));
//        observable1.onNext(new Integer(4));
//        observable1.subscribe(integer -> System.out.println("test" + integer));
//        observable1.onNext(new Integer(7));
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Stream<String> stream = Arrays.asList("fdfd", "abcd", null).stream();

        stream.filter(s -> s != null)
                .flatMap(s -> s.chars().mapToObj(value -> (char) value))
                .forEach(o -> System.out.println(o));

        Optional<String> optional = Optional.ofNullable(null);

        optional.map(s -> {
            System.out.println(s);
            return s;
        });
    }
}