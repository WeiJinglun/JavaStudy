package leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/fair-candy-swap/comments/
 *
 * @author WeiJinglun
 * @date 2021.02.01
 */
public class Solution888 {
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int remainder = (sumA - sumB) >> 1;
        Set<Integer> numSet = new HashSet<>();
        for (int a : A) {
            numSet.add(a);
        }
        int[] ans = new int[2];
        for (int b : B) {
            int a = b + remainder;
            if (numSet.contains(a)) {
                ans[0] = a;
                ans[1] = b;
            }
        }
        return ans;
    }
}
