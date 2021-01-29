package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/sort-items-by-groups-respecting-dependencies/
 *
 * @author WeiJinglun
 * @date 2021.01.27
 */
public class Solution1203 {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // 记录每个组内的项目
        List<List<Integer>> groupItem = new ArrayList<>();
        for (int i = 0; i < m + n; i++) {
            groupItem.add(new ArrayList<>());
        }
        // 整理 group,将 -1 转换为 m+
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m + i;
            }
            groupItem.get(group[i]).add(i);
        }

        // 记录每个组内项目的总入度和
        int[] groupDegree = new int[m + n];
        // 记录组内项目之间的入度
        int[] itemDegree = new int[n];
        // 记录组与组之间的以关系图
        List<List<Integer>> groupGraph = new ArrayList<>();
        for (int i = 0; i < m + n; i++) {
            groupGraph.add(new ArrayList<>());
        }
        // 记录组内项目依赖关系图
        List<List<Integer>> itemGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            // 查看依赖的项目是否为同组
            for (Integer item : beforeItems.get(i)) {
                if (group[i] == group[item]) {
                    // 组内项目依赖
                    ++itemDegree[i];
                    itemGraph.get(item).add(i);
                } else {
                    // 组之间的依赖
                    ++groupDegree[group[i]];
                    groupGraph.get(group[item]).add(group[i]);
                }
            }
        }

        // 先对组进行拓扑排序
        List<Integer> groupId = new ArrayList<>();
        for (int i = 0; i < m + n; i++) {
            groupId.add(i);
        }
        List<Integer> groupTopSort = topSort(groupDegree, groupGraph, groupId);
        if (groupTopSort.size() == 0) {
            return new int[0];
        }
        // 返回结果集
        int[] ans = new int[n];
        int index = 0;
        // 根据组排序,组内元素
        for (Integer g : groupTopSort) {
            List<Integer> itemId = groupItem.get(g);
            if (itemId.size() == 0) {
                continue;
            }
            List<Integer> itemRes = topSort(itemDegree, itemGraph, itemId);
            if (itemRes.size() == 0) {
                return new int[0];
            }
            for (Integer item : itemRes) {
                ans[index++] = item;
            }
        }
        return ans;
    }

    /**
     * @param degree 各点入度
     * @param graph  各点后续点
     * @param id     要排序的点
     * @return 拓扑排序
     */
    private List<Integer> topSort(int[] degree, List<List<Integer>> graph, List<Integer> id) {
        Queue<Integer> queue = new LinkedList<>();
        for (Integer i : id) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            res.add(poll);
            for (Integer v : graph.get(poll)) {
                if (--degree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return res.size() == id.size() ? res : new ArrayList<>();
    }

}
