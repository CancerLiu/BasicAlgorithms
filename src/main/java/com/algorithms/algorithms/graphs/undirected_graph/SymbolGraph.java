package com.algorithms.algorithms.graphs.undirected_graph;

import edu.princeton.cs.algs4.LinearProbingHashST;
import org.apache.commons.lang3.tuple.Pair;
import org.omg.CORBA.Object;

/**
 * 符号图的意义我理解在于可以通过和index索引的映射多引入一个维度的数据。
 * <p>
 * 利用符号图,可以轻松实现反向索引
 */
public class SymbolGraph<T> {

    /**
     * 用于反向索引,保存每个顶点索引所对应的顶点对象
     */
    private T[] keys;

    /**
     * 图对象
     */
    private Graph G;

    /**
     * 符号表,用于具体对象和索引之间的关联
     */
    private LinearProbingHashST<T, Integer> st;

    /**
     * @param t     数组
     * @param pairs pair数组,数组中的每一对值表示新键图中需要连接的顶点对
     */
    public SymbolGraph(T[] t, Pair<Integer, Integer>[] pairs) {
        //具体类型——>索引
        st = new LinearProbingHashST<>();
        for (int i = 0; i < t.length; i++) {
            st.put(t[i], i);
        }

        //索引——>具体类型
        keys = (T[]) new Object[st.size()];
        for (T item : st.keys()) {
            keys[st.get(item)] = item;
        }


        //建立顶点之间的链接
        G = new Graph(st.size());
        for (Pair<Integer, Integer> pair : pairs) {
            G.addEdge(pair.getLeft(), pair.getRight());
        }
    }

    public boolean contains(T t) {
        return st.contains(t);
    }

    public int index(T s) {
        return st.get(s);
    }

    public T name(int i) {
        return keys[i];
    }

    public Graph G() {
        return G;
    }
}
