package com.example.javademo.java8;

import com.example.javademo.JavaDemoApplicationTests;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/04/07
 **/
public class TestStream extends JavaDemoApplicationTests {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("c:\\SUService.log"));
        int longest = br.lines().
                mapToInt(String::length).
                max().
                getAsInt();
        br.close();
        System.out.println(longest);

    }

    /**
     * 获取范围流
     * IntStream.range(start, end)
     */
    @Test
    public void testRange(){
        // 不包含end
        IntStream.range(1, 3).forEach(System.out::println);
        System.out.println("----");
        // 包含end
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
    }

    /**
     * reduce
     * 主要作用是把 Stream 元素组合起来。它提供一个起始值（种子），然后依照运算规则二元运算符（BinaryOperator），和前面 Stream 的第一个、第二个、第 n 个元素组合。
     * 从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce
     */
    @Test
    public void testReduce(){
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(1, Integer::sum);
        // 等同于上面
        //int sumValue = Stream.of(1, 2, 3, 4).reduce(1, (a,b) -> a + b);
        System.out.println("求和-有初始值：" + sumValue);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println("求和：" + sumValue);
        // 求最小值
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::max);
        System.out.println("最小值：" + minValue);
    }


    /**
     * 中间操作
     * map 一对多
     * flatMap
     */
    @Test
    public void testMapOneToMany(){
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        outputStream.filter(integer -> integer % 2 == 0).forEach(System.out::println);
    }

    /**
     *  peek
     *  对每个元素执行操作并返回一个新的 Stream
     *  不会改变stream中的元素
     */
    @Test
    public void testPeek(){
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    /**
     * ForEach 并行模式下无法保证元素原始次序
     * ForEachOrder 并行模式下按照原始次序输出
     */
    @Test
    public void testForEach(){
        Integer[] array = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        Arrays.asList(array)
                .parallelStream()
                .forEach(System.out::println);
        System.out.println("-------------------");
        Arrays.asList(array)
                .parallelStream()
                .forEachOrdered(System.out::println);
    }


    /**
     * findFirst
     */
    @Test
    public void testFindFirst(){
        Integer[] array = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        Optional<Integer> optional = Arrays.asList(array)
                .stream().findFirst();
        optional.ifPresent(System.out::println);
    }

    /**
     * Optional<T>
     */
    @Test
    public void testOptional(){
        String text = null;
//        // Java 8 如果不为null，则执行后面的操作，否则，直接退出；
        Optional.ofNullable(text).ifPresent(System.out::println);
//        // Pre-Java 8
//        if (text != null) {
//            System.out.println(text);
//        }
        Integer number = 0;
        // Java 8
//        number = Optional.ofNullable(text).map(String::length).orElse(-1);
        // 函数 返回值
//        number = Optional.ofNullable(text).map(String::length).orElseGet(() -> 8+5);
        // 函数 抛出异常
        number = Optional.ofNullable(text).map(String::length).orElseThrow(() -> new RuntimeException("不能为null"));
        // Pre-Java 8
//        number = (text != null) ? text.length() : -1;;
        System.out.println(number);
    }


    /**
     * 自己生成流
     */
    @Test
    public void testGenerateStream(){
        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(10).forEach(System.out::println);
        //Another way
//        IntStream.generate(() -> (int) (System.nanoTime() % 100)).
//                limit(10).forEach(System.out::println);
    }

    /**
     * iterate
     * iterate 跟 reduce 操作很像，接受一个种子值，和一个 一元操作符UnaryOperator（例如 f）。然后种子值成为 Stream 的第一个元素，f(seed) 为第二个，f(f(seed)) 第三个，以此类推
     */
    @Test
    public void testIterate(){
        Stream.iterate(0, n -> n + 3).limit(10). forEach(x -> System.out.print(x + " "));
    }

}
