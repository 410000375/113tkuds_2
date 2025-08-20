import java.util.*;

public class M09_AVLValidate {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 根據層序輸入建立二元樹，-1 代表 null
    static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < arr.length && !q.isEmpty()) {
            TreeNode cur = q.poll();
            if (i < arr.length && arr[i] != -1) {
                cur.left = new TreeNode(arr[i]);
                q.offer(cur.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1) {
                cur.right = new TreeNode(arr[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    // 回傳節點高度，如果不平衡或 BST 違反，回傳 -1
    static int checkAVL(TreeNode node, long min, long max) {
        if (node == null) return 0;

        // 檢查 BST
        if (node.val <= min || node.val >= max) return -1;

        int leftHeight = checkAVL(node.left, min, node.val);
        if (leftHeight == -1) return -1;

        int rightHeight = checkAVL(node.right, node.val, max);
        if (rightHeight == -1) return -1;

        // 檢查 AVL 平衡因子
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        TreeNode root = buildTree(arr);

        int result = checkAVL(root, Long.MIN_VALUE, Long.MAX_VALUE);
        if (result == -1) {
            // 再分辨是 BST 還是 AVL
            int bstCheck = checkBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
            if (bstCheck == -1) System.out.println("Invalid BST");
            else System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    // 單純檢查 BST（後續用於分辨錯誤原因）
    static int checkBST(TreeNode node, long min, long max) {
        if (node == null) return 0;
        if (node.val <= min || node.val >= max) return -1;
        int left = checkBST(node.left, min, node.val);
        if (left == -1) return -1;
        int right = checkBST(node.right, node.val, max);
        if (right == -1) return -1;
        return Math.max(left, right) + 1;
    }
}

/*
時間複雜度:
- 建樹：O(n)，每個節點處理一次。
- 檢查 AVL 與 BST：O(n)，每個節點僅訪問一次。
- 總時間複雜度：O(n)
- 空間複雜度：
    - 建樹使用佇列：O(n)
    - 遞迴堆疊：O(h)，最壞 O(n)，平均 O(log n)
*/
