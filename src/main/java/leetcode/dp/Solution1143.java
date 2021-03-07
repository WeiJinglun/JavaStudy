package leetcode.dp;

/**
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 *
 * @author WeiJinglun
 * @date 2021.02.04
 */
public class Solution1143 {
    public int longestCommonSubsequence1(String text1, String text2) {
        return process1(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1);
    }

    private int process1(char[] chars1, char[] chars2, int i, int j) {
        if (i == 0 && j == 0) {
            return chars1[i] == chars2[j] ? 1 : 0;
        } else if (i == 0) {
            return chars1[i] == chars2[j] ? 1 : process1(chars1, chars2, i, j - 1);
        } else if (j == 0) {
            return chars1[i] == chars2[j] ? 1 : process1(chars1, chars2, i - 1, j);
        } else {
            int p1 = process1(chars1, chars2, i, j - 1);
            int p2 = process1(chars1, chars2, i - 1, j);
            int p3 = chars1[i] == chars2[j] ? 1 + process1(chars1, chars2, i - 1, j - 1) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }


    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int[][] dp = new int[n][m];
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = chars1[i] == chars2[0] ? 1 : dp[i - 1][0];
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = chars1[0] == chars2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int p = chars1[i] == chars2[j] ? 1 + dp[i - 1][j - 1] : 0;
                dp[i][j] = Math.max(p, Math.max(dp[i][j - 1], dp[i - 1][j]));
            }
        }
        return dp[n - 1][m - 1];
    }


}
