package Zadacha2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StringStream {
    public static void main(String[] args) {
        List<String> processed= new ArrayList<>();
        Stream<String> stringStream = Stream.of("aBc", "d", "ef", "123456");
        stringStream.sorted().map(String::toUpperCase).forEach(processed::add);
        processed.forEach(System.out::println);
    }
}
