import java.util.Arrays;

public class ValidMaxHeapChecker {

    // 檢查是否為有效 Max Heap
    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        // 最後一個非葉節點
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // 檢查左子節點
            if (left < n && arr[i] < arr[left]) {
                System.out.println("違反規則：索引 " + left + " 的值 " + arr[left] + " 大於父節點 " + arr[i]);
                return false;
            }

            // 檢查右子節點
            if (right < n && arr[i] < arr[right]) {
                System.out.println("違反規則：索引 " + right + " 的值 " + arr[right] + " 大於父節點 " + arr[i]);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65},
            {100, 90, 80, 95, 60, 75, 65},
            {50},
            {}
        };

        for (int[] test : testCases) {
            System.out.println("測試陣列: " + Arrays.toString(test));
            boolean result = isValidMaxHeap(test);
            System.out.println("是否為有效 Max Heap: " + result);
            System.out.println();
        }
    }
}
