package com.algorithms.algorithms.fundamentals.bags_queues_stacks;

/**
 * 使用环形链表来实现队列
 *
 * @param <T>
 */
public class CircularLinkedQueues<T> {

    private int N;
    private Node first;
    private Node last;

    private class Node {
        private T item;
        private Node next;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    public T dequeue() {
        T t = first.item;
        last.next = first.next;
        first = null;
        return t;
    }

    public void enqueue(T t) {
        if (N > 0) {
            Node node = last;
            last = new Node();
            last.item = t;
            node.next = last;
            last.next = first;
        } else {
            first = last = new Node();
            last.item = t;
            last.next = first;
        }
        N++;
    }
}
