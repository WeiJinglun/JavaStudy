package leetcode.sliding_window;

/**
 * https://leetcode-cn.com/problems/permutation-in-string/
 *
 * @author WeiJinglun
 * @date 2021.02.10
 */
public class Solution567 {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }
        // 计数字典
        int[] charts = new int[26];
        for (int i = 0; i < n; i++) {
            ++charts[s1.charAt(i) - 'a'];
            --charts[s2.charAt(i) - 'a'];
        }
        int diff = 0;
        for (int chart : charts) {
            if (chart != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }

        // 滑动窗口 [L,R)
        for (int i = n; i < m; i++) {
            int rChart = s2.charAt(i) - 'a';
            int lChart = s2.charAt(i - n) - 'a';
            // 比对进出窗口的字符是否相等
            if (lChart == rChart) {
                continue;
            }
            if (charts[rChart] == 0) {
                diff++;
            }
            ++charts[rChart];
            if (charts[rChart] == 0) {
                diff--;
            }

            if (charts[lChart] == 0) {
                diff++;
            }
            --charts[lChart];
            if (charts[lChart] == 0) {
                diff--;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }
}
