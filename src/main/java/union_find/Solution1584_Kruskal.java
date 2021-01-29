package union_find;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/min-cost-to-connect-all-points/
 * <p>
 * 最小生成树 Kruskal
 *
 * @author WeiJinglun
 * @date 2021.01.27
 */
public class Solution1584_Kruskal {
    // points[i] = [xi, yi]
    // 曼哈顿距离 ：|xi - xj| + |yi - yj|
    public int minCostConnectPoints(int[][] points) {
        int result = 0;
        // 点数
        int n = points.length;
        // 边集 [distinct, i, j], 升序排列
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // 点 i 到 j 的距离
                priorityQueue.offer(new int[]{Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]), i, j});
            }
        }

        // 创建并查集
        UnionFind unionFind = new UnionFind(n);

        // 遍历边集合
        while (!priorityQueue.isEmpty()) {
            int[] edge = priorityQueue.poll();
            if (unionFind.union(edge[1], edge[2])) {
                result += edge[0];
            }
            if (unionFind.size == 1) {
                break;
            }
        }
        return result;
    }

    /**
     * 并查集
     */
    static class UnionFind {
        // 使用 角标指向自己,代替
        int[] parent;
        int size;

        /**
         * 初始化并查集
         *
         * @param n 初始大小
         */
        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            this.size = n;
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
            if (xParent != yParent) {
                parent[xParent] = yParent;
                // 合并连通区域,减小连通区域数量
                size--;
                return true;
            }
            return false;
        }
    }

}
