package leetcode.dfs;

/**
 * https://leetcode-cn.com/problems/number-of-islands/
 *
 * @author WeiJinglun
 * @date 2021.03.07
 */
public class Solution200 {
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    // 岛屿计数
                    ans++;
                    dfs(grid, i, j, n, m);
                }
            }
        }
        return ans;
    }

    private void dfs(char[][] grid, int row, int col, int n, int m) {
        if (row < 0 || row >= n || col < 0 || col >= m || grid[row][col] == '0') {
            return;
        }
        // 修改岛屿状态
        grid[row][col] = '0';
        // 检索相邻位置状态
        dfs(grid, row + 1, col, n, m);
        dfs(grid, row, col + 1, n, m);
        dfs(grid, row - 1, col, n, m);
        dfs(grid, row, col - 1, n, m);
    }
}
