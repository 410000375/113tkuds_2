public class TreeMirrorAndSymmetry {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 判斷是否為對稱樹
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    // 2. 將樹轉為鏡像樹（直接修改原樹）
    public static TreeNode mirror(TreeNode root) {
        if (root == null) return null;
        TreeNode leftMirror = mirror(root.left);
        TreeNode rightMirror = mirror(root.right);
        root.left = rightMirror;
        root.right = leftMirror;
        return root;
    }

    // 3. 比較兩棵樹是否互為鏡像
    public static boolean areMirrors(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;
        return areMirrors(root1.left, root2.right) && areMirrors(root1.right, root2.left);
    }

    // 4. 檢查一棵樹是否為另一棵樹的子樹
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true;  // 空樹是任何樹的子樹
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // 測試主程式
    public static void main(String[] args) {
        /*
                1
               / \
              2   2
             /     \
            3       3
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);

        System.out.println("是否對稱樹: " + isSymmetric(root));  // true

        // 測試鏡像樹
        TreeNode mirrorRoot = mirror(root);
        System.out.println("鏡像後樹是否對稱: " + isSymmetric(mirrorRoot)); // true

        // 建立另一棵鏡像樹
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.left = new TreeNode(3);

        System.out.println("兩棵樹是否互為鏡像: " + areMirrors(mirrorRoot, root2));  // true

        // 檢查子樹
        TreeNode subRoot = new TreeNode(2);
        subRoot.left = new TreeNode(3);
        System.out.println("是否為子樹: " + isSubtree(mirrorRoot, subRoot));  // true
    }
}
