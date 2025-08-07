
public class BSTRangeQuerySystem {

    // 節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 插入節點到 BST 中
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    // 1. 範圍查詢：找出 [min, max] 內的所有節點
    public static void rangeQuery(TreeNode root, int min, int max) {
        if (root == null) return;
        if (root.val > min) rangeQuery(root.left, min, max);
        if (root.val >= min && root.val <= max) System.out.print(root.val + " ");
        if (root.val < max) rangeQuery(root.right, min, max);
    }

    // 2. 範圍計數
    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;
        int count = 0;
        if (root.val >= min && root.val <= max) count = 1;
        return count + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    // 3. 範圍總和
    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;
        int sum = 0;
        if (root.val >= min && root.val <= max) sum = root.val;
        return sum + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    // 4. 最接近查詢：找出最接近 target 的值
    public static int closestValue(TreeNode root, int target) {
        return closestHelper(root, target, root.val);
    }

    private static int closestHelper(TreeNode node, int target, int closest) {
        if (node == null) return closest;
        if (Math.abs(node.val - target) < Math.abs(closest - target)) {
            closest = node.val;
        }
        if (target < node.val) {
            return closestHelper(node.left, target, closest);
        } else if (target > node.val) {
            return closestHelper(node.right, target, closest);
        } else {
            return node.val; // exact match
        }
    }

    // 主程式測試
    public static void main(String[] args) {
        // 建立 BST
        int[] values = {15, 10, 20, 8, 12, 17, 25};
        TreeNode root = null;
        for (int val : values) {
            root = insert(root, val);
        }

        int min = 10, max = 20, target = 13;

        // 測試 1：範圍查詢
        System.out.println("📌 範圍查詢 [" + min + ", " + max + "]：");
        rangeQuery(root, min, max);  // Output: 10 12 15 17 20

        // 測試 2：範圍計數
        System.out.println("\n\n📊 範圍內節點數量: " + rangeCount(root, min, max));

        // 測試 3：範圍總和
        System.out.println("💰 範圍內節點總和: " + rangeSum(root, min, max));

        // 測試 4：最接近查詢
        System.out.println("🎯 最接近 " + target + " 的節點值: " + closestValue(root, target));
    }
}
