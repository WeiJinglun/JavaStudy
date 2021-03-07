package leetcode.sliding_window;

/**
 * https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/
 *
 * @author WeiJinglun
 * @date 2021.02.18
 */
public class Solution995 {
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;

        if (K > n) {
            return -1;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (i + K > n) {
                break;
            }

            if (A[i] == 0) {
                // 翻转
                ans++;
                for (int j = i; j < i + K; j++) {
                    A[j] ^= 1;
                }
            }
        }
        // 检测最后一个窗口
        for (int i = n-K; i < n; i++) {
            if (A[i] != 1) {
                return -1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution995().minKBitFlips(new int[]{0, 1, 0}, 1));
    }
}
