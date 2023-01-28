package com.github.donkeyrit.utils;

@FunctionalInterface
public interface ThrowingFunction<T, R> {
   R apply(T t) throws Exception;
}

