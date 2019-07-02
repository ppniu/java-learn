package com.example.demo.datastuctrue.stack;

/**
 * @author 牛朋朋
 * @date 2019/7/2
 */
public interface Stack<E> {


    boolean isEmpty();


    void push(E e);


    E pop();


    E peek();
}
