package com.algorithms.algorithms.sorting.basesorting;

public class BaseSortingExample {


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            //默认是排列结果是升序
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    /**
     * 选择排序——冒泡排序
     *
     * @param a 待排序的数组队列
     */
    public void selectionSort(Comparable[] a) {
        int length = a.length;
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i; j < length; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }

    /**
     * 插入排序
     *
     * @param a 待排序的数组队列
     */
    public void insertSort(Comparable[] a) {
        int length = a.length;
        for (int i = 1; i < length - 1; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param a 待排序的数组队列
     */
    public void shellsSort(Comparable[] a) {
        //希尔排序之所以更高效的原因在于其权衡了子数组的规模和有序性
        int length = a.length;
        int h = 1;
        while (h < length / 3) h = h * 3 + 1;

        //三层循环，最外层实时计算
        while (h >= 1) {
            for (int i = h; i < length; i++) {
                //最内层循环是插入排序
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            //此处实时计算递增序列
            h = h / 3;
        }
    }

    /**
     * 用于归并排序的合并
     * 将数据放在辅助数组中————这里是为了分类的方便(即一个方法对应一个排序方法)，
     * 所以才将辅助数组声明为merge(...)方法的局部变量。实际使用时，最好将该数组
     * 声明为全局变量来减少每次new数组的资源消耗.
     *
     * @param a   待排序数组队列
     * @param lo  排序起始位置
     * @param mid 排序中间分割位置
     * @param hi  排序结束位置
     */
    private void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        //辅助数组
        Comparable[] tempArray = new Comparable[a.length];
        for (int k = lo; k <= hi; k++) {
            tempArray[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = tempArray[j++];
            else if (j > hi) a[k] = tempArray[i++];
            else if (less(tempArray[i], tempArray[j])) a[k] = tempArray[i++];
            else a[k] = tempArray[j++];
        }
    }

    /**
     * 自顶向下的归并排序——递归
     *
     * @param a  待排序的数组队列
     * @param lo 起始位置
     * @param hi 结束位置
     */
    public void mergeSortTopToBottom(Comparable[] a, int lo, int hi) {
        //给出迭代终止条件
        if (hi <= lo) return;

        int mid = (hi + lo) / 2;
        mergeSortTopToBottom(a, lo, mid);
        mergeSortTopToBottom(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    /**
     * 自底向上的归并排序——迭代
     *
     * @param a 待排序的数组队列
     */
    public void mergeSortBottomToTop(Comparable[] a) {
        int length = a.length;

        //其中i就是子数组的大小
        //外层循环决定了每次归并的子数组大小，内层循环用于生成子数组并执行归并，不断从小到大拼成子数组。
        for (int i = 1; i < length; i += i) {
            for (int lo = 0; lo < length - i; lo += 2 * i) {
                merge(a, lo, lo + i - 1, Math.min(lo + 2 * i - 1, length - 1));
            }
        }
    }

    /**
     * 快速排序的切分方法
     *
     * @param a  待排序的数组队列
     * @param lo 起始位置
     * @param hi 结束位置
     * @return 返回最新切分元素的位置
     */
    private int partition(Comparable[] a, int lo, int hi) {

        int i = lo, j = hi + 1;
        //得到切分元素
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        //循环完了之后，将切分元素换到当前排序好的数组队列的正确位置
        exch(a, lo, j);
        //并返回最新的切分元素的位置
        return j;
    }

    /**
     * 快速排序
     *
     * @param a  待排序数组队列
     * @param lo 起始位置
     * @param hi 结束位置
     */
    public void quickSort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = partition(a, lo, hi);
        quickSort(a, lo, mid - 1);
        quickSort(a, mid + 1, hi);
    }

    /**
     * 三向切分实现快速排序，主要为了应对待排列数组队列中有很多的重复元素的情况
     *
     * @param a  待排序的数组队列
     * @param lo 起始位置
     * @param hi 结束位置
     */
    public void threeWayQuickSort(Comparable[] a, int lo, int hi) {
        int lt = lo, i = lo + 1, gt = hi;

        //三向切分的切分逻辑
        Comparable v = a[lo];
        while (i <= gt) {
            int cmd = a[i].compareTo(v);
            if (cmd < 0) exch(a, lt++, i++);
            else if (cmd > 0) exch(a, i, gt--);
            else i++;
        }
        threeWayQuickSort(a, lo, lt - 1);
        threeWayQuickSort(a, gt + 1, hi);
    }

    /**
     * 堆排序，这里使用优先队列数据结构，通过不断地取出元素来将数据排序
     * 堆排序的难点是构造堆。
     */
    public void priorityQueueSort(Comparable[] a) {
        //先构造堆
        int N = a.length;
        //因为二叉堆的索引0位置不用，所以k>=1
        for (int k = N / 2; k >= 1; k--) {
            sink(a, N, k);
        }

        //然后排序(这里是默认升序，和前面的排序方式相同)
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, N, 1);
        }
    }

    /**
     * @param a 待排序的数组
     * @param N 数组总长度
     * @param k 当前节点的索引
     */
    private void sink(Comparable[] a, int N, int k) {

        while (2 * k <= N) {
            int j = 2 * k;
            //找出子元素中较大的一个
            if (j < N && less(j, j + 1)) j++;
            //(1)如果是先下沉在上浮，则不需要下面的一句比较————即直接每次提升较大的那个结点。
            if (less(j, k)) break;
            exch(a, j, k);
            k = j;
        }
        //(2)接上述第(1)点，这里再通过while的swim(...)对所有元素上浮

    }


}
