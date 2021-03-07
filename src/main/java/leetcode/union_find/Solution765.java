package leetcode.union_find;

/**
 * https://leetcode-cn.com/problems/couples-holding-hands/
 *
 * @author WeiJinglun
 * @date 2021.02.14
 */
public class Solution765 {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        UnionFind unionFind = new UnionFind(n / 2);
        for (int i = 0; i < n; i+=2) {
            int l = row[i]/2;
            int r = row[i + 1] / 2;
            unionFind.union(l, r);
        }
        return n / 2 - unionFind.size;
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

    public int countHomogenous(String s) {
        int ans = 0;
        int n = s.length();
        int l = 0, r = 0;
        while (r < n) {
            if (s.charAt(l) != s.charAt(r)) {
                ans += cal(r, l);
                l = r;
            }
            r++;
        }
        ans += cal(r,l);
        return ans;
    }

    private int cal(int r, int l) {
        int t = r - l;
        if ((t & 1) == 0) {
            return (t >> 1) * (t + 1);
        } else {
            return t * ((t + 1) >> 1);
        }
    }
}
