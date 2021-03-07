package leetcode.dfs;

/**
 * https://leetcode-cn.com/problems/surrounded-regions/
 *
 * @author WeiJinglun
 * @date 2021.03.07
 */
public class Solution130 {
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        // 检索四周边界位置的 O,使其转换为 A
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0, n, m);
            dfs(board, i, m - 1, n, m);
        }

        for (int i = 0; i < m; i++) {
            dfs(board, 0, i, n, m);
            dfs(board, n - 1, i, n, m);
        }
        // 修改 O->X, A->O
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int row, int col, int n, int m) {
        if (row < 0 || row >= n || col < 0 || col >= m || board[row][col] != 'O') {
            return;
        }
        board[row][col] = 'A';
        dfs(board, row + 1, col, n, m);
        dfs(board, row, col + 1, n, m);
        dfs(board, row, col - 1, n, m);
        dfs(board, row - 1, col, n, m);
    }
}
