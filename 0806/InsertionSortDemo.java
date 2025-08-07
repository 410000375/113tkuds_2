import java.util.Arrays;

public class InsertionSortDemo {
    
    public static void insertionSort(int[] array) {
        int n = array.length;
        int totalComparisons = 0;
        int totalShifts = 0;
        
        System.out.println("插入排序過程：");
        System.out.println("已排序部分 | 未排序部分");
        System.out.println("------------|------------");
        
        for (int i = 1; i < n; i++) {
            int key = array[i];  
            int j = i - 1;       
            
            System.out.printf("\n第 %d 步：插入元素 %d\n", i, key);
            
           
            System.out.print("插入前：");
            for (int k = 0; k < i; k++) {
                System.out.print(array[k] + " ");
            }
            System.out.print("| ");
            for (int k = i; k < n; k++) {
                if (k == i) {
                    System.out.print("[" + array[k] + "] ");
                } else {
                    System.out.print(array[k] + " ");
                }
            }
            System.out.println();
            
            
            while (j >= 0 && array[j] > key) {
                totalComparisons++;
                System.out.printf("  比較 %d > %d，將 %d 向右移動\n", 
                                array[j], key, array[j]);
                array[j + 1] = array[j];
                totalShifts++;
                j--;
            }
            
            if (j >= 0) {
                totalComparisons++;  
            }
            
            
            array[j + 1] = key;
            System.out.printf("  將 %d 插入到位置 %d\n", key, j + 1);
            
            
            System.out.print("插入後：");
            for (int k = 0; k <= i; k++) {
                System.out.print(array[k] + " ");
            }
            System.out.print("| ");
            for (int k = i + 1; k < n; k++) {
                System.out.print(array[k] + " ");
            }
            System.out.println();
        }
        
        System.out.printf("\n排序完成！總比較次數：%d，總移動次數：%d\n", 
                        totalComparisons, totalShifts);
    }
    
    public static void main(String[] args) {
        int[] numbers = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("原始陣列：" + Arrays.toString(numbers));
        
        insertionSort(numbers);
        
        System.out.println("最終結果：" + Arrays.toString(numbers));
    }
}
