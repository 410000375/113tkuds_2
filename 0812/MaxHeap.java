import java.util.*;

public class MaxHeap {
    // 使用 ArrayList 作為底層儲存結構
    // ArrayList 是動態陣列，可以自動調整大小
    private List<Integer> heap;
    
    // 建構子：初始化空的 heap
    public MaxHeap() {
        heap = new ArrayList<>();
    }
    
    // 計算父節點索引的輔助方法
    // 輸入：子節點的索引 i
    // 輸出：父節點的索引
    // 公式：(i-1)/2，整數除法會自動捨去小數
    private int parent(int i) { 
        return (i - 1) / 2; 
    }
    
    // 計算左子節點索引
    // 公式：2*i+1
    private int leftChild(int i) { 
        return 2 * i + 1; 
    }
    
    // 計算右子節點索引  
    // 公式：2*i+2
    private int rightChild(int i) { 
        return 2 * i + 2; 
    }
    
    // 向上調整：維護堆積性質
    // 當插入新元素時，可能破壞 Max Heap 性質
    // 需要將新元素與父節點比較，必要時進行交換
    private void heapifyUp(int i) {
        // 循環條件：
        // 1. i > 0：確保不是根節點（根節點沒有父節點）
        // 2. heap.get(i) > heap.get(parent(i))：子節點值大於父節點值
        while (i > 0 && heap.get(i) > heap.get(parent(i))) {
            // 交換當前節點與父節點的值
            Collections.swap(heap, i, parent(i));
            // 更新索引，繼續向上檢查
            i = parent(i);
        }
    }
    
    // 向下調整：維護堆積性質
    // 當移除根節點後，需要重新建立堆積性質
    private void heapifyDown(int i) {
        // 假設當前節點就是最大值節點
        int maxIdx = i;
        // 計算左右子節點索引
        int left = leftChild(i);
        int right = rightChild(i);
        
        // 檢查左子節點是否存在且值更大
        // left < heap.size() 確保索引在有效範圍內
        if (left < heap.size() && heap.get(left) > heap.get(maxIdx)) {
            maxIdx = left;  // 更新最大值節點索引
        }
        
        // 檢查右子節點是否存在且值更大
        if (right < heap.size() && heap.get(right) > heap.get(maxIdx)) {
            maxIdx = right;  // 更新最大值節點索引
        }
        
      
        if (maxIdx != i) {
          
            Collections.swap(heap, i, maxIdx);
          
            heapifyDown(maxIdx);
        }
    }
    
  
    public void insert(int value) {
       
        heap.add(value);
        
        heapifyUp(heap.size() - 1);
    }
    
    
    public int extractMax() {
        
        if (heap.isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        
        
        int max = heap.get(0);
      
        heap.set(0, heap.get(heap.size() - 1));
       
        heap.remove(heap.size() - 1);
        
       
        if (!heap.isEmpty()) {
            heapifyDown(0);
        }
        
        return max; 
    }
    
   
    public int peek() {
        if (heap.isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        return heap.get(0);  // 根節點就是最大值
    }
    
   
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
   
    public int size() {
        return heap.size();
    }
    
    
    public void display() {
        System.out.println("Heap 內容: " + heap);
    }
    
    
    public static void main(String[] args) {
       
        MaxHeap maxHeap = new MaxHeap();
        
        System.out.println("=== Max Heap 操作示範 ===");
        
      
        int[] values = {10, 20, 15, 30, 40};
        
  
        for (int value : values) {
            maxHeap.insert(value);
            System.out.println("插入 " + value + " 後: " + maxHeap.heap);
        }
        
       
        System.out.println("\n當前最大值: " + maxHeap.peek());
        
       
        System.out.println("\n依序取出最大值:");
        while (!maxHeap.isEmpty()) {
            int max = maxHeap.extractMax();
            System.out.println("取出: " + max + ", 剩餘: " + maxHeap.heap);
        }
    }
}
