package com.algorithms.algorithms.fundamentals.union_find;

public class QuickUnionUF {

    private int[] id;
    private int count;

    public QuickUnionUF(int N) {
        count = N;
        id = new int[count];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        int pValue = find(p);
        int qValue = find(q);
        if (qValue == pValue) return;

        id[pValue] = qValue;
        count--;
    }

    public int find(int p) {
        //迭代找每棵树的定点结点号
        while (id[p] != p) p = id[p];
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}
