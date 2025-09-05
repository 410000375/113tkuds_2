import java.util.*;

public class LC24_SwapPairs_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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

        ListNode swapped = swapPairs(dummy.next);

        // 輸出
        StringBuilder sb = new StringBuilder();
        while (swapped != null) {
            sb.append(swapped.val);
            if (swapped.next != null) sb.append(" ");
            swapped = swapped.next;
        }
        System.out.println(sb.toString());
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 交換
            prev.next = b;
            a.next = b.next;
            b.next = a;

            // 移動 prev
            prev = a;
        }

        return dummy.next;
    }
}
