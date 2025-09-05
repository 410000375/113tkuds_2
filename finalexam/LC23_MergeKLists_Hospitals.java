import java.util.*;

public class LC23_MergeKLists_Hospitals {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();

        List<ListNode> lists = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            String line = sc.nextLine().trim();
            if (line.equals("-1")) {
                lists.add(null);
                continue;
            }
            String[] parts = line.split(" ");
            ListNode dummy = new ListNode(0), cur = dummy;
            for (String p : parts) {
                if (p.equals("-1")) break;
                cur.next = new ListNode(Integer.parseInt(p));
                cur = cur.next;
            }
            lists.add(dummy.next);
        }

        sc.close();

        ListNode merged = mergeKLists(lists);

        // 輸出合併後序列
        StringBuilder sb = new StringBuilder();
        while (merged != null) {
            sb.append(merged.val);
            if (merged.next != null) sb.append(" ");
            merged = merged.next;
        }
        System.out.println(sb.toString());
    }

    public static ListNode mergeKLists(List<ListNode> lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0), tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) pq.offer(node.next);
        }

        return dummy.next;
    }
}
