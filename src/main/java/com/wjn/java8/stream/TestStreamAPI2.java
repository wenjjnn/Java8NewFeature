package com.wjn.java8.stream;

import com.wjn.java8.lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * 中间操作
 */
public class TestStreamAPI2 {

    List<Employee> emps = Arrays.asList(
            new Employee(101,"张三",18,9999.99),
            new Employee(102,"张四",59,6666.66),
            new Employee(103,"王五",28,3333.33),
            new Employee(104,"赵六",18,7777.77),
            new Employee(105,"田七",38,5555.55),
            new Employee(105,"田七",38,5555.55),
            new Employee(105,"田七",38,5555.55)
    );

    // 内部迭代：迭代操作由Stream API完成
    @Test
    public void test1() {
        // 中间操作：不会执行任何操作
        Stream<Employee> employeeStream = emps.stream().filter(e -> e.getAge() > 35);
        // 终止操作：一次性执行全部内容，即“惰性求值”
        employeeStream.forEach(System.out::println);
    }

    /**
     * 外部迭代
      */
    @Test
    public void test2() {
        Iterator<Employee> iterator = emps.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 演示limit：截断流，使其元素不超过给定数量。
     * 满足limit个数，就直接短路，不再继续迭代，可以提高效率
     */
    @Test
    public void test3() {
        emps.stream()
                .limit(2)
                .filter(e -> e.getSalary() > 5000)
                .forEach(System.out::println);

    }

    /**
     * 演示skip：跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素
     * 不足 n 个，则返回一个空流。与 limit(n) 互补
     */
    @Test
    public void test4() {
        emps.stream()
                .filter(e -> e.getSalary() > 5000)
                .skip(2)
                .forEach(System.out::println);
    }

    /**
     * 演示distinct:筛选，通过流所生成元素的 hashCode() 和 equals() 去
     * 除重复元素
     */
    @Test
    public void test5() {
        emps.stream().distinct().forEach(e -> System.out.println(e.getName()));
    }

    /**
     * 演示map：接收一个函数作为参数，该函数会被应用到每个元
     * 素上，并将其映射成一个新的元素。
     */
    @Test
    public void test6() {
        String[] arr = new String[]{"aaa", "bbb", "ccc", "ddd"};
        List<String> list = Arrays.asList(arr);
        list.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);
        System.out.println("------------------------------------");
        emps.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    @Test
    public void test7() {

        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        Stream<String> stream = strList.stream()
                .map(String::toUpperCase);

        stream.forEach(System.out::println);

        System.out.println("-------------------------------------------");

        Stream<Stream<Character>> stream2 = strList.stream()
                .map(TestStreamAPI2::filterCharacter);

        stream2.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("---------------------------------------------");

        // flatMap的使用
        Stream<Character> stream3 = strList.stream()
                .flatMap(TestStreamAPI2::filterCharacter);

        stream3.forEach(System.out::println);
    }


    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    /**
     * 演示排序
     * sorted()  --- 自然排序
     * sorted(Comparator comp)  --- 定制排序
     */
    @Test
    public void test8() {
        List<String> strList = Arrays.asList("ccc","aaa", "bbb", "ddd", "eee");
        strList.stream().
                sorted().
                forEach(System.out::println);
        System.out.println("-------------------------------------------");
        emps.stream()
                .sorted((e1,e2)->{
                    if (e1.getAge() == e2.getAge()) {
                        return e1.getName().compareTo(e2.getName());
                    }else {
                        return Integer.compare(e1.getAge(),e2.getAge());
                    }
                }).forEach(System.out::println);

    }

}
