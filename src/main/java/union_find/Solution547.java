package union_find;

/**
 * https://leetcode-cn.com/problems/number-of-provinces/
 *
 * @author WeiJinglun
 * @date 2021.01.26
 */
public class Solution547 {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }

        return unionFind.size;
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
        public void union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);
            if (xParent != yParent) {
                parent[xParent] = yParent;
                size--;
            }
        }
    }
}
