package dp;

/**
 * https://leetcode-cn.com/problems/fibonacci-number/
 *
 *
 * @author WeiJinglun
 * @date 2021.01.28
 */
public class Solution509 {
    public int fib(int n) {
        if (n<2) {
            return n;
        }
         // dp[n] = dp[n-1] + dp[n-2]
        int[] dp = new int[]{1, 0};
        for (int i = 2; i <= n; i++) {
            int temp = dp[0] + dp[1];
            dp[1] = dp[0];
            dp[0] = temp;
        }
        return dp[0];
    }
}
