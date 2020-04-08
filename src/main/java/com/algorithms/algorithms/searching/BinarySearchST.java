package com.algorithms.algorithms.searching;

import com.algorithms.algorithms.fundamentals.bags_queues_stacks.LinkedQueues;
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.LinkedQueue;

/**
 * 基于数组实现的有序数组,使用了二分查找。同时使用了并行数组来表示符号表的键和值
 * 其中rank(...)方法是关键
 *
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Comparable[capacity];
    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int index = rankIteration(key);
        if (index < N && keys[index].compareTo(key) == 0) return vals[index];
        else return null;
    }

    public void put(Key key, Value val) {
        int index = rankIteration(key);
        if (index < N && keys[index].compareTo(key) == 0) {
            vals[index] = val;
            return;
        }
        //为新元素腾位置
        for (int j = N; j > index; j--) {
            //j=index+1时,j-1索引就已经将index位置元素移动走
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[index] = key;
        vals[index] = val;
        N++;
    }

    public void delete(Key key) {
        int index = rankIteration(key);
        if (index < N && keys[index].compareTo(key) == 0) {
            for (int j = index; j < N - 1; j++) {
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }
            N--;
        }
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 找出大于Key对应value值的最小Key
     *
     * @param key 目标key
     * @return
     */
    public Key ceiling(Key key) {
        int index = rankIteration(key);
        return keys[index];
    }

    /**
     * 找出小于Key对应value的最大key
     *
     * @param key 目标key
     * @return
     */
    public Key floor(Key key) {
        int index = rankIteration(key);
        return keys[index + 1];
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        LinkedQueue<Key> queue = new LinkedQueue<>();
        for (int i = rankIteration(lo); i < rankIteration(hi); i++) {
            queue.enqueue(keys[i]);
            //因为如果rankIteration没有命中,返回的是下限。
            if (contains(hi)) {
                queue.enqueue(keys[rankIteration(hi)]);
            }
        }
        return queue;
    }

    public boolean contains(Key key) {
        int index = rankIteration(key);
        if (index < N && keys[index].compareTo(key) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 基于有序数组的二分查找,找对对应key的位置
     * 迭代的方式实现
     *
     * @param key 待搜寻的key
     * @return 位置索引
     */
    private int rankIteration(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        //没找到返回升序最接近的位置
        return lo;
    }

    /**
     * 基于有序数组的二分查找,找对对应key的位置
     * 递归的方式实现
     *
     * @param key 待搜寻的key
     * @param lo  搜寻起始位置
     * @param hi  搜寻结束位置
     * @return 位置索引
     */
    private int rankRecursion(Key key, int lo, int hi) {
        if (lo >= hi) return lo;

        int mid = (lo + hi) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) return rankRecursion(key, lo, mid - 1);
        else if (cmp > 0) return rankRecursion(key, mid + 1, hi);
        else return mid;
    }
}
