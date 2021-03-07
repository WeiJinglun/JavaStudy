package sort;

/**
 * 冒泡排序
 *
 * @author WeiJinglun
 * @date 2021.03.04
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        //todo 实现冒泡排序








    }








    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 20;
        int[] arr = generateRandomArray(maxSize, maxValue);
        System.out.print("排序前:");
        printArray(arr);
        bubbleSort(arr);
        System.out.print("排序后:");
        printArray(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random()+2)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
