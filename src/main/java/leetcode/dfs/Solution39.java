package leetcode.dfs;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/combination-sum/
 *
 * @author WeiJinglun
 * @date 2021.03.07
 */
public class Solution39 {
    //输入：candidates = [2,3,6,7], target = 7,
    //所求解集为：
    //[
    //  [7],
    //  [2,2,3]
    //]
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) {
            return res;
        }
        // 排序是剪枝的前提
        Arrays.sort(candidates);

        dfs(candidates, 0, target, new ArrayDeque<Integer>(), res);
        return res;
    }

    private void dfs(int[] candidates, int index, int target, Deque<Integer> prePath, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(prePath));
            return;
        }
        // i 从 index 开始向后检索,不回退
        for (int i = index; i < candidates.length; i++) {
            // 数组排序后,如果检测到,已经不足以凑够 targe 目标数,之后,后续遍历可直接剪枝掉
            if (target - candidates[i] <0) {
                break;
            }
            prePath.addLast(candidates[i]);
            dfs(candidates, i, target - candidates[i], prePath, res);
            prePath.removeLast();
        }
    }
}
