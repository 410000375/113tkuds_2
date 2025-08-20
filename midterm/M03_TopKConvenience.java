
// 檔名: M03_TopKConvenience.java
import java.util.*;

public class M03_TopKConvenience {
    // 建立一個商品類別，存品名與數量
    static class Item {
        String name;
        int qty;
        Item(String name, int qty) {
            this.name = name;
            this.qty = qty;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 商品數量
        int K = sc.nextInt();   // 要找 Top-K
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            items.add(new Item(name, qty));
        }

        // Min-Heap (數量小的會在堆頂)
        PriorityQueue<Item> minHeap = new PriorityQueue<>(new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) {
                    return a.qty - b.qty; // 數量小的優先
                }
                // 如果銷量相同 → 用字典序決定 (可改成維護輸入順序)
                return a.name.compareTo(b.name);
            }
        });

        // 維護大小為 K 的 Min-Heap
        for (Item it : items) {
            minHeap.offer(it);
            if (minHeap.size() > K) {
                minHeap.poll(); // 移除最小的，保留前 K 大
            }
        }

        // 取出 Heap 中元素（此時順序是從小到大）
        List<Item> result = new ArrayList<>(minHeap);
        // 重新排序 → 高到低
        result.sort(new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                if (b.qty != a.qty) {
                    return b.qty - a.qty; // 數量大優先
                }
                return a.name.compareTo(b.name);
            }
        });

        // 輸出
        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }
    }
}
/*
時間複雜度：
- 將時間字串轉分鐘：O(1)。
- 讀入 n 筆時間並轉換：O(n)。
- 二分搜尋：O(log n)。
- 總時間複雜度：O(n + log n) ≈ O(n)。
- 空間複雜度：O(n)，用來存放輸入的 n 筆時間。
*/