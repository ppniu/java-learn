package com.example.demo.datastuctrue.array;

/**
 * @author 牛朋朋
 * @date 2019/7/1
 */
public class ArrayDemo {



    public static void main(String[] args) {
        Array<String> array = new Array(5);

        for (int i = 0; i < 5; i++) {
            array.addLast("haha" + i);
        }
        for (int i = 0; i < 5; i++) {
            array.addLast("hehe" + i);
        }
        System.out.println(array.toString());

      //  System.out.println(array.indexAt(7));

        System.out.println();
    }




}
