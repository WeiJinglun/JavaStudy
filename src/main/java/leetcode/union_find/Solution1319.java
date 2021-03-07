package leetcode.union_find;

/**
 * https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected/
 *
 * @author WeiJinglun
 * @date 2021.01.28
 */
public class Solution1319 {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            // n 个点至少有 n-1 条边才能连为整体
            return -1;
        }
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : connections) {
            unionFind.union(edge[0], edge[1]);
        }
        return unionFind.size-1;
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
                // 合并连通区域,减小联通区域数量
                size--;
                return true;
            }
            return false;
        }
    }
}
