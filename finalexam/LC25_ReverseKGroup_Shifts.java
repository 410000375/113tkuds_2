import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
        String line = sc.nextLine().trim();
        sc.close();

        if (line.isEmpty()) {
            System.out.println();
            return;
        }

        String[] parts = line.split(" ");
        ListNode dummy = new ListNode(0), cur = dummy;
        for (String p : parts) {
            cur.next = new ListNode(Integer.parseInt(p));
            cur = cur.next;
        }

        ListNode reversed = reverseKGroup(dummy.next, k);

        StringBuilder sb = new StringBuilder();
        while (reversed != null) {
            sb.append(reversed.val);
            if (reversed.next != null) sb.append(" ");
            reversed = reversed.next;
        }
        System.out.println(sb.toString());
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (true) {
            ListNode node = prev;
            // 檢查是否還有 k 個節點
            for (int i = 0; i < k && node != null; i++) node = node.next;
            if (node == null) break;

            ListNode start = prev.next;
            ListNode end = node.next;
            // 反轉 start 到 node
            ListNode newHead = reverse(start, node);
            prev.next = newHead;
            start.next = end;
            prev = start;
        }

        return dummy.next;
    }

    private static ListNode reverse(ListNode start, ListNode end) {
        ListNode prev = null, curr = start;
        ListNode stop = end.next;
        while (curr != stop) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev; // 新頭節點
    }
}
