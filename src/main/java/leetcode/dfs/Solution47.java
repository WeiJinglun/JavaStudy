package leetcode.dfs;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/permutations-ii/
 *
 * @author WeiJinglun
 * @date 2021.03.07
 */
public class Solution47 {
    // [1, 1, 2]
    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        // 排序为了方便剪枝
        Arrays.sort(nums);
        boolean[] used = new boolean[n];
        // 使用 Deque 是 Java 官方 Stack 类的建议
        dfs(nums, 0, used, new ArrayDeque<Integer>(n), res);
        return res;
    }

    private void dfs(int[] nums, int depth, boolean[] used, Deque<Integer> prePath, List<List<Integer>> res) {
        if (depth == nums.length) {
            // 收集答案,使用新 list 收集结果
            res.add(new ArrayList<>(prePath));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 剪枝, 比对前一位数值,一样的情况下,肯定是之前检索过了,如果前一位使用了,说明第一次检索
            // !used[i-1],这里是关键位置,因为回溯的情况下,前一位肯定已经回退到 false 状态了
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            prePath.addLast(nums[i]);
            dfs(nums, depth + 1, used, prePath, res);
            prePath.removeLast();
            used[i] = false;
        }
    }
}
