import java.util.*;

public class MergeKSortedArrays {

    // 用來存 heap 的節點資料
    static class Node {
        int value;      // 當前值
        int arrayIndex; // 來自哪個陣列
        int elementIndex; // 該陣列中的位置

        Node(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        
        // 最小堆（依 value 升序）
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));

        // 初始化：每個陣列的第一個元素進 heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Node(arrays[i][0], i, 0));
            }
        }

        // 從 heap 取出最小值，再把該陣列的下一個元素放進 heap
        while (!minHeap.isEmpty()) {
            Node current = minHeap.poll();
            result.add(current.value);

            int nextIndex = current.elementIndex + 1;
            if (nextIndex < arrays[current.arrayIndex].length) {
                minHeap.offer(new Node(arrays[current.arrayIndex][nextIndex], current.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] test1 = { {1, 4, 5}, {1, 3, 4}, {2, 6} };
        System.out.println(mergeKSortedArrays(test1)); // [1, 1, 2, 3, 4, 4, 5, 6]

        int[][] test2 = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        System.out.println(mergeKSortedArrays(test2)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        int[][] test3 = { {1}, {0} };
        System.out.println(mergeKSortedArrays(test3)); // [0, 1]
    }
}
