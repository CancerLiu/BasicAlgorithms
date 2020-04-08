package com.algorithms.algorithms.sorting.basesorting;

/**
 * 具体优先队列的实现的关键在于两个方法————插入(insert(Key v))与删除最大(最小)元素(delNax())上。
 * 分为有序与无序的
 * 无序————
 * 可以用数组实现，此时的insert(...)就是普通的插入，delMax()的时候再用相关排序方法找到最大的元素返回
 * 有序————
 * 数组实现时，insert(...)方法实时将比它大的元素依次往右移动，腾出位置;delMax()方法则类似于栈的pop()方法，直接返回最小的;
 * 链表实现时，insert(...)方法需要遍历找到其位置，再插入;delMax(...)方法则为删除firstNode.
 * <p>
 * 这里是二叉堆数据结构实现优先队列的代码，其中二叉堆是用数组来表示的.
 */
public class PriorityQueues<T extends Comparable<T>> {

    private T[] pq;
    private int N;

    public PriorityQueues(int maxN) {
        pq = (T[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(T t) {
        //加入再上浮
        pq[++N] = t;
        swim(N);
    }

    public T delMax() {
        //刪除最大的，然后将最小的放入根结点再下沉。
        T max = pq[1];
        pq[1] = pq[N--];
        //这里+1很关键，第一次写的时候忘了
        pq[N + 1] = null;
        sink(1);

        return max;
    }

    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public void exch(int i, int j) {
        T temp = pq[i];
        pq[j] = pq[i];
        pq[i] = temp;
    }

    public void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (k * 2 <= N) {
            int j = 2 * k;
            //找出两个子元素中较大的一个
            if (j < N && less(j, j + 1)) j++;
            if (less(j, k)) break;
            exch(j, k);
            k = j;
        }
    }
}
