package union_find;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/swim-in-rising-water/
 *
 * @author WeiJinglun
 * @date 2021.01.30
 */
public class Solution778 {
    // 相邻点,四个方向
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int swimInWater(int[][] grid) {
        int ans = grid[0][0];
        int N = grid.length;
        // [row col grid[row][col]]
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge[2]));
        queue.offer(new int[]{0, 0, grid[0][0]});

        UnionFind unionFind = new UnionFind(N * N);
        boolean[] visited = new boolean[N * N];

        while (!queue.isEmpty()) {
            int[] edge = queue.poll();
            int id = edge[0] * N + edge[1];
            if (visited[id]) {
                continue;
            }
            visited[id] = true;

            unionFind.union(0, id);
            ans = Math.max(ans, edge[2]);
            if (unionFind.isConnect(0, N * N - 1)) {
                break;
            }
            addEdge(queue, grid, visited, N, edge[0], edge[1]);
        }
        return ans;
    }

    // 队列 当前行 当前列 最大行 最大列
    public void addEdge(PriorityQueue<int[]> edgeQueue, int[][] grid, boolean[] visited, int N, int row, int col) {
        for (int[] direction : directions) {
            int toRow = row + direction[0];
            int toCol = col + direction[1];
            if (toRow >= 0 && toRow < N && toCol >= 0 && toCol < N && !visited[toRow * N + toCol]) {
                edgeQueue.offer(new int[]{toRow, toCol, grid[toRow][toCol]});
            }
        }
    }

    /**
     * 并查集
     */
    static class UnionFind {
        // 使用 角标指向自己,代替
        int[] parent;
        // 每个连通分量的数据集大小
        int[] size;
        // 当前并查集内连通分量
        int setCount;

        /**
         * 初始化并查集
         *
         * @param n 初始大小
         */
        public UnionFind(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            this.setCount = n;
        }


        public int find(int i) {
            // 压缩路径
            return i == parent[i] ? i : (parent[i] = find(parent[i]));
        }

        /**
         * 合并
         */
        public boolean union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);
            if (xParent == yParent) {
                return false;
            }
            if (size[xParent] < size[yParent]) {
                int temp = xParent;
                xParent = yParent;
                yParent = temp;
            }
            parent[yParent] = xParent;
            size[xParent] += size[yParent];
            --setCount;
            return true;
        }

        public boolean isConnect(int x, int y) {
            return find(x) == find(y);
        }
    }

    public static void main(String[] args) {
//        [[10,12,4,6],[9,11,3,5],[1,7,13,8],[2,0,15,14]]
        int[][] grid = new int[4][4];
        grid[0] = new int[]{10, 12, 4, 6};
        grid[1] = new int[]{9, 11, 3, 5};
        grid[2] = new int[]{1, 7, 13, 8};
        grid[3] = new int[]{2, 0, 15, 14};
//        [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
//        int[][] grid = new int[5][5];
//        grid[0] = new int[]{0, 1, 2, 3, 4};
//        grid[1] = new int[]{24, 23, 22, 21, 5};
//        grid[2] = new int[]{12, 13, 14, 15, 16};
//        grid[3] = new int[]{11, 17, 18, 19, 20};
//        grid[4] = new int[]{10, 9, 8, 7, 6};


        System.out.println(new Solution778().swimInWater(grid));
    }
}
