package Zadacha3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTask {
    public static void main(String[] args) {
        IntStream intStream = IntStream.range(1,101);
        String str = intStream.mapToObj(String::valueOf).reduce((s1,s2) -> s1 + "#" + s2).get();
        System.out.println(str);

        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < 20; i++){
            randomNumbers.add(random.nextInt(31));
        }
        Supplier<IntStream> streamSupplier = () -> randomNumbers.stream().mapToInt(Integer::valueOf);
        streamSupplier.get().forEach((number) -> System.out.print(number + " "));

        List<Integer> divideBy5 = streamSupplier.get().filter((number) -> number % 5 == 0).boxed().collect(Collectors.toList());
        boolean lessThan15 = streamSupplier.get().allMatch((number) -> number < 15);
        int numberCountGreaterThanAverage = (int) streamSupplier.get().average().stream().count();
        boolean greaterThan5 = numberCountGreaterThanAverage > 5;

        System.out.printf("\nNumbers that divide by 5: %s\n", divideBy5.toString());
        String message = lessThan15 ? "All numbers are less than 15" : "Not all number are less than 15";
        System.out.println(message);
        String message2 = greaterThan5 ? "More than 5 numbers are grater than the average" : "Less than 5 numbers are grater than the average";
        System.out.println(message2);
    }
}
