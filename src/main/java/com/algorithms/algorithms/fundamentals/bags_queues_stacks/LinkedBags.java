package com.algorithms.algorithms.fundamentals.bags_queues_stacks;

import java.util.Iterator;

/**
 * 也是基于链表实现的Bag背包功能。
 * 此处实现的迭代功能可以用在LinkedQueues和LinkedStacks中
 */
public class LinkedBags<T> implements Iterable<T> {

    private Node first;
    private int N;

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T t = current.item;
            current = current.next;
            return t;
        }
    }

    private class Node {
        private Node next;
        private T item;
    }

    public void add(T t) {
        Node tempNode = first;
        first = new Node();
        first.item = t;
        first.next = tempNode;
        N++;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
