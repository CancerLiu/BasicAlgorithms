package com.algorithms.algorithms.searching;

import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.RedBlackBST;

/**
 * 二叉查找树的数据结构搜索
 *
 * @param <Key>
 * @param <Value>
 */
public class BinarySortTreeSearch<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        /**
         * 以该结点为根的所有子树中的结点总数
         */
        private int N;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (null == node) return 0;
        else return node.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1);

        int cmp = key.compareTo(x.key);

        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;

        x.N = x.left.N + x.right.N + 1;

        return x;
    }

    /**
     * 查找最小值,最大值差不多,此处不再写明
     *
     * @return 最小值
     */
    //接下来是有序性相关的操作
    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (null == x.left) return x;
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (null == x.right) return x;
        return max(x.right);
    }

    /**
     * 用于查找二叉树中小于等于Key的最大键
     * ceiling(...)查找二叉树中大于等于Key的最小键的情况基本和这个相反。
     *
     * @param key 目标
     * @return
     */
    public Key floor(Key key) {
        return floor(root, key).key;
    }

    private Node floor(Node x, Key key) {
        if (null == x) return null;

        int cmp = key.compareTo(x.key);

        //如果key小于结点,小于key的最大键一定在左子树
        if (cmp == 0) return x;
        else if (cmp < 0) return floor(x.left, key);

        //如果在右子树中,判断右子树中是否有小于key的,如果没有则就是本结点本身
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * 根据索引值得到键
     *
     * @param k
     * @return
     */
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (null == x) return null;

        int t = x.left.N;
        if (t < k) return select(x.right, k - 1 - t);
        else if (t > k) return select(x.left, k);
        else return x;
    }

    /**
     * 根据键获得它的排名
     *
     * @param key
     * @return
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (null == root) return 0;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return 1 + x.left.N + rank(x.right, key);
        else return x.left.N;
    }

    /**
     * 删除最小键
     */
    public void deleteMin() {
        deleteMin(root);
    }

    /**
     * 返回的是已经被删除了最小值的子树的根结点
     *
     * @param x 待删除最小值所属树的根结点
     * @return 返回的已删除对应结点的原树的根结点
     */
    private Node deleteMin(Node x) {
        if (null == x.left) return x.right;

        //最小键一定在左子树中,删除左子树后,将原本指向左子树的指针指向原左子树的右子树。
        x.left = deleteMin(x.left);
        x.N = x.left.N + x.right.N + 1;
        return x;
    }

    /**
     * 总的来说,删除一个结点后,使用该结点的后序结点来填补它的位置
     *
     * @param x   树的根结点
     * @param key 待删除的键
     * @return 返回的已删除对应结点的原树的根结点
     */
    public Node delete(Node x, Key key) {

        if (null == x) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return delete(x.left, key);
        else if (cmp > 0) return delete(x.right, key);
        else {
            //找到待删除的结点后
            if (null == x.left) return x.right;
            if (null == x.right) return x.left;

            //保存待删除结点的状态,之后会用到
            Node t = x;
            //找到待删除结点的后继结点,并替换
            x = min(t.right);
            //将该"后继结点"所在子树的根结点当作该"后继节点"的右子树
            x.right = deleteMin(t.right);
            //新填补的"后继节点"的左子树和之前的结点一样
            x.left = t.left;
        }

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        LinkedQueue<Key> queue = new LinkedQueue<>();

        keys(root, queue, lo, hi);

        return queue;
    }

    private void keys(Node x, LinkedQueue queue, Key lo, Key hi) {
        if (null == x) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmphi >= 0 && cmplo <= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }
}

/**
 * 因为二叉查找树可能退化为链表,此时会造成最坏情况下效率极低。所以引入红黑树进行改良。
 * 红黑树大部分API都可以复用二叉查找树的,所以写在一个文件中
 *
 * @param <Key>
 * @param <Value>
 */
class RedBlackBinarySortTreeSearch<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;

        /**
         * 由其父结点,指向它的链接的颜色
         */
        boolean color;

        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
        }
    }

    /**
     * 判断当前的结点是否是红色
     *
     * @param x 判断结点
     * @return 结果
     */
    private boolean isRed(Node x) {
        if (null == x) return false;
        return x.color == RED;
    }

    /**
     * 红黑树的左旋————右子树结点当作根结点,之前的根结点当作左子树。之前右子树结点的左子树结点当作新的左子树结点的右子树结点。
     *
     * @param x 待旋转的子树的根结点
     * @return 返回的是旋转之后的子树
     */
    private Node rotateLeft(Node x) {
        Node node = x.right;
        x.right = node.left;
        node.left = x;
        node.color = x.color;
        node.N = x.N;
        x.N = x.left.N + x.right.N + 1;

        return node;
    }

    /**
     * 红黑树的右旋————和以上相反
     *
     * @param x 待旋转的子树的根结点
     * @return 返回的是旋转之后的子树
     */
    private Node rotateRight(Node x) {
        Node node = x.left;
        x.left = node.right;
        node.right = x;
        node.color = x.color;
        node.N = x.N;
        x.N = x.left.N + x.right.N + 1;

        return node;
    }

    /**
     * 在红黑树添加或删除元素后所需要进行的颜色调整
     *
     * @param x 待转换颜色的子树根结点
     */
    private void flipColors(Node x) {
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }
    private Node put(Node h, Key key, Value value) {
        if (h == null) return new Node(key, value, 1, RED);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, value);
        else if (cmp > 0) h.right = put(h.right, key, value);
        else h.val = value;

        //对数据进行修复
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = h.left.N + h.right.N + 1;
        return h;
    }

    // TODO: 2020/2/20 delete方法待完成 

}
