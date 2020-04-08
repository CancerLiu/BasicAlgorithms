package com.algorithms.algorithms.fundamentals.bags_queues_stacks;

public class LinkedListStructure<T> {

    private Node first;
    private int N;

    public Node getFirst() {
        return first;
    }

    public int getN() {
        return N;
    }

    private class Node {
        public Node next;
        public T t;
    }

    /**
     * 删除尾节点
     * 除非是双向链表，否则需要遍历链表来找到尾节点。
     */
    public void removeLast() {
        if (null == first.next) {
            first = null;
        }
        Node tempNode = first, preNode = first;

        while (null != tempNode.next) {
            preNode = tempNode;
            tempNode = tempNode.next;
        }
        preNode.next = null;
        tempNode = null;
    }

    /**
     * 删除链表的第index元素
     *
     * @param index 需要删除第index元素
     */
    public void deleteByIndex(int index) {
        if (null == first || N < index) {
            throw new IndexOutOfBoundsException("输入待删除元素的下标越界");
        }
        Node preNode = first;
        int count = 0;
        for (Node tempNode = first; tempNode != null; tempNode = tempNode.next) {
            if (count != 0 && (index - 1) == count) {
                preNode.next = tempNode.next;
                tempNode.next = null;
                tempNode = null;
                return;
            } else if (count == 0 && (index - 1) == count) {
                first = null;
                return;
            }
            preNode = tempNode;
            count++;
        }
    }

    /**
     * 在输入的链表中寻找给定的对象T，找到则返回true，否则返回false
     *
     * @param linkedList 给点的链表
     * @param t          待寻找的对象
     * @return 是否找到
     */
    public boolean find(LinkedListStructure linkedList, T t) {
        for (Node node = linkedList.getFirst(); node != null; node = node.next) {
            if (node.t.equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 接受一个节点并删除该节点的后续一个节点，无则什么也不做
     *
     * @param node 输入节点
     */
    public void removeAfter(Node node) {
        if (node == null || node.t == null) {
            return;
        }
        for (Node tempNode = first; tempNode != null; tempNode = tempNode.next) {
            //先遍历查找到再删除
            if (node.t.equals(tempNode.t) && null != tempNode.next) {
                Node removeNode = tempNode.next;
                tempNode.next = removeNode.next;
                removeNode = null;
                return;
            }
        }
    }

    /**
     * 链表中，根据第一个节点找到位置，在其后插入第二个节点
     *
     * @param firstNode  已存在节点
     * @param secondNode 待插入节点
     */
    public void insertAfter(Node firstNode, Node secondNode) {
        boolean flag = firstNode == null || secondNode == null || firstNode.next == null || secondNode.next == null;
        if (flag) {
            return;
        }
        for (Node tempNode = first; tempNode != null; tempNode = tempNode.next) {
            if (firstNode.t.equals(tempNode.t)) {
                secondNode.next = firstNode.next;
                firstNode.next = secondNode;
                return;
            }
        }
    }

    /**
     * 删除给定链表中所有节点中item域为t的节点
     *
     * @param linkedList 给定的链表
     * @param t          待删除的域值
     */
    public void remove(LinkedListStructure linkedList, T t) {
        Node preNode = linkedList.getFirst();
        for (Node node = linkedList.getFirst(); node != null; node = node.next) {
            if (node.t.equals(t)) {
                preNode.next = node.next;
                node = preNode;
            }
            preNode = node;
        }
    }

    /**
     * 接受一条链表的首节点为参数，返回链表中结点中的item域最大的值(默认item域都为正整数)。无则返回0
     * 这里是遍历的方式
     *
     * @param firstNode 链表中的第一个节点
     * @return 最大值
     */
    public int maxIterator(Node firstNode) {
        if (firstNode == null || firstNode.t == null) {
            return 0;
        }
        if (!(firstNode.t instanceof Integer)) {
            return 0;
        }

        int compare = 0;
        for (Node node = firstNode; node != null; node = node.next) {
            if (compare < (Integer) node.t) {
                compare = (Integer) node.t;
            }
        }
        return compare;
    }

    /**
     * 递归的方式找最大值
     *
     * @param node 结点
     * @return 最大值
     */
    public int maxRecursion(Node node) {
        if (node == null || node.t == null) {
            return 0;
        }
        if (!(node.t instanceof Integer)) {
            return 0;
        }
        if (null == node.next) {
            return -1;
        }
        int compare = (Integer) node.t;
        int nextCompare = maxRecursion(node.next);
        if (compare < nextCompare) {
            return nextCompare;
        }
        return compare;
    }
}
