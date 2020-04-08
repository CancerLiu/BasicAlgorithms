package com.algorithms.algorithms.fundamentals.bags_queues_stacks;

/**
 * 双向链表实现的队列.
 * <p>
 * 这里为了简便，都默认输入的待删除、待定位的节点都是存在的且非空(不再做迭代校验)。
 * 注意不包括待加入的节点。
 * <p>
 * 双向链表主要需要注意的就是调用相关结点的next或prev方法时候可能得到的是null对象，此时再通过该对象调用prev或next
 * 或报错
 */
public class DoubleLinkedQueues<T> {

    private int N;
    private DoubleNode first;
    private DoubleNode last;

    private class DoubleNode {
        private T item;
        private DoubleNode next;
        private DoubleNode prev;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void removeObj(DoubleNode node) {
        if (node == first) {
            DoubleNode secondNode = first.next;
            if (secondNode != null) secondNode.prev = null;
            first = secondNode;
        } else if (node == last) {
            DoubleNode secondNode = last.prev;
            if (secondNode != null) secondNode.next = null;
            last = secondNode;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        N--;

    }

    public void addFirst(DoubleNode node) {
        if (first != null) {
            first.prev = node;
            node.next = first;
        } else {
            last = first = node;
        }
        N++;
    }

    public void addLast(DoubleNode node) {
        if (last != null) {
            last.next = node;
            node.prev = last;
        } else if (first == null) {
            addFirst(node);
        } else {
            last = node;
            first = last.prev;
            first.next = last;
        }
        N++;
    }

    public T removeFirst() {
        if (first != null) {
            DoubleNode node = first;
            DoubleNode secondNode = first.next;
            if (secondNode != null) secondNode.prev = null;
            first = secondNode;
            N++;
            return node.item;
        }
        N++;
        return null;
    }

    public T removeLast() {
        if (last != null) {
            DoubleNode node = last;
            DoubleNode secondNode = last.prev;
            if (secondNode != null) secondNode.next = null;
            last = secondNode;
            N++;
            return node.item;
        }
        N++;
        return null;
    }

    public void insertAfter(DoubleNode firstNode, DoubleNode secondNode) {
        DoubleNode afterNode = firstNode.next;
        if (afterNode != null) afterNode.prev = secondNode;
        secondNode.next = afterNode;
        firstNode.next = secondNode;
        secondNode.prev = firstNode;
        N++;
    }

    public void insertBefore(DoubleNode firstNode, DoubleNode secondNode) {
        DoubleNode beforeNode = firstNode.prev;
        if (beforeNode != null) beforeNode.next = secondNode;
        secondNode.prev = beforeNode;
        secondNode.next = firstNode;
        firstNode.prev = secondNode;
        N++;
    }


}
