package leetcode.contest;

/**
 * @author WeiJinglun
 * @date 2021.02.28
 */
public class Solution1 {
    public boolean checkOnesSegment(String s) {
        boolean flag = false;// 前面数是否为 1
        int change = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                change++;
                if (flag) {
                    return true;
                } else {
                    flag = true;
                }
            } else {
                flag = false;
            }
        }
        return change == 1;
    }
}
