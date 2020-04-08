package com.algorithms.algorithms.graphs.directed_graph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 基于深度优先搜索的顶点排序
 * 允许用例用各种顺序遍历深度优先搜索经过的所有顶点
 * <p>
 * 这里包含了三种排序方式:前序、后序与逆后序。
 */
public class DepthFirstOrder {

    private boolean[] marked;

    /**
     * 前序排序
     */
    private Queue<Integer> pre;

    /**
     * 后序排序
     */
    private Queue<Integer> post;

    /**
     * 逆后续排序
     */
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Directedgraph G) {

        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();

        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Directedgraph G, int v) {
        marked[v] = true;

        //前序是在迭代之前
        pre.enqueue(v);

        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }

        //后序实在迭代之后
        post.enqueue(v);
        //逆后续是在迭代之后,并且是放在LIFO的栈中的。
        reversePost.push(v);
    }

    public Queue<Integer> getPre() {
        return pre;
    }

    public Queue<Integer> getPost() {
        return post;
    }

    public Stack<Integer> getReversePost() {
        return reversePost;
    }
}
