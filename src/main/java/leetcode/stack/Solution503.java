package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/next-greater-element-ii/
 *
 * @author WeiJinglun
 * @date 2021.03.06
 */
public class Solution503 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        Stack<Integer> stack = new Stack<>();
        // 循环数组,最多 2*n-1 长度
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.empty() && nums[i % n] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i % n];
            }
            // 压栈为数组下标
            stack.push(i % n);
        }
        return res;
    }
}
