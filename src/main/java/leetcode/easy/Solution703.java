package leetcode.easy;

import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/
 *
 * @author WeiJinglun
 * @date 2021.02.11
 */
public class Solution703 {
    class KthLargest {
        PriorityQueue<Integer> queue;
        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.queue = new PriorityQueue<>(k);
            for (int num : nums) {
                queue.add(num);
            }
        }

        public int add(int val) {
            queue.offer(val);
            while (queue.size() > k) {
                queue.poll();
            }
            return queue.peek();
        }
    }
}
