package leetcode.union_find;

import java.util.Arrays;

/**
 * 并查集
 *
 * @author WeiJinglun
 * @date 2021.01.31
 */
class UnionFind {
    // 数组内存放的值为联通分量内的代表点
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
        // 连通区域内较小的,连通较大的
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
