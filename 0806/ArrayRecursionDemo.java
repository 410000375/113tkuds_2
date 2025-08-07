import java.util.Arrays;

public class ArrayRecursionDemo {
    
    
    public static int arraySum(int[] arr, int index) {
        
        if (index >= arr.length) {
            return 0;
        }
        
        System.out.printf("arraySum(arr, %d) = %d + arraySum(arr, %d)\n", 
                        index, arr[index], index + 1);
        
        
        return arr[index] + arraySum(arr, index + 1);
    }
    
    
    public static int findMax(int[] arr, int index) {
       
        if (index == arr.length - 1) {
            return arr[index];
        }
        
        
        int maxOfRest = findMax(arr, index + 1);
        int currentMax = Math.max(arr[index], maxOfRest);
        
        System.out.printf("比較 arr[%d]=%d 與剩餘最大值 %d，結果：%d\n", 
                        index, arr[index], maxOfRest, currentMax);
        
        return currentMax;
    }
    
   
    public static boolean isSorted(int[] arr, int index) {
        
        if (index >= arr.length - 1) {
            return true;
        }
        
       
        if (arr[index] > arr[index + 1]) {
            return false;
        }
        
        
        return isSorted(arr, index + 1);
    }
    
    
   
    public static void printArray(int[] arr, int index) {
        if (index >= arr.length) {
            System.out.println();
            return;
        }
        
        System.out.print(arr[index] + " ");
        printArray(arr, index + 1);
    }
    
   
    public static void printArrayReverse(int[] arr, int index) {
        if (index >= arr.length) {
            return;
        }
        
        printArrayReverse(arr, index + 1);
        System.out.print(arr[index] + " ");
    }
    
    public static void main(String[] args) {
        int[] numbers = {3, 7, 2, 9, 1, 5};
        int[] sortedNumbers = {1, 3, 5, 7, 9};
        
        System.out.println("測試陣列：" + Arrays.toString(numbers));
        System.out.println();
        
        
        System.out.println("=== 陣列總和 ===");
        int sum = arraySum(numbers, 0);
        System.out.println("總和：" + sum);
        
        System.out.println("\n=== 尋找最大值 ===");
        int max = findMax(numbers, 0);
        System.out.println("最大值：" + max);
        
        System.out.println("\n=== 檢查排序狀態 ===");
        System.out.println("numbers 是否已排序：" + isSorted(numbers, 0));
        System.out.println("sortedNumbers 是否已排序：" + isSorted(sortedNumbers, 0));
        
        System.out.println("\n=== 陣列列印 ===");
        System.out.print("正向列印：");
        printArray(numbers, 0);
        System.out.print("反向列印：");
        printArrayReverse(numbers, 0);
        System.out.println();
    }
}
