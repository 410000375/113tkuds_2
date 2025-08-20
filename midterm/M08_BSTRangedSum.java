import java.util.*;

public class M08_BSTRangedSum {

    // 樹節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 根據層序輸入建立 BST（-1 表 null）
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

    // 遞迴計算 BST 區間總和
    static int rangeSumBST(TreeNode node, int L, int R) {
        if (node == null) return 0;

        if (node.val < L) {
            // 節點小於 L，只需走右子樹
            return rangeSumBST(node.right, L, R);
        } else if (node.val > R) {
            // 節點大於 R，只需走左子樹
            return rangeSumBST(node.left, L, R);
        } else {
            // 節點在區間內，加上左右子樹
            return node.val + rangeSumBST(node.left, L, R) + rangeSumBST(node.right, L, R);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int L = sc.nextInt();
        int R = sc.nextInt();

        TreeNode root = buildTree(arr);
        int sum = rangeSumBST(root, L, R);
        System.out.println("Sum: " + sum);
    }
}

