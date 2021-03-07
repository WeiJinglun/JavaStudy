package leetcode.sliding_window;

/**
 * https://leetcode-cn.com/problems/grumpy-bookstore-owner/
 *
 * @author WeiJinglun
 * @date 2021.02.23
 */
public class Solution1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int cur = 0;
        int n = customers.length;
        // 先累加不生气的顾客
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                cur += customers[i];
            }
        }
        // 初始化窗口
        for (int i = 0; i < X; i++) {
            if (grumpy[i] == 1) {
                cur += customers[i];
            }
        }
        int ans = cur;
        // 滑动窗口,检测边界
        for (int i = X; i < n; i++) {
            if (grumpy[i] == 1) {
                cur += customers[i];
            }
            if (grumpy[i - X] == 1) {
                cur -= customers[i - X];
            }
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public static int process(int m) {
        if (m < 4) {
            return 0;
        }
        int ans = 0;
        int[] dp = new int[m];
        // 初始化
        for (int i = 0; i < m; i++) {
            dp[i] = (1 << i);
        }
        // 开始检索,只检索到 m-4,后面不能组队
        for (int i = 0; i < m - 4; i++) {
            int cur = dp[i];



        }


        return ans;
    }

    public static void main(String[] args) {
        System.out.println(process(12));
    }
}
