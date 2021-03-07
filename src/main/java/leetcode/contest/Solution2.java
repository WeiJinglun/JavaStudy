package leetcode.contest;

/**
 * @author WeiJinglun
 * @date 2021.02.28
 */
public class Solution2 {
    public int minElements(int[] nums, int limit, int goal) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int sub = Math.abs(goal - sum);
        return (sub + (limit - 1)) / limit;
    }
}
