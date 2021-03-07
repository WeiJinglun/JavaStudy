package leetcode.dfs;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/combination-sum-ii/
 *
 * @author WeiJinglun
 * @date 2021.03.07
 */
public class Solution40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) {
            return res;
        }
        // 排序是剪枝的前提
        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayDeque<Integer>(candidates.length), res);
        return res;
    }

    private void dfs(int[] candidates, int index, int target, Deque<Integer> prePath, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(prePath));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            if (i > 0 && candidates[i] == candidates[i - 1]) {
                continue;
            }
            prePath.addLast(candidates[i]);
            dfs(candidates, i + 1, target - candidates[i], prePath, res);
            prePath.removeLast();
        }
    }

}
