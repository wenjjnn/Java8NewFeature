package com.wjn.java8.lambdaPractice;

import org.junit.Test;

/**
 * Lambda表达式练习3：
 * 声明一个带两个泛型的函数式接口，泛型类型为<T,R>  T为参数，R为返回值
 * 接口中声明对应抽象方法
 * 在类中声明方法，使用接口作为参数，计算两个long型参数的和。
 * 再计算两个long型参数的乘积。
 */
public class lambdaPractice3 {

    public void countValue(Long num1, Long num2, MutiTwoValue<Long,Long> mutiTwoValue) {
        System.out.println(mutiTwoValue.getMutiValue(num1,num2));
    }

    @Test
    public void TestLambda() {
        countValue(2L, 3L, (x,y) -> x * y);

        countValue(2L, 3L, (x,y) -> x + y);
    }
}
