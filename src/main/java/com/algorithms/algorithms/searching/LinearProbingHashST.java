package com.algorithms.algorithms.searching;

/**
 * 基于线性探测的符号表
 *
 * @param <Key>
 * @param <Value>
 */
public class LinearProbingHashST<Key, Value> {

    /**
     * 键值对数
     */
    private int N;

    /**
     * 初始探测表大小
     */
    private int M = 16;

    /**
     * 使用一组并行数组来表示符号表的键值对应关系
     */
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int cap) {
        keys = (Key[]) new Object[cap];
        values = (Value[]) new Object[cap];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value val) {
        if (N >= M / 2) resize(2 * M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                values[i] = val;
                return;
            }
        }
        keys[i] = key;
        values[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) return values[i];
        }
        return null;
    }

    public boolean contains(Key key) {
        for (Key k : keys) {
            if (k.equals(key)) return true;
        }
        return false;
    }


    /**
     * 这里删除需要注意的就是,在移除元素后,需要依次移动相关元素把移除元素时留下的坑填了。
     *
     * @param key 待删除的key
     */
    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);

        //定位待删除的元素
        while (!keys[i].equals(key)) i = (i + 1) % M;

        //进行删除
        keys[i] = null;
        values[i] = null;

        //开始移动元素
        i = (i + 1) % M;

        //将当前键簇循环完
        while (keys[i] != null) {
            //暂存,便于之后使用put(...)重新加入,自动找到适当的位置
            Key tempKey = keys[i];
            Value tempValue = values[i];

            keys[i] = null;
            values[i] = null;
            N--;

            put(tempKey, tempValue);

            i = (i + 1) % M;
        }

        N--;
        if (N > 0 && N <= M / 8) resize(M / 2);
    }


    /**
     * 调整数组大小对于线性探测法是必须的,但是对于。拉链法是非必须的,甚至会拖慢运行效率。
     * <p>
     * 底层就是就是重新new一个数组,然后依次重新放元素
     */
    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) put(keys[i], values[i]);
        }
        keys = t.keys;
        values = t.values;
        M = t.M;
    }
}
