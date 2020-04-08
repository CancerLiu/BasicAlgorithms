package com.algorithms.algorithms.fundamentals.bags_queues_stacks;


import java.util.Iterator;

/**
 * 基于Java自带数据类型“数组”实现的。
 * 这里基于模拟Stacks的抽象数据类型几乎(但还没有)达到了任意集合类数据类型的实现的最佳性能——
 * 优点——
 * (1)大部分操作的用时都与集合大小无关;
 * (2)空间需求总是不超过集合大小乘以一个常数;
 * 缺点——
 * (1)某些push()和pop()操作会调整数组的大小——这项操作的耗时和栈大小成正比。
 */
public class ResizingArrayStacks<T> implements Iterable<T> {
    //元素容量
    private int N;
    //存放数据的数组
    private T[] array = (T[]) new Object[1];


    public void push(T t) {
        array[N++] = t;
        //每次容量扩大两倍
        if (N == array.length) {
            resize(2 * N);
        }
    }

    public T pop() {
        T t = array[--N];
        //防止游离状态
        array[N] = null;
        if (N > 0 && N <= array.length / 4) {
            resize(array.length / 2);
        }
        return t;
    }

    //重新调整数组大小——实际就是重新创建数组，然后移动元素
    private void resize(int max) {
        T[] tempArray = (T[]) new Object[max];
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        array = tempArray;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return array[--i];
        }
    }
}
