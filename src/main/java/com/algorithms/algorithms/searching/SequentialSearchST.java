package com.algorithms.algorithms.searching;

import edu.princeton.cs.algs4.LinkedQueue;

/**
 * 使用链表来实现无序的符号表的顺序查找
 *
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key, Value> {

    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        //无序链表需要遍历查找
        for (Node node = first; node != null; node = node.next) {
            if (node.key == key) return node.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (val == null) delete(null);
        for (Node node = first; node != null; node = node.next) {
            if (node.key.equals(key)) {
                node.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    public void delete(Key key) {
        Node preNode = null;
        for (Node node = first; node != null; node = node.next) {
            if (first.key.equals(key)) {
                first = first.next;
                return;
            } else if (node.key.equals(key)) {
                preNode.next = node.next;
                node.next = null;
                return;
            }
            preNode = node;
        }
    }

    public boolean contains(Key key) {
        for (Node node = first; node != null; node = node.next) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Iterable<Key> keys() {
        LinkedQueue queue = new LinkedQueue();
        for (Node t = first; t != null; t = t.next) {
            queue.enqueue(t);
        }
        return queue;
    }

    public int size() {
        int count = 0;
        for (Node t = first; t != null; t = t.next) {
            count += 1;
        }
        return count;
    }
}
