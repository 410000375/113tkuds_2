
public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 1. 驗證是否為有效BST（中序遞迴檢查）
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private static boolean isValidBSTHelper(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) return false;
        return isValidBSTHelper(node.left, min, node.val) && isValidBSTHelper(node.right, node.val, max);
    }

    // 2. 找出不符合規則的節點（錯誤節點）
    // 透過中序遍歷找錯誤節點（兩個錯誤節點交換）
    static TreeNode firstError = null, secondError = null, prevNode = null;

    public static void findErrorNodes(TreeNode root) {
        firstError = null;
        secondError = null;
        prevNode = null;
        inorderFind(root);
    }

    private static void inorderFind(TreeNode node) {
        if (node == null) return;
        inorderFind(node.left);
        if (prevNode != null && prevNode.val > node.val) {
            if (firstError == null) firstError = prevNode;
            secondError = node;
        }
        prevNode = node;
        inorderFind(node.right);
    }

    // 3. 修復錯誤節點交換的BST
    public static void recoverBST(TreeNode root) {
        findErrorNodes(root);
        if (firstError != null && secondError != null) {
            int temp = firstError.val;
            firstError.val = secondError.val;
            secondError.val = temp;
        }
    }

    // 4. 計算需要移除多少節點才能變成有效BST
    // 利用找最大BST子樹大小，整棵樹節點數 - 最大BST子樹節點數 = 最少需移除節點數
    static class BSTInfo {
        boolean isBST;
        int size;
        int min;
        int max;
        BSTInfo(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    public static int minRemovalsToBST(TreeNode root) {
        int totalNodes = countNodes(root);
        int maxBSTSize = largestBSTSubtree(root).size;
        return totalNodes - maxBSTSize;
    }

    private static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private static BSTInfo largestBSTSubtree(TreeNode node) {
        if (node == null) return new BSTInfo(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        BSTInfo left = largestBSTSubtree(node.left);
        BSTInfo right = largestBSTSubtree(node.right);

        if (left.isBST && right.isBST && node.val > left.max && node.val < right.min) {
            int size = left.size + right.size + 1;
            int min = Math.min(left.min, node.val);
            int max = Math.max(right.max, node.val);
            return new BSTInfo(true, size, min, max);
        }
        return new BSTInfo(false, Math.max(left.size, right.size), 0, 0);
    }

    // 測試主程式
    public static void main(String[] args) {
        /*
             範例樹 (節點值錯亂):
                  3
                 / \
                1   4
                   /
                  2
         */

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println("是否為有效BST? " + isValidBST(root)); // false

        findErrorNodes(root);
        System.out.println("錯誤節點值: " + (firstError != null ? firstError.val : "無") + ", " + (secondError != null ? secondError.val : "無"));

        System.out.println("修復BST中...");
        recoverBST(root);
        System.out.println("修復後是否為有效BST? " + isValidBST(root)); // true

        System.out.println("需要移除多少節點才能成為有效BST? " + minRemovalsToBST(root)); // 0 (已修復)

        // 測試一棵非BST
        TreeNode root2 = new TreeNode(10);
        root2.left = new TreeNode(15); // 錯誤節點
        root2.right = new TreeNode(20);

        System.out.println("非BST樹需要移除多少節點? " + minRemovalsToBST(root2));
    }
}
