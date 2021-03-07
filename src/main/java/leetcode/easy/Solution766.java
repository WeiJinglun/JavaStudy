package leetcode.easy;

/**
 * https://leetcode-cn.com/problems/toeplitz-matrix/
 *
 * @author WeiJinglun
 * @date 2021.02.22
 */
public class Solution766 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = m - 1; i >= 0; i--) {
            if (!pick(matrix, m, n, i, 0)) {
                return false;
            }
        }
        for (int i = 1; i < n; i++) {
            if (!pick(matrix, m, n, 0, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean pick(int[][] matrix, int m, int n, int r, int c) {
        for (int i = r, j = c; i < m && j < n; i++, j++) {
            if (matrix[r][c] != matrix[i][j]) {
                return false;
            }
        }
        return true;
    }
}
