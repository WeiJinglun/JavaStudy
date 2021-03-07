package leetcode.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/next-greater-element-i/
 *
 * @author WeiJinglun
 * @date 2021.03.06
 */
public class Solution496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        // 存储各数值的右侧最大值
        HashMap<Integer, Integer> map = new HashMap<>();
        // 构建单调栈
        Stack<Integer> stack = new Stack<>();

        // [1,3,4,2]
        // [1,2,3,4]
        for (int cur : nums2) {
            while (!stack.empty() && cur > stack.peek()) {
                // 记录右侧的最大值
                map.put(stack.pop(), cur);
            }
            // 压栈
            stack.push(cur);
        }

        // 填充结果
        for (int i = 0; i < nums1.length; i++) {
            // 右侧没有更大的值,则没有记录,取 -1
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
