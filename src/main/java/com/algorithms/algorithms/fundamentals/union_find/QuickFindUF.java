package com.algorithms.algorithms.fundamentals.union_find;

/**
 * 动态连接问题的quick-find实现。着重于find 的效率。
 */
public class QuickFindUF {

    private int[] id;
    private int count;

    public QuickFindUF(int N) {
        count = N;
        id = new int[count];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        int pValue = find(p);
        int qValue = find(q);
        if (pValue == qValue) return;

        for (int i = 0; i < id.length; i++) {
            //注意不能写成if (id[i] == find(p)) id[i] = find(q);
            if (id[i] == pValue) id[i] = qValue;
            count--;
        }
    }

    public int find(int p) {
        return id[p];
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}
