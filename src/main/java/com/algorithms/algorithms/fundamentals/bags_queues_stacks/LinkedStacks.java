package com.algorithms.algorithms.fundamentals.bags_queues_stacks;

/**
 * 使用链表来实现Stacks栈
 * 链表表示的是一列元素。
 * 优点——
 * (1)删除、添加元素所需时间都与链表长度无关
 * (2)所需空间总是和集合的大小成正比。
 * <p>
 * 引入链表结构可知，算法和数据结构是相辅相成的。
 */
public class LinkedStacks<T> {

    private Node first;
    private int N;

    //因为Java没有自带链表的结构，所以此处通过内部类引入自定义的链表结构
    private class Node {
        private T item;
        private Node next;
    }

    public void push(T t) {
        if (null != first) {
            Node tempNode = first;
            first = new Node();
            first.item = t;
            first.next = tempNode;
        } else {
            first = new Node();
            first.item = t;
        }
        N++;
    }

    public T pop() {
        T t = first.item;
        first = first.next;
        N--;
        return t;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }
}
