package leetcode.union_find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author WeiJinglun
 * @date 2021.01.29
 */
public class Solution1631_UnionFind {
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        // 构建并查集
        UnionFind unionFind = new UnionFind(row * col);
        // 将边压入 [from, to, distinct]
        ArrayList<int[]> edgeList = new ArrayList<>((row - 1) * col + (col - 1) * row);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int from = i * col + j;
                // 连接左上的边
                if (i > 0) {
                    edgeList.add(new int[]{from, from - col, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if (j > 0) {
                    edgeList.add(new int[]{from, from - 1, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }
        edgeList.sort(Comparator.comparingInt(edge -> edge[2]));

        int ans = 0;

        for (int[] edge : edgeList) {
            unionFind.union(edge[0], edge[1]);
            if (unionFind.isConnect(0, row * col - 1)) {
                ans = edge[2];
                break;
            }
        }

        return ans;
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
        int[][] ints = new int[1][];
        ints[0] = new int[]{1, 10, 6, 7, 9, 10, 4, 9};

        System.out.println(new Solution1631_UnionFind().minimumEffortPath(ints));
    }
}
