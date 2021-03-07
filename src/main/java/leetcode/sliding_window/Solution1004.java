package leetcode.sliding_window;

/**
 * https://leetcode-cn.com/problems/max-consecutive-ones-iii/
 *
 * @author WeiJinglun
 * @date 2021.02.19
 */
public class Solution1004 {
    public int longestOnes(int[] A, int K) {
        int n = A.length;
        // 窗口边界
        int l = 0, r = 0;
        // 记录窗口内部,0 个数
        int zeroCount = 0;
        int ans = 0;
        while (r < n) {
            // 窗口右移,检测右边界
            if (A[r] == 0) {
                // 窗口内部 0 多于上限,滑动左边界
                if (++zeroCount > K) {
                    while (zeroCount > K) {
                        if (A[l++] == 0) {
                            --zeroCount;
                        }
                    }
                }
            }
            ans = Math.max(ans, ++r - l);
        }
        return ans;
    }
}
