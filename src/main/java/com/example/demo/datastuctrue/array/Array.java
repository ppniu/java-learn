package com.example.demo.datastuctrue.array;

/**
 * @author 牛朋朋
 * @date 2019/7/1
 */
public class Array {

    private int[] data;

    /**
     * 已添加元素个数
     */
    private int size;

    //  private final int capacity = 10;

    /**
     * @param capacity data init size
     */
    public Array(int capacity) {
        this.data = new int[capacity];
        this.size = 0;
    }

    public Array() {
        this(10);
    }

    /**
     * get array's length
     *
     * @return
     */
    public int getCapacity() {
        return data.length;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * @param e
     */
    public void addLast(int e) {
        //判断是否越界
        if (size == data.length) {
            throw new IllegalArgumentException("index out of range...");
        }
        data[size] = e;
        size++;
    }

    /**
     * 指定位置插入元素
     *
     * @param e
     * @param index
     */
    public void add(int e, int index) {
        //判断是否越界
        if (size == data.length) {
            throw new IllegalArgumentException("index out of range...");
        }
        //判断index是否合法
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed..");
        }

        for (int i = size - 1; i >= index; i--) {
            //该位置之后元素后退一个位置
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }


    public int remove(int index) {
        //判断index是否合法
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index out of range...");
        }
        int ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return ret;
    }


    public int get(int index) {
        //判断index是否合法
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index out of range...");
        }
        return data[index];
    }


    public boolean contains(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    public int indexAt(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Array: size= %d , capacity = %d\n", size, data.length));
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i != size - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
