
import java.util.Arrays;


public class Main {


    public static void main(String[] args) {
        System.out.println(Arrays.toString(printOddNumbers(new int[]{1, 2, 3}, new int[]{4, 5, 6})));
    }

    public static int[] printOddNumbers(int[] arr, int[] arr1) {
        int j = 0, k = 0, i = 0;
        int[] finalArr = new int[arr.length + arr1.length];
        while (arr.length > j && arr1.length > k) {
            finalArr[i++] = arr[j] <= arr1[k] ? arr[j++] : arr1[k++];
        }
        while (j < arr.length) {
            finalArr[i++] = arr[j++];
        }
        while (k < arr1.length) {
            finalArr[i++] = arr1[k++];
        }
        return finalArr;
    }
}
