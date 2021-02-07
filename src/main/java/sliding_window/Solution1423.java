package sliding_window;

/**
 * https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/
 *
 * @author WeiJinglun
 * @date 2021.02.06
 */
public class Solution1423 {
    //[1,2,3,4,5,6,1]
    //3
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int start = n - k;
        int ans = 0;
        for (int i = start; i < n; i++) {
            ans += cardPoints[i];
        }
        int sum = ans;
        for (int i = 0; i < k; i++) {
            sum = sum + cardPoints[i] - cardPoints[start++];
            ans = Math.max(ans, sum);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1423().maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
    }
}
