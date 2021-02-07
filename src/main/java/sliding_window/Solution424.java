package sliding_window;

/**
 * https://leetcode-cn.com/problems/longest-repeating-character-replacement/
 *
 * @author WeiJinglun
 * @date 2021.02.02
 */
public class Solution424 {
    // 滑动窗口
    // AABABBA 1
    public int characterReplacement(String s, int k) {
        int[] letter = new int[26];
        int n = s.length();
        int left = 0, right = 0;
        // 窗口内最多的重复字符
        int maxSub = 0;
        while (right < n) {
            maxSub = Math.max(maxSub, ++letter[s.charAt(right) - 'A']);
            if (right - left >= maxSub + k) {
                --letter[s.charAt(left++) - 'A'];
            }
            right++;
        }
        return right - left;
    }
}
