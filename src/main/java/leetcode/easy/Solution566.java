package leetcode.easy;

/**
 * https://leetcode-cn.com/problems/reshape-the-matrix/
 *
 * @author WeiJinglun
 * @date 2021.02.17
 */
public class Solution566 {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length;
        int n = nums[0].length;
        if (m * n != r * c) {
            return nums;
        }
        int[][] ans = new int[r][c];
        int R = 0, C = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ans[i][j] = nums[R][C];
                if (++C >= n) {
                    C = 0;
                    R++;
                }
            }
        }
        return ans;
    }
}
