package com.algorithms.algorithms.graphs.weighted_directed_graph;

import com.algorithms.algorithms.graphs.directed_graph.Directedgraph;
import com.algorithms.algorithms.graphs.weighted_undirected_graph.EdgeWeightGraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DirectedDepthFirstOrder {

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

    public DirectedDepthFirstOrder(EdgeWeigtedDirectedgraph G) {

        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();

        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(EdgeWeigtedDirectedgraph G, int v) {
        marked[v] = true;

        //前序是在迭代之前
        pre.enqueue(v);

        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
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
