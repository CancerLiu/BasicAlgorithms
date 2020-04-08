package com.algorithms.algorithms.searching;

import edu.princeton.cs.algs4.LinkedQueue;

/**
 * 基于拉链法的散列表
 *
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST<Key, Value> {


    /**
     * 散列表大小
     */
    private int M;

    /**
     * 存放链表对象的数组
     */
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int m) {
        M = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }

    //以下为自定义的方法,主要是便于使用。也是为了练习
    public Iterable<Key> keys() {
        LinkedQueue queue = new LinkedQueue();
        for (SequentialSearchST<Key, Value> s : st) {
            s.keys().forEach(queue::enqueue);
        }
        return queue;
    }

    public int size() {
        int count = 0;
        for (SequentialSearchST<Key, Value> s : st) {
            count += s.size();
        }
        return count;
    }

    public void delete(Key key) {
        st[hash(key)].delete(key);
    }
}
