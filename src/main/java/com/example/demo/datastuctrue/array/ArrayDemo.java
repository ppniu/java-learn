package com.example.demo.datastuctrue.array;

/**
 * @author 牛朋朋
 * @date 2019/7/1
 */
public class ArrayDemo {



    public static void main(String[] args) {
        Array array = new Array(10);

        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }

        System.out.println(array.toString());

        System.out.println(array.indexAt(7));
    }




}
