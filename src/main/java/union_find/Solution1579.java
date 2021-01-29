package union_find;

/**
 * https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/
 *
 * @author WeiJinglun
 * @date 2021.01.27
 */
public class Solution1579 {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        // 分别构建 Alice 和 Bob 两个并查集,用于判断各自是否能够到达整个图
        UnionFind unionFindA = new UnionFind(n);
        UnionFind unionFindB = new UnionFind(n);
        // 可以删除的边
        int result = 0;

        // 偏移图的标志位,从 0 开始
        for (int[] edge : edges) {
            --edge[1];
            --edge[2];
        }

        // 先判断 type 为 3 的公共通道
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                // 判断两端是否已经连通
                if (unionFindA.union(edge[1], edge[2])) {
                    unionFindB.union(edge[1], edge[2]);
                    // 说明 x y 第一次联通
                } else {
                    // x y 之前已经处于一个连通区域,删除此边
                    result++;
                }
            }
        }

        // 检测各自的独占边
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                // Alice 独占边
                if (!unionFindA.union(edge[1], edge[2])) {
                    result++;
                }
            } else if (edge[0] == 2) {
                // Bob 独占边
                if (!unionFindB.union(edge[1], edge[2])) {
                    result++;
                }
            }
        }

        // 检测 Alice Bob 是否能够到达所有点
        if (unionFindA.size != 1 || unionFindB.size != 1) {
            return -1;
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
                // 合并连通区域,减小联通区域数量
                size--;
                return true;
            }
            return false;
        }
    }
}
