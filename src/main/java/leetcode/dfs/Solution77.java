package leetcode.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combinations/
 *
 * @author WeiJinglun
 * @date 2021.03.07
 */
public class Solution77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(n, k, 0, new ArrayDeque<Integer>(k), res);
        return res;
    }

    private void dfs(int n, int k, int index, Deque<Integer> prePath, List<List<Integer>> res) {
        if (prePath.size() == k) {
            res.add(new ArrayList<>(prePath));
            return;
        }
        if (index > n) {
            return;
        }
        for (int i = index; i < n; i++) {
            prePath.addLast(i + 1);
            dfs(n, k, i + 1, prePath, res);
            prePath.removeLast();
        }
    }
}
