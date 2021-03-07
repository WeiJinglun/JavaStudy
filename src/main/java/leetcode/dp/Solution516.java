package leetcode.dp;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 *
 * @author WeiJinglun
 * @date 2021.02.04
 */
public class Solution516 {
    public int longestPalindromeSubseq1(String s) {
        return process1(s.toCharArray(), 0, s.length() - 1);
    }

    private int process1(char[] chars, int L, int R) {
        if (L > R) {
            return 0;
        }
        if (L == R) {
            return 1;
        }

        if (chars[L] == chars[R]) {
            return 2 + process1(chars, L + 1, R - 1);
        } else {
            int p1 = process1(chars, L + 1, R);
            int p2 = process1(chars, L, R - 1);
            return Math.max(p1, p2);
        }
    }

    public int longestPalindromeSubseq(String s) {
        char[] chars = s.toCharArray();
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 自底向上,填写 leetcode.dp 表
        for (int L = n - 1; L >= 0; L--) {
            for (int R = 0; R < n; R++) {
                if (L >= R) {
                    continue;
                }
                if (chars[L] == chars[R]) {
                    dp[L][R] = 2 + dp[L + 1][R - 1];
                } else {
                    dp[L][R] = Math.max(dp[L + 1][R], dp[L][R - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
