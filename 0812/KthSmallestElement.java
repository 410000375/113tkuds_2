import java.util.*;

public class KthSmallestElement {

    // 方法1：Max Heap 大小為 K
    public static int kthSmallest_MaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 移除最大值
            }
        }
        return maxHeap.peek();
    }

    // 方法2：Min Heap 提取 K 次
    public static int kthSmallest_MinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
        }
        int result = -1;
        for (int i = 0; i < k; i++) {
            result = minHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 10, 4, 3, 20, 15};
        int k1 = 3;
        System.out.println("MaxHeap 方法: " + kthSmallest_MaxHeap(arr1, k1));
        System.out.println("MinHeap 方法: " + kthSmallest_MinHeap(arr1, k1));

        int[] arr2 = {1};
        int k2 = 1;
        System.out.println("MaxHeap 方法: " + kthSmallest_MaxHeap(arr2, k2));
        System.out.println("MinHeap 方法: " + kthSmallest_MinHeap(arr2, k2));

        int[] arr3 = {3, 1, 4, 1, 5, 9, 2, 6};
        int k3 = 4;
        System.out.println("MaxHeap 方法: " + kthSmallest_MaxHeap(arr3, k3));
        System.out.println("MinHeap 方法: " + kthSmallest_MinHeap(arr3, k3));
    }
}
