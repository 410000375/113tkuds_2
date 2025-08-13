import java.util.*;

public class MultiLevelCacheSystem {
    
    class CacheNode {
        int key;
        String value;
        int freq;        // 存取頻率
        int layer;       // 目前所在層級
        long timestamp;  // 最近使用時間，用於 LRU

        CacheNode(int key, String value, int layer, long timestamp) {
            this.key = key;
            this.value = value;
            this.freq = 1;
            this.layer = layer;
            this.timestamp = timestamp;
        }
    }

    private int[] capacities = {2, 5, 10};   // L1, L2, L3
    private int[] costs = {1, 3, 10};
    private Map<Integer, CacheNode> map;
    private List<PriorityQueue<CacheNode>> heaps;
    private long globalTime;

    public MultiLevelCacheSystem() {
        map = new HashMap<>();
        heaps = new ArrayList<>();
        globalTime = 0;

        for (int i = 0; i < 3; i++) {
            // Min Heap：依據 (freq, timestamp) 排序，freq小的先淘汰
            PriorityQueue<CacheNode> heap = new PriorityQueue<>(
                (a, b) -> (a.freq != b.freq) ? a.freq - b.freq : Long.compare(a.timestamp, b.timestamp)
            );
            heaps.add(heap);
        }
    }

    public String get(int key) {
        if (!map.containsKey(key)) return null;

        CacheNode node = map.get(key);
        node.freq++;
        node.timestamp = ++globalTime;

        // 嘗試提升層級
        promote(node);
        return node.value;
    }

    public void put(int key, String value) {
        globalTime++;
        if (map.containsKey(key)) {
            CacheNode node = map.get(key);
            node.value = value;
            node.freq++;
            node.timestamp = globalTime;
            promote(node);
            return;
        }

        // 新節點預設放在 L1
        CacheNode node = new CacheNode(key, value, 0, globalTime);
        map.put(key, node);
        heaps.get(0).offer(node);

        // 檢查 L1 容量
        checkCapacity(0);
    }

    private void promote(CacheNode node) {
        int layer = node.layer;
        if (layer < 2) { // 不是最上層
            PriorityQueue<CacheNode> heap = heaps.get(layer);
            PriorityQueue<CacheNode> nextHeap = heaps.get(layer + 1);

            // 如果該節點 freq 高於 heap 中最小 freq 的節點，升級
            if (!heap.isEmpty() && heap.peek() != node) {
                CacheNode minNode = heap.peek();
                if (node.freq > minNode.freq) {
                    heap.remove(node);
                    node.layer++;
                    heaps.get(layer + 1).offer(node);
                    checkCapacity(layer + 1);
                }
            }
        }
    }

    private void checkCapacity(int layer) {
        PriorityQueue<CacheNode> heap = heaps.get(layer);
        while (heap.size() > capacities[layer]) {
            CacheNode evict = heap.poll();
            evict.layer++;
            if (evict.layer < 3) {
                heaps.get(evict.layer).offer(evict);
            } else {
                // 最下層滿了直接淘汰
                map.remove(evict.key);
            }
        }
    }

    // --- 測試範例 ---
    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C"); // L1 滿，最舊的下放 L2

        System.out.println(cache.get(1)); // "A"
        System.out.println(cache.get(3)); // "C"

        for (int i = 4; i <= 15; i++) {
            cache.put(i, "V" + i);
        }

        System.out.println(cache.get(5));
        System.out.println(cache.get(2));
    }
}
