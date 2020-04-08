package com.algorithms.algorithms.graphs.undirected_graph;

/**
 * 用于集成常用的图处理方法,需要图是基于邻接表的数据结构
 */
public class GraphHandler {

    /**
     * G图中计算顶点V的度数
     *
     * @param G
     * @param v
     * @return
     */
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int s : G.adj(v)) {
            degree++;
        }
        return degree;
    }

    /**
     * 计算所有顶点的最大度数
     *
     * @param G 图
     * @return 最大度数
     */
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int i = 0; i < G.V(); i++) {
            if (degree(G, i) > max) {
                max = degree(G, i);
            }
        }
        return max;
    }


    /**
     * 计算自环(自环指只有一个顶点的环)的个数
     *
     * @param G 图
     * @return 自环个数
     */
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (w == v) count++;
            }
        }
        return count / 2;
    }


}
