import java.util.*;

public class LC19_RemoveNth_Node_Clinic {
    // 定義鏈結串節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }

        int k = sc.nextInt();
        sc.close();

        ListNode head = removeNthFromEnd(dummy.next, k);

        // 輸出刪除後序列
        cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(" ");
            cur = cur.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        // fast 先走 n+1 步，使 slow 停在要刪前一個
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除 slow.next
        slow.next = slow.next.next;
        return dummy.next;
    }
}
