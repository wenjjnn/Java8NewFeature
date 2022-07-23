package com.wjn.java8.lambdaPractice;


import com.wjn.java8.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Lambda表达式练习1：
 * 调用Colloections.sort()方法，通过定制排序比较两个Employee（先按年龄比，年龄相同按姓名比），使用Lambda作为参数传递
 */
public class lambdaPractice1 {

    List<Employee> emps = Arrays.asList(
            new Employee(101,"张三",18,9999.99),
            new Employee(102,"张四",59,6666.66),
            new Employee(103,"王五",28,3333.33),
            new Employee(104,"赵六",18,7777.77),
            new Employee(105,"田七",38,5555.55)
            );

    @Test
    public void test1() {
        Collections.sort(emps, (x,y) -> {
            if (x.getAge() == y.getAge()) {
                return x.getName().compareTo(y.getName());
            }else {
                return -Integer.compare(x.getAge(),y.getAge());
            }
        });
        for (Employee employee : emps) {
            System.out.println(employee);
        }
    }

}
