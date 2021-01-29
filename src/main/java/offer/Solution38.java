package offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 剑指offer.38
 * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 *
 * @author WeiJinglun
 * @date 2021.01.28
 */
public class Solution38 {
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        List<String> ans = new ArrayList<>();
        process(chars, 0, ans);
        return ans.toArray(new String[0]);
    }

    private void process(char[] chars, int index, List<String> ans) {
        if (index == chars.length - 1) {
            ans.add(String.valueOf(chars));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = index; i < chars.length; i++) {
            // 剪枝
            if (set.contains(chars[i])) {
                continue;
            }
            set.add(chars[i]);
            swap(chars, index, i);
            process(chars, index + 1, ans);
            // 回溯
            swap(chars, index, i);
        }

    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution38().permutation("acc")));
    }
}
