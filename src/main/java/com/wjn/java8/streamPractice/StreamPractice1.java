package com.wjn.java8.streamPractice;

import com.wjn.java8.stream.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamPractice1 {
    /**
     * 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     * 给定【1,2,3,4,5】，返回【1,4,9,16,25】
     */
    @Test
    public void test1() {
        Integer[] nums = new Integer[]{1,2,3,4,5};
        Arrays.stream(nums)
                .map(x -> x*x)
                .forEach(System.out::println);
    }

    /**
     * 怎样用map和reduce方法数一数流中有多少个Employee呢？
     */

    List<Employee> emps = Arrays.asList(
            new com.wjn.java8.stream.Employee(102, "李四", 79, 6666.66, com.wjn.java8.stream.Employee.Status.BUSY),
            new com.wjn.java8.stream.Employee(101, "张三", 18, 9999.99, com.wjn.java8.stream.Employee.Status.FREE),
            new com.wjn.java8.stream.Employee(103, "王五", 28, 3333.33, com.wjn.java8.stream.Employee.Status.VOCATION),
            new com.wjn.java8.stream.Employee(104, "赵六", 8, 7777.77, com.wjn.java8.stream.Employee.Status.BUSY),
            new com.wjn.java8.stream.Employee(104, "赵六", 8, 7777.77, com.wjn.java8.stream.Employee.Status.FREE),
            new com.wjn.java8.stream.Employee(104, "赵六", 8, 7777.77, com.wjn.java8.stream.Employee.Status.FREE),
            new com.wjn.java8.stream.Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );
    @Test
    public void test2() {
        Optional<Integer> sum = emps.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(sum.get());
    }


}
