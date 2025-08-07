import java.util.*;

public class BinaryTreeBasicOperations {

    // 二元樹節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 計算總和與平均值
    static class SumResult {
        int sum = 0;
        int count = 0;
    }

    public static SumResult sumAndCount(TreeNode root) {
        SumResult res = new SumResult();
        sumHelper(root, res);
        return res;
    }

    private static void sumHelper(TreeNode node, SumResult res) {
        if (node == null) return;
        res.sum += node.val;
        res.count++;
        sumHelper(node.left, res);
        sumHelper(node.right, res);
    }

    // 2. 找最大值與最小值
    public static int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(findMax(root.left), findMax(root.right)));
    }

    public static int findMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(findMin(root.left), findMin(root.right)));
    }

    // 3. 計算最大寬度（每層最多節點數）
    public static int maxWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int max = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            max = Math.max(max, levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }

        return max;
    }

    // 4. 判斷是否為完全二元樹
    public static boolean isComplete(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean mustBeLeaf = false;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                if (mustBeLeaf) return false;
                queue.offer(node.left);
            } else {
                mustBeLeaf = true;
            }

            if (node.right != null) {
                if (mustBeLeaf) return false;
                queue.offer(node.right);
            } else {
                mustBeLeaf = true;
            }
        }

        return true;
    }

    // 測試程式
    public static void main(String[] args) {
        /*
                  10
                 /  \
                5    15
               / \     \
              3   7     20
         */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(20);

        // 1. 總和與平均值
        SumResult result = sumAndCount(root);
        System.out.println("總和: " + result.sum);
        System.out.println("平均值: " + (double) result.sum / result.count);

        // 2. 最大值與最小值
        System.out.println("最大值: " + findMax(root));
        System.out.println("最小值: " + findMin(root));

        // 3. 最大寬度
        System.out.println("樹的最大寬度: " + maxWidth(root));

        // 4. 是否為完全二元樹
        System.out.println("是否為完全二元樹: " + isComplete(root));
    }
}
