package leetcode.sliding_window;

/**
 * https://leetcode-cn.com/problems/get-equal-substrings-within-budget/
 *
 * @author WeiJinglun
 * @date 2021.02.05
 */
public class Solution1208 {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        // 转换为 字符差的绝对值数组
        int[] abs = new int[n];
        for (int i = 0; i < n; i++) {
            abs[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        // 窗口区间 [l,r] 闭区间
        int l = 0, r = 0;
        // 返回值,取窗口滑动过程中最大值
        int ans = 0;
        // 窗口内的该表消耗
        int cost = 0;
        while (r < n) {
            cost = cost + abs[r];
            if (cost > maxCost) {
                cost = cost - abs[l];
                l++;
            }
            ans = Math.max(ans, (r - l + 1));
            r++;
        }
        return ans;
    }
}
