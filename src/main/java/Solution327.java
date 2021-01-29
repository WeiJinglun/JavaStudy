/**
 * https://leetcode-cn.com/problems/count-of-range-sum/
 *
 * @author WeiJinglun
 * @date 2020.12.23
 */
public class Solution327 {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long s = 0L;
        int length = nums.length;
        long[] preSum = new long[length];
        for (int i = 0; i < length; i++) {
            s += nums[i];
            preSum[i] = s;
        }

        return countRangeSumRecursive(preSum, 0, length - 1, lower, upper);
    }

    private int countRangeSumRecursive(long[] preSum, int left, int right, int lower, int upper) {
        if (left == right) {
            // 直接比较前缀和大小
            if (preSum[left] >= lower && preSum[left] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }
        // 归并求左右分支,以及 merge 操作结果,求和返回
        int mid = left + ((right - left) >> 1);
        int leftSum = countRangeSumRecursive(preSum, left, mid, lower, upper);
        int rightSum = countRangeSumRecursive(preSum, mid + 1, right, lower, upper);
        int merge = merge(preSum, left, mid, right, lower, upper);
        return leftSum + rightSum + merge;
    }

    private int merge(long[] preSum, int left, int mid, int right, int lower, int upper) {
        // 判断前缀树的区间差值
        int result = 0;
        // 比对以右区间为结尾的原数组区间,是否符合范围
        int l = left, r = left;
        // 右区间的遍历指针
        int m = mid + 1;

        while (m <= right) {
            long min = preSum[m] - upper;
            long max = preSum[m] - lower;

            while (l <= mid && preSum[l] < min) {
                l++;
            }

            while (r <= mid && preSum[r] <= max) {
                r++;
            }

            result += (r - l);
            m++;
        }

        // 合并操作
        long[] res = new long[right - left + 1];
        // 两个指针
        int L = left, R = mid + 1;
        // 辅助数组指针
        int i = 0;
        while (L <= mid || R <= right) {
            if (L > mid) {
                // 左边数组空
                res[i++] = preSum[R++];
            } else if (R > right) {
                // 右边数组空
                res[i++] = preSum[L++];
            } else {
                // 比较大小
                if (preSum[L] < preSum[R]) {
                    res[i++] = preSum[L++];
                } else {
                    res[i++] = preSum[R++];
                }
            }
        }
        // 拷贝回原数组
        for (i = 0; i < res.length; i++) {
            preSum[left + i] = res[i];
        }
        return result;
    }


}
