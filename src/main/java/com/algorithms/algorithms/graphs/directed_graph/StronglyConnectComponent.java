package com.algorithms.algorithms.graphs.directed_graph;


/**
 * 类似于无向图中的寻找所给图中的连通分量。对于有向图,我们寻找的是强连通分量(即顶点间互相可连的情况)
 * <p>
 * 具体使用的是kosaraju算法，该算法形容起来很简答,即得到输入有向图的反向图的顶点的逆后序集合,然后按这个逆后序去遍历最初的有向图
 */
public class StronglyConnectComponent {

    private boolean[] marked;
    private int[] id;
    private int count;

    public StronglyConnectComponent(Directedgraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());

        //使用逆后序来遍历最初的原图
        for (int s : order.getReversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    public void dfs(Directedgraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return marked[v] == marked[w];
    }

    /**
     * 得到顶点对应的强连通分量标志
     */
    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }


}
