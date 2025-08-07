import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 1. 找出從根節點到所有葉節點的路徑
    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> path = new ArrayList<>();
        dfs(root, path, res);
        return res;
    }

    private static void dfs(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        path.add(node.val);
        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
        } else {
            if (node.left != null) dfs(node.left, path, res);
            if (node.right != null) dfs(node.right, path, res);
        }
        path.remove(path.size() - 1);
    }

    // 2. 判斷是否存在根到葉路徑的和為 targetSum
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 3. 找出和最大的根到葉路徑和
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE; 
        if (root.left == null && root.right == null) return root.val;
        int leftMax = maxRootToLeafSum(root.left);
        int rightMax = maxRootToLeafSum(root.right);
        return root.val + Math.max(leftMax, rightMax);
    }

    // 4. 計算樹中任意兩節點間的最大路徑和（樹的直徑）
    // 注意這裡路徑可以穿過根且不一定是從根開始或結束
    static int maxPathSumResult = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxPathSumResult = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return maxPathSumResult;
    }

    // 回傳從該節點開始的最大單邊路徑和（只能往下）
    private static int maxPathSumHelper(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathSumHelper(node.left));
        int right = Math.max(0, maxPathSumHelper(node.right));

        int currentMax = node.val + left + right;
        maxPathSumResult = Math.max(maxPathSumResult, currentMax);

        return node.val + Math.max(left, right);
    }

    // 測試主程式
    public static void main(String[] args) {
        /*
                10
               /  \
              5   -3
             / \    \
            3   2    11
           / \   \
          3  -2   1
         */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        // 1. 所有根到葉路徑
        System.out.println("所有根到葉路徑:");
        List<List<Integer>> paths = allRootToLeafPaths(root);
        for (List<Integer> path : paths) {
            System.out.println(path);
        }

        // 2. 是否存在目標和路徑
        int targetSum = 18;
        System.out.println("\n是否存在根到葉路徑和為 " + targetSum + " : " + hasPathSum(root, targetSum));

        // 3. 最大根到葉路徑和
        System.out.println("\n最大根到葉路徑和: " + maxRootToLeafSum(root));

        // 4. 樹的最大路徑和（任意兩節點間）
        System.out.println("\n樹的最大路徑和: " + maxPathSum(root));
    }
}
