package easy;

/**
 * @author WeiJinglun
 * @date 2021.01.28
 */
public class Solution724 {
    public int pivotIndex1(int[] nums) {
        // 前缀和
        int n = nums.length;
        int[] sum = new int[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            sum[i] = (total += nums[i]);
        }
        for (int i = 0; i < n; i++) {
            if ((i - 1 < 0 ? 0 : sum[i - 1]) * 2 + nums[i] == total) {
                return i;
            }
        }
        return -1;
    }

    public int pivotIndex(int[] nums) {
        // 压缩前缀和,只保留最后一位
        int n = nums.length;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 记录之前的总和
        int lSum = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                lSum += nums[i - 1];
            }
            if (lSum == (sum - nums[i] - lSum)) {
                return i;
            }
        }
        return -1;
    }
}
