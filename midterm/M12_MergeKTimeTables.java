import java.util.*;

public class M12_MergeKTimeTables {

    static class Node implements Comparable<Node> {
        int time;       // 到站時間（分鐘）
        int listIdx;    // 來源列表索引
        int elemIdx;    // 在該列表中的索引

        Node(int t, int l, int e) {
            time = t;
            listIdx = l;
            elemIdx = e;
        }

        public int compareTo(Node other) {
            return this.time - other.time; // Min-Heap
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < len; j++) tmp.add(sc.nextInt());
            lists.add(tmp);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 初始化，每個列表放入第一個元素
        for (int i = 0; i < K; i++) {
            if (!lists.get(i).isEmpty()) {
                pq.offer(new Node(lists.get(i).get(0), i, 0));
            }
        }

        List<Integer> merged = new ArrayList<>();

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            merged.add(cur.time);

            int nextIdx = cur.elemIdx + 1;
            if (nextIdx < lists.get(cur.listIdx).size()) {
                pq.offer(new Node(lists.get(cur.listIdx).get(nextIdx), cur.listIdx, nextIdx));
            }
        }

        // 輸出
        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i) + (i == merged.size()-1 ? "\n" : " "));
        }
    }
}

