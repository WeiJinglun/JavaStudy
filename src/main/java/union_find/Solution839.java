package union_find;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/similar-string-groups/
 *
 * @author WeiJinglun
 * @date 2021.01.31
 */
public class Solution839 {
    public int numSimilarGroups(String[] strs) {
        int N = strs.length;
        // 构建并查集
        UnionFind unionFind = new UnionFind(N);

        // 遍历字符串
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // 比较 i 和 j 位置字符串能够满足题目需求
                if (unionFind.isConnect(i, j)) {
                    continue;
                }
                if (checkStr(strs[i], strs[j])) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.setCount;
    }

    private boolean checkStr(String x, String y) {
        int size = 0;
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) != y.charAt(i)) {
                if (++size > 2) {
                    return false;
                }
            }
        }
        return true;
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

}
