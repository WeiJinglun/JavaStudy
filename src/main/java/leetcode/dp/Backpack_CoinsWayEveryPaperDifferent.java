package leetcode.dp;


/**
 * 01 背包
 *
 * @author WeiJinglun
 * @date 2021.02.09
 */
public class Backpack_CoinsWayEveryPaperDifferent {

    public static int coinWays(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j] + (j - arr[i] < 0 ? 0 : dp[i + 1][j - arr[i]]);
            }
        }
        return dp[0][aim];
    }


    public static int coinWays1(int[] arr, int aim) {
        return process1(arr, arr.length - 1, aim);
    }

    private static int process1(int[] arr, int i, int aim) {
        if (aim < 0) {
            return 0;
        }
        if (i < 0) {
            return aim == 0 ? 1 : 0;
        }
        // 不要
        int p1 = process1(arr, i - 1, aim);
        // 要
        int p2 = process1(arr, i - 1, aim - arr[i]);
        return p1 + p2;
    }


    /**
     * 答案
     */
    public static int dp(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : 0);
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
