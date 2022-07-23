package com.wjn.java8.lambdaPractice;

@FunctionalInterface
public interface MutiTwoValue<T,R> {

    public R getMutiValue(T x,R y);


}
