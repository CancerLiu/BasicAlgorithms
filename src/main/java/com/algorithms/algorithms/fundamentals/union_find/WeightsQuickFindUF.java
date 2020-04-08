package com.algorithms.algorithms.fundamentals.union_find;

/**
 * 加权QuickFind
 */
public class WeightsQuickFindUF {
    private int[] id;
    //用于记录每一个结点所拥有的结点数
    private int[] idCount;
    private int count;

    public WeightsQuickFindUF(int N) {
        count = N;
        id = new int[count];
        idCount = new int[count];
        for (int i = 0; i < count; i++) {
            id[i] = i;
            idCount[i] = 1;
        }
    }

    public void union(int p, int q) {
        int pValue = find(p);
        int qValue = find(q);
        if (qValue == pValue) return;

        //小的拼大的
        if (idCount[pValue] < idCount[qValue]) {
            id[pValue] = qValue;
            idCount[pValue] += idCount[qValue];
        } else {
            id[qValue] = pValue;
            idCount[qValue] += idCount[pValue];
        }
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
