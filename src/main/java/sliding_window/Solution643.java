package sliding_window;

/**
 * https://leetcode-cn.com/problems/maximum-average-subarray-i/
 *
 * @author WeiJinglun
 * @date 2021.02.04
 */
public class Solution643 {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int max = sum;
        for (int i = k; i < n; i++) {
            // 窗口范围: i-k+1 至 i
            sum = sum - nums[i - k] + nums[i];
            max = Math.max(max, sum);
        }
        return max * 1.0 / k;
    }


    public static void main(String[] args) {
        int[] nums = {5};
        int k = 1;

        System.out.println(new Solution643().findMaxAverage(nums, k));
    }
}
