package leetcode.dp;

/**
 * https://leetcode-cn.com/problems/n-queens-ii/
 *
 * @author WeiJinglun
 * @date 2021.03.06
 */
public class Solution52 {
    public int totalNQueens(int n) {
        return process(n, 0, 0, 0, 0);

    }

    private int process(int n, int row, int colLimit, int leftLimit, int rightLimit) {
        if (row >= n) {
            return 1;
        }
        int ans = 0;
        // pos 的二进制表示其中 1 的位置表示可以放皇后的位置
        int pos = ((1 << n) - 1) & (~(colLimit | leftLimit | rightLimit));

        while (pos != 0) {
            // 取出最右侧的 1
            int rightOne = pos & (-pos);
            // 减去最右侧的 1
            pos = pos & (pos - 1);
            // 计算后续
            ans += process(n, row + 1, colLimit | rightOne, (leftLimit | rightOne) << 1, (rightLimit | rightOne) >> 1);
        }
        return ans;
    }
}
