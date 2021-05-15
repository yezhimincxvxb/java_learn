package com.yzm.base.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {

    private static void demo01() {
        Stream<String> stringStream = Stream.of("1", "2", "3", "4");
        stringStream.forEach(System.out::print);
        System.out.println();

        String[] arr = {"a", "b", "c"};
        Stream<String> arrStream = Arrays.stream(arr);
        arrStream.forEach(System.out::print);
        System.out.println();

        List<String> list = Arrays.asList(arr);
        list.stream().map(String::toUpperCase).forEach(System.out::print);
        System.out.println();

        IntStream.range(18, 100).limit(10).forEach(System.out::print);
    }

    private static void demo02() {
        // limit：限制
        // skip：跳过
        IntStream.range(0, 100).limit(10).skip(5).forEach(System.out::print);
        System.out.println();

        // count：流中元素个数
        System.out.println(IntStream.range(0, 100).limit(10).count());
        System.out.println(IntStream.range(0, 100).limit(10).min().getAsInt());
        System.out.println(IntStream.range(0, 100).limit(10).max().getAsInt());
        //
    }

    public static void main(String[] args) {
//        demo01();
        demo02();
    }
}
