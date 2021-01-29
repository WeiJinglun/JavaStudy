package graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/path-with-minimum-effort/
 *
 * @author WeiJinglun
 * @date 2021.01.29
 */
public class Solution1631_Dijkstra {
    //<editor-fold desc="solution 1 Dijkstra">
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        int[][] dp = new int[row][col];
        // -1 该单元格还未访问
        for (int i = 0; i < row; i++) {
            Arrays.fill(dp[i], -1);
        }
        // 从 0,0 开始
        dp[0][0] = 0;
        // 每次取最小边 [dis from to]
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.distinct));

        addEdge(edgeQueue, heights, dp, row, col, 0, 0, 0, 1);
        addEdge(edgeQueue, heights, dp, row, col, 0, 0, 1, 0);

        while (!edgeQueue.isEmpty()) {
            Edge poll = edgeQueue.poll();
            if (dp[poll.toRow][poll.toCol] != -1) {
                continue;
            }
            // 填写当前距离
            dp[poll.toRow][poll.toCol] = Math.max(dp[poll.fromRow][poll.fromCol], poll.distinct);
            if (poll.toRow == (row - 1) && poll.toCol == (col - 1)) {
                break;
            }

            // 压入 目标点 四周的边
            addEdge(edgeQueue, heights, dp, row, col, poll.toRow, poll.toCol, poll.toRow, poll.toCol + 1);
            addEdge(edgeQueue, heights, dp, row, col, poll.toRow, poll.toCol, poll.toRow, poll.toCol - 1);
            addEdge(edgeQueue, heights, dp, row, col, poll.toRow, poll.toCol, poll.toRow + 1, poll.toCol);
            addEdge(edgeQueue, heights, dp, row, col, poll.toRow, poll.toCol, poll.toRow - 1, poll.toCol);
        }

        return dp[row - 1][col - 1];
    }

    // 队列 当前行 当前列 最大行 最大列
    public void addEdge(PriorityQueue<Edge> edgeQueue, int[][] heights, int[][] dp, int row, int col,
                        int fromRow, int fromCol, int toRow, int toCol) {
        if (toRow < 0 || toCol < 0 || toRow >= row || toCol >= col) {
            return;
        }
        if (dp[toRow][toCol] != -1) {
            return;
        }
        edgeQueue.add(new Edge(Math.abs(heights[fromRow][fromCol] - heights[toRow][toCol]), fromRow, fromCol, toRow, toCol));
    }

    class Edge {
        int distinct;//距离
        int fromRow;
        int fromCol;
        int toRow;
        int toCol;

        public Edge(int distinct, int fromRow, int fromCol, int toRow, int toCol) {
            this.distinct = distinct;
            this.fromRow = fromRow;
            this.fromCol = fromCol;
            this.toRow = toRow;
            this.toCol = toCol;
        }
    }
    //</editor-fold>

    public static void main(String[] args) {
        int[][] ints = new int[1][];
        ints[0] = new int[]{1, 10, 6, 7, 9, 10, 4, 9};

        System.out.println(new Solution1631_Dijkstra().minimumEffortPath(ints));
    }
}
