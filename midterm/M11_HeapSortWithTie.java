import java.util.*;

public class M11_HeapSortWithTie {

    static class Node implements Comparable<Node> {
        int score;
        int index;
        Node(int s, int i) { score = s; index = i; }

        // Max-Heap 比較：先比分數，再比 index
        public int compareTo(Node other) {
            if (this.score != other.score) return other.score - this.score; // 分數大在前
            return this.index - other.index; // 分數相同，索引小在前
        }
    }

    // 將陣列建堆
    static void heapify(Node[] arr, int n, int i) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < n && arr[left].compareTo(arr[largest]) < 0) largest = left;
        if (right < n && arr[right].compareTo(arr[largest]) < 0) largest = right;

        if (largest != i) {
            Node temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    static void heapSort(Node[] arr) {
        int n = arr.length;

        // Build-Heap
        for (int i = n/2 - 1; i >= 0; i--) heapify(arr, n, i);

        // Heap Sort
        for (int i = n-1; i > 0; i--) {
            Node temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Node[] arr = new Node[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Node(sc.nextInt(), i);
        }

        heapSort(arr);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score + (i == n-1 ? "\n" : " "));
        }
    }
}

/*
時間複雜度:
- Build-Heap：O(n)
- Heap Sort 排序：每次取出堆頂需 heapify O(log n)，共 n-1 次 → O(n log n)
- 總時間複雜度：O(n log n)
- 空間複雜度：O(n)，儲存 Node 封裝元素
- 平手規則處理：比較函式已將 index 做穩定處理，不影響時間複雜度
*/
