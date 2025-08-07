
public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 1. 將BST轉換為排序的雙向鏈結串列（中序走訪）
    static TreeNode prev = null;  // 用於串連雙向鏈結串列
    static TreeNode head = null;  // 鏈結串列頭節點

    public static TreeNode bstToDoublyLinkedList(TreeNode root) {
        prev = null;
        head = null;
        inorderConvert(root);
        return head;
    }

    private static void inorderConvert(TreeNode node) {
        if (node == null) return;
        inorderConvert(node.left);

        if (prev == null) {
            head = node;  // 第一個節點為頭
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;

        inorderConvert(node.right);
    }

    // 2. 將排序陣列轉換為平衡BST
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBSTHelper(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBSTHelper(nums, start, mid - 1);
        node.right = sortedArrayToBSTHelper(nums, mid + 1, end);
        return node;
    }

    // 3. 檢查BST是否平衡，並計算平衡因子（左右子樹高度差）
    // 回傳樹高，平衡狀態透過全域變數或回傳值判斷
    static boolean isBalancedFlag = true;

    public static boolean isBalanced(TreeNode root) {
        isBalancedFlag = true;
        checkHeight(root);
        return isBalancedFlag;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = checkHeight(node.left);
        int rightHeight = checkHeight(node.right);

        int balanceFactor = Math.abs(leftHeight - rightHeight);
        if (balanceFactor > 1) isBalancedFlag = false;

        // 如果想要印出每個節點的平衡因子，可以在這裡加入輸出
        // System.out.println("Node " + node.val + " balance factor: " + balanceFactor);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 4. 將BST中每個節點的值改為所有大於等於該節點值的總和（逆中序走訪）
    static int accSum = 0;

    public static void convertBSTGreaterSum(TreeNode root) {
        accSum = 0;
        reverseInorder(root);
    }

    private static void reverseInorder(TreeNode node) {
        if (node == null) return;
        reverseInorder(node.right);
        accSum += node.val;
        node.val = accSum;
        reverseInorder(node.left);
    }

    // 輔助方法：中序走訪印出
    public static void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    // 測試主程式
    public static void main(String[] args) {
        /*
         建立範例BST:
                5
               / \
              3   7
             / \   \
            2   4   8
         */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(8);

        System.out.println("原BST中序走訪:");
        inorderPrint(root);
        System.out.println();

        // 1. BST轉雙向鏈結串列
        TreeNode head = bstToDoublyLinkedList(root);
        System.out.print("轉成雙向鏈結串列（從head開始）：");
        TreeNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
        System.out.println();

        // 2. 排序陣列轉平衡BST
        int[] sortedArr = {1,2,3,4,5,6,7};
        TreeNode balancedBST = sortedArrayToBST(sortedArr);
        System.out.print("排序陣列轉平衡BST中序：");
        inorderPrint(balancedBST);
        System.out.println();

        // 3. 檢查BST是否平衡
        System.out.println("原BST是否平衡? " + isBalanced(root));
        System.out.println("平衡BST是否平衡? " + isBalanced(balancedBST));

        // 4. 將BST轉換成所有大於等於該節點值的總和版本
        convertBSTGreaterSum(root);
        System.out.print("轉換後BST中序走訪: ");
        inorderPrint(root);
        System.out.println();
    }
}
