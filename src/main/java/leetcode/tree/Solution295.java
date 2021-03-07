package leetcode.tree;

import java.util.PriorityQueue;

/**
 * @author WeiJinglun
 * @date 2021.02.04
 */
public class Solution295 {
    class MedianFinder {
        int size;
        PriorityQueue<Integer> minTree;
        PriorityQueue<Integer> maxTree;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            this.minTree = new PriorityQueue<>();
            this.maxTree = new PriorityQueue<>((o1, o2) -> o2 - o1);
        }

        public void addNum(int num) {
            size++;
            maxTree.offer(num);
            // 保证左侧最大堆,都是比右侧最小堆中数小
            minTree.offer(maxTree.poll());
            // 总大小为偶数时,从右侧最小堆,弹出最小元素,放入最大堆
            if ((size & 1) == 0) {
                maxTree.offer(minTree.poll());
            }
        }

        public double findMedian() {
            if ((size & 1) == 0) {
                // 偶数,为两侧平均值
                return (maxTree.peek() + minTree.peek()) * 1.0 / 2;
            } else {
                // 奇数,为右侧最小堆,堆顶
                return minTree.peek();
            }
        }
    }
}
