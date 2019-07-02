package com.example.demo.datastuctrue.stack;

/**
 * 栈
 * @author 牛朋朋
 * @date 2019/7/2
 */
public class StackDemo {

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(10);
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        System.out.println(stack.toString());
    }


}
