package leetcode.sliding_window;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * @author WeiJinglun
 * @date 2021.03.07
 */
public class Solution_sliding_window_maximum {
    // 滑动窗口
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int index = 0;
        // 队列内维护 窗口内下标
        Deque<Integer> deque = new LinkedList<>();
        //输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
        //输出：[3,3,5,5,6,7]
        for (int right = 0; right < n; ++right) {
            // 队列右侧,移除比当前值小的元素
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[right]) {
                deque.removeLast();
            }
            deque.addLast(right);
            // 移除窗口左侧的值
            if (right - k == deque.peek()) {
                deque.poll();
            }

            // 从窗口内部取值
            if (right - k + 1 >= 0) {
                res[index++] = nums[deque.peek()];
            }
        }
        return res;
    }
}
