class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 建立一個 dummy 頭節點方便操作
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // 逐一比較兩個串列
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // 把剩下的串列接上
        if (list1 != null) current.next = list1;
        if (list2 != null) current.next = list2;

        return dummy.next;
    }
}
