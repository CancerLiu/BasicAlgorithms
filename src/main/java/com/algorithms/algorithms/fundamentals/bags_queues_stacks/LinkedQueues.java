package com.algorithms.algorithms.fundamentals.bags_queues_stacks;

/**
 * 使用链表实现Stack栈一样，这里使用链表实现Queue队列.
 * 相比于栈，此处有两个指针，分别指向队列的头和尾。
 */
public class LinkedQueues<T> {

    private int N;
    private Node first;
    private Node last;

    private class Node {
        private T item;
        private Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    /**
     * 向表尾添加元素
     *
     * @param t
     */
    public void enqueue(T t) {
        //向表尾添加元素
        Node tempNode = last;
        last = new Node();
        last.item = t;
        last.next = null;
        tempNode.next = last;
        N++;
    }

    /**
     * 表头删除元素
     *
     * @return
     */
    public T dequeue() {
        T t = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return t;
    }

}
