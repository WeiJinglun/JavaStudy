package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 *
 * @author WeiJinglun
 * @date 2021.03.07
 */
public class Solution46 {
    public List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        // 特例判定
        if (nums.length == 0) {
            return res;
        }
        boolean[] used = new boolean[nums.length];
        dfs(nums, 0, new ArrayList<Integer>(), res, used);
        return res;
    }

    /**
     * @param nums
     * @param index   当前来到的数字下标
     * @param prePath 之前做过的判断结果
     * @param res     结果集
     * @param used    记录是否使用
     */
    private void dfs(int[] nums, int index, List<Integer> prePath, List<List<Integer>> res, boolean[] used) {
        //
        if (index == nums.length) {
            // 收集答案,要使用新数组收集答案
            res.add(new ArrayList<>(prePath));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 当前数字未使用过
            if (!used[i]) {
                used[i] = true;
                prePath.add(nums[i]);
                dfs(nums, index + 1, prePath, res, used);
                // 回溯,复原现场
                prePath.remove(prePath.size() - 1);
                used[i] = false;
            }
        }
    }
}
