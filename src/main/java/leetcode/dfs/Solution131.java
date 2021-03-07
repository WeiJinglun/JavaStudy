package leetcode.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author WeiJinglun
 * @date 2021.03.07
 */
public class Solution131 {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s.length() == 0) {
            return res;
        }
        char[] chars = s.toCharArray();
        int n = s.length();
        // [i,j] 子串是否为回文
        boolean[][] dp = new boolean[n][n];
        for (int right = 0; right < n; right++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int left = 0; left <= right; left++) {
                if (chars[left] == chars[right] && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
            }
        }

        dfs(chars, 0, new ArrayDeque<String>(), res, dp);
        return res;
    }

    private void dfs(char[] chars, int index, Deque<String> prePath, List<List<String>> res, boolean[][] dp) {
        if (index == chars.length) {
            res.add(new ArrayList<>(prePath));
            return;
        }

        for (int i = index; i < chars.length; ++i) {
            // 校验拆分的子串 [index ,i] 是否为回文
            if (!dp[index][i]) {
                continue;
            }
            prePath.addLast(new String(chars, index, i - index + 1));
            dfs(chars, i + 1, prePath, res, dp);
            prePath.removeLast();
        }
    }

    private void dfs1(char[] chars, int index, Deque<String> prePath, List<List<String>> res) {
        if (index == chars.length) {
            res.add(new ArrayList<>(prePath));
            return;
        }

        for (int i = index; i < chars.length; ++i) {
            // 校验拆分的子串 [index ,i] 是否为回文
            if (!checkPalindrome(chars, index, i)) {
                continue;
            }
            prePath.addLast(new String(chars, index, i - index + 1));
            dfs1(chars, i + 1, prePath, res);
            prePath.removeLast();
        }
    }

    /**
     * 校验 子串 [left, right] 范围是否为回文
     *
     * @param chars
     * @param left
     * @param right
     * @return
     */
    private boolean checkPalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution131().partition("a"));
    }
}
