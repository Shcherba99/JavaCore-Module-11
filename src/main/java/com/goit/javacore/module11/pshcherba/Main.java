package com.goit.javacore.module11.pshcherba;

import javax.xml.transform.stream.StreamSource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {
        List<String> names = List.of("Ivan", "Alex", "Peter", "Anton", "Artem", "Anna");
        String[] array = {"1, 2, 0", "4, 5"};
        long a = 25214903917L;
        long c = 11L;
        long m = (long) Math.pow(2, 48);
        Stream<Integer> first = Stream.of(1, 2, 3);
        Stream<Integer> second = Stream.of(5, 6);

        System.out.println(getOddIndexNames(names));
        System.out.println(getUpperNames(names));
        printSortNumbers(array);
        System.out.println(generateRandomStream(a, c, m).limit(10).toList());
        System.out.println(zip(first, second).toList());
    }


    //Task 1
    public static String getOddIndexNames(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(i -> i + ". " + names.get(i))
                .collect(Collectors.joining(", "));
    }


    //Task 2
    public static List<String> getUpperNames(List<String> names) {
        return names.stream()
                .map(String::toUpperCase).sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(ArrayList::new));
    }


    //Task 3
    public static void printSortNumbers(String[] array) {
        String result = Arrays.stream(array)
                .flatMap(str -> Arrays.stream(str.split(",\\s*")))
                .map(Integer::parseInt)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }


    //Task 4
    public static Stream<Long> generateRandomStream(long a, long c, long m) {
        return Stream.iterate(1L, x -> (a * x + c) % m);
    }

    //Task 5
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> secondIterator = second.iterator();
        return first.flatMap(e -> secondIterator.hasNext()
                ? Stream.of(e, secondIterator.next())
                : Stream.empty());
    }
}
