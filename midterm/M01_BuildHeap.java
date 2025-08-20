import java.util.*;

public class M01_BuildHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取輸入
        String type = sc.next();  // "max" 或 "min"
        int n = sc.nextInt();
        int[] heap = new int[n];
        for (int i = 0; i < n; i++) {
            heap[i] = sc.nextInt();
        }

        // 建堆
        buildHeap(heap, type);

        // 輸出結果
        for (int i = 0; i < n; i++) {
            System.out.print(heap[i]);
            if (i < n - 1) System.out.print(" ");
        }
    }

    // 建堆（自底向上）
    public static void buildHeap(int[] arr, String type) {
        int n = arr.length;
        // 從最後一個非葉節點開始往上做 heapify
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i, type);
        }
    }

    // heapifyDown：維持堆性質
    public static void heapifyDown(int[] arr, int n, int i, String type) {
        int target = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (type.equals("max")) {
            // Max-Heap：父節點 >= 子節點
            if (left < n && arr[left] > arr[target]) target = left;
            if (right < n && arr[right] > arr[target]) target = right;
        } else {
            // Min-Heap：父節點 <= 子節點
            if (left < n && arr[left] < arr[target]) target = left;
            if (right < n && arr[right] < arr[target]) target = right;
        }

        // 若目標有變，交換並遞迴處理
        if (target != i) {
            int temp = arr[i];
            arr[i] = arr[target];
            arr[target] = temp;

            heapifyDown(arr, n, target, type);
        }
    }
}

/*
時間複雜度：
- buildHeap：O(n)
  理由：heapifyDown 的攤還成本不是 O(log n)，
  而是 ∑ O(height(node)) ≈ O(n)。
  （逐一 insert 需要 O(n log n)，所以不能用。）

空間複雜度：
- O(1)，在原陣列中就地建堆，不需額外記憶體。
*/
