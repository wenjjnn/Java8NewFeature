package com.wjn.java8.lambdaPractice;

import org.junit.Test;

/**
 * Lambda表达式练习2：
 * 声明函数式接口，接口中声明抽象方法
 * 声明类，类中编写方法使用接口作为参数，将一个字符串转成大写，并作为方法的返回值
 * 再将一个字符串的第2个和第4个索引位置进行截取子串
 */
public class lambdaPractice2 {

    public String changeValue(String str, UpdateString updateString) {
        return updateString.changeValue(str);
    }

    @Test
    public void testLambda() {
        String upperValue = changeValue("abcdefg", x -> x.toUpperCase());
        System.out.println(upperValue);
        String substring = changeValue("abcdefg", x -> x.substring(2,5));
        System.out.println(substring);
    }
}
