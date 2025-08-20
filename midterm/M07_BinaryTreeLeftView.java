import java.util.*;

public class M07_BinaryTreeLeftView {

    // 樹節點定義
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

            // 左子樹
            if (i < arr.length && arr[i] != -1) {
                cur.left = new TreeNode(arr[i]);
                q.offer(cur.left);
            }
            i++;

            // 右子樹
            if (i < arr.length && arr[i] != -1) {
                cur.right = new TreeNode(arr[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    // BFS 取左視圖
    static List<Integer> leftView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                // 該層第一個節點
                if (i == 0) res.add(cur.val);

                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        TreeNode root = buildTree(arr);
        List<Integer> view = leftView(root);

        System.out.print("LeftView:");
        for (int v : view) {
            System.out.print(" " + v);
        }
        System.out.println();
    }
}

