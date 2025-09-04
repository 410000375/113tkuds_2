class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 建立 dummy 節點，避免刪除 head 特例
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 n+1 步
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // fast 與 slow 一起走
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除 slow.next
        slow.next = slow.next.next;

        return dummy.next;
    }
}
