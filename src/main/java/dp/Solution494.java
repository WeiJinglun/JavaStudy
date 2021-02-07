package dp;

/**
 * https://leetcode-cn.com/problems/target-sum/
 *
 * @author WeiJinglun
 * @date 2021.02.04
 */
public class Solution494 {
    public int findTargetSumWays1(int[] nums, int S) {
        return process1(nums, nums.length - 1, S);
    }

    private int process1(int[] nums, int i, int s) {
        if (i == -1) {
            return s == 0 ? 1 : 0;
        }

        int p1 = process1(nums, i - 1, s - nums[i]);
        int p2 = process1(nums, i - 1, s + nums[i]);
        return p1 + p2;
    }

    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int[][] dp = new int[n][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < n; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[n - 1][S + 1000];
    }
}
