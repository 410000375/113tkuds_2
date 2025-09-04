class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0); // 建立虛擬節點方便操作
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;

            // 交換兩個節點
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // 移動 prev 指標到下一對
            prev = first;
        }

        return dummy.next;
    }
}
