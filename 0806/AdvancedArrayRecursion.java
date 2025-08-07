public class AdvancedArrayRecursion {

    // 1. 遞迴實作快速排序
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 2. 遞迴合併兩個已排序的陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        return mergeRecursive(a, 0, b, 0);
    }

    private static int[] mergeRecursive(int[] a, int i, int[] b, int j) {
        if (i == a.length) {
            int[] result = new int[b.length - j];
            System.arraycopy(b, j, result, 0, result.length);
            return result;
        }
        if (j == b.length) {
            int[] result = new int[a.length - i];
            System.arraycopy(a, i, result, 0, result.length);
            return result;
        }

        if (a[i] < b[j]) {
            int[] rest = mergeRecursive(a, i + 1, b, j);
            int[] result = new int[rest.length + 1];
            result[0] = a[i];
            System.arraycopy(rest, 0, result, 1, rest.length);
            return result;
        } else {
            int[] rest = mergeRecursive(a, i, b, j + 1);
            int[] result = new int[rest.length + 1];
            result[0] = b[j];
            System.arraycopy(rest, 0, result, 1, rest.length);
            return result;
        }
    }

    // 3. 遞迴尋找第 k 小元素（QuickSelect）
    public static int kthSmallest(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    private static int quickSelect(int[] arr, int low, int high, int k) {
        if (low == high) return arr[low];

        int pivotIndex = partition(arr, low, high);

        if (k == pivotIndex) return arr[k];
        else if (k < pivotIndex) return quickSelect(arr, low, pivotIndex - 1, k);
        else return quickSelect(arr, pivotIndex + 1, high, k);
    }

    // 4. 遞迴檢查是否存在子序列總和為 target
    public static boolean subsetSum(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index == arr.length) return false;

        return subsetSum(arr, index + 1, target - arr[index]) || subsetSum(arr, index + 1, target);
    }

    // 主程式測試
    public static void main(String[] args) {
        // 測試快速排序
        int[] arr = {3, 6, 1, 5, 2};
        quickSort(arr, 0, arr.length - 1);
        System.out.print("QuickSort: ");
        for (int n : arr) System.out.print(n + " ");
        System.out.println();

        // 測試合併
        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6};
        int[] merged = mergeSortedArrays(a, b);
        System.out.print("Merged: ");
        for (int n : merged) System.out.print(n + " ");
        System.out.println();

        // 測試第 k 小元素
        int[] c = {7, 10, 4, 3, 20, 15};
        System.out.println("3rd smallest: " + kthSmallest(c, 3)); // Output: 7

        // 測試子序列總和
        int[] nums = {3, 34, 4, 12, 5, 2};
        System.out.println("Subset sum to 9? " + subsetSum(nums, 0, 9)); // true
        System.out.println("Subset sum to 30? " + subsetSum(nums, 0, 30)); // false
    }
}
