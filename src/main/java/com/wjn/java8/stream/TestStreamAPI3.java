package com.wjn.java8.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 */
public class TestStreamAPI3 {
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 79, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );

    /**
     * 查找与匹配
     */
    @Test
    public void test1() {
        // allMatch():emps集合中是否所有元素的枚举值都是Status.BUSY
        boolean b = emps.stream()
                .allMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);

        // anyMatch():emps集合中是否所有元素的枚举值至少有一个Status.BUSY
        boolean b1 = emps.stream()
                .anyMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        // noneMatch():检查是否没有匹配所有元素
        boolean b2 = emps.stream()
                .noneMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);

        // findFirst():返回第一个元素
        Optional<Employee> op = emps.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(op.get());

        // parallelStream() 表示并行流，在这里表示多个线程同时找到空闲的职员。 findAny():返回当前流中的任意元素
        Optional<Employee> op2 = emps.parallelStream()
                .filter(e -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(op2.get());
    }

    /**
     * 查找与匹配
     */
    @Test
    public void test2() {
        // count():返回流中元素总数
        long count = emps.stream().count();
        System.out.println(count);

        // max(Comparator c):返回流中最大值
        Optional<Employee> max = emps.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());

        // min(Comparator c)：返回流中最小值
        Optional<Double> min = emps.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(min.get());
    }

    /**
     * 归约
     */
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println("----------------------------------");

        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }

    /**
     * 收集  collect(Collector c):将流转换为其他形式。接收一个 Collector接口的
     * 实现，用于给Stream中元素做汇总的方法
     */
    @Test
    public void test4() {
        // 将所有的名字收集到一个List集合中
        List<String> list = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("----------------------------------");

        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        set.forEach(System.out::println);

        System.out.println("----------------------------------");

        HashSet<String> hs = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));

        hs.forEach(System.out::println);
    }

    @Test
    public void test5(){
        Optional<Double> max = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare));

        System.out.println(max.get());

        Optional<Employee> op = emps.stream()
                .collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));

        System.out.println(op.get());

        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));

        System.out.println(sum);

        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println(avg);

        Long count = emps.stream()
                .collect(Collectors.counting());

        System.out.println(count);

        System.out.println("--------------------------------------------");

        DoubleSummaryStatistics dss = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(dss.getMax());
    }
    //分组
    @Test
    public void test6(){
        Map<Employee.Status, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        System.out.println(map);
    }

    //多级分组
    @Test
    public void test7(){
        Map<Employee.Status, Map<String, List<Employee>>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if(e.getAge() >= 60)
                        return "老年";
                    else if(e.getAge() >= 35)
                        return "中年";
                    else
                        return "成年";
                })));

        System.out.println(map);
    }

    //分区
    @Test
    public void test8(){
        Map<Boolean, List<Employee>> map = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() >= 5000));

        System.out.println(map);
    }

    //
    @Test
    public void test9(){
        String str = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining("," , "----", "----"));

        System.out.println(str);
    }

    @Test
    public void test10(){
        Optional<Double> sum = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.reducing(Double::sum));

        System.out.println(sum.get());
    }
}
