package com.algorithms.algorithms.fundamentals.bags_queues_stacks;

/**
 * 此处用可变数组来实现队列。数组索引小的方向作为队列头，索引大的方向作为队列尾。
 */
public class ResizingArrayQueues<T> {


    private T[] array = (T[]) new Object[1];
    private int N;
    //用于指示队列头
    private int index;

    public T dequeue() {
        T t = array[index++];
        if (N > 0 && (N - index) <= array.length / 4) {
            resize(array.length / 2);
        }
        return t;
    }

    public void enqueue(T t) {
        array[N++] = t;
        if (N == array.length) {
            resize(2 * N);
        }
    }

    public int size() {
        return (N - index);
    }

    public boolean isEmpty() {
        return (N - index) == 0;
    }

    private void resize(int max) {
        T[] tempArray = (T[]) new Object[max];
        int j = 0;
        for (int i = index; i < N; i++) {
            tempArray[j] = array[i];
            j++;
        }
        array = tempArray;
    }

    /**
     * 接受一个参数k，队列中倒数第k个元素
     *
     * @param k
     * @return 取得的元素
     */
    public T getObjByIndex(int k) {
        if ((N - index) < k || k <= 0) {
            System.out.println("元素不够");
            return null;
        }
        return array[N - k];
    }

    public static void main(String[] args) {
        ResizingArrayQueues<String> queues = new ResizingArrayQueues<>();
        queues.enqueue("haha");
        System.out.println(queues.size());
        queues.enqueue("xixi");
        System.out.println(queues.size());
        queues.enqueue("heihei");
        System.out.println(queues.size());
        queues.enqueue("huahua");
        System.out.println(queues.size());
        queues.enqueue("huohuo");
        System.out.println(queues.size());
        queues.dequeue();
        System.out.println(queues.size());
        queues.enqueue("papa");
        System.out.println(queues.size());
        queues.enqueue("mama");
        System.out.println(queues.size());
        queues.enqueue("nini");
        System.out.println(queues.size());
        System.out.println(queues.isEmpty());
    }
}
