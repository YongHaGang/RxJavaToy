import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

public class StreamPractice {
    class MyClass {
        private int price;
        private String category;

        public MyClass(int price, String category) {
            this.price = price;
            this.category = category;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        @Override
        public String toString() {
            return "MyClass{" +
                    "price=" + price +
                    ", category='" + category + '\'' +
                    '}';
        }
    }

    static class MySingletonClass {

        private static class SingletonHolder {
            private static final MySingletonClass instance = new MySingletonClass();
        }

        public static MySingletonClass getInstance() {
            return SingletonHolder.instance;
        }
    }

    @Test
    public void streamTest() {
        List<String> list = Arrays.asList("1232", "3232", "4343", "5555555", "545454");

        double average = list.stream()
                .mapToInt(Integer::parseInt)
                .mapToDouble(value -> value)
                .average()
                .orElse(0);
        System.out.println(average);

        Map<Character, List<String>> set = list.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0)));
        System.out.println(set.keySet());
        System.out.println(set.values());

        List<MyClass> myClassList = Arrays.asList(new MyClass(10, "CAR"),
                new MyClass(200, "House"),
                new MyClass(5, "Computer"));
        myClassList.stream()
                .filter(myClass -> "Car".equalsIgnoreCase(myClass.getCategory()))
                .forEach(myClass -> myClass.setPrice((int)(myClass.getPrice() * 0.9)));
        System.out.println(myClassList);
    }

    @Test
    public void downloadFileTest() {

    }
}
