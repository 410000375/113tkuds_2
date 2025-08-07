import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 1. 根據前序 + 中序重建二元樹
    public static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) return null;
        Map<Integer, Integer> inIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inIndexMap.put(inorder[i], i);
        return buildPreInHelper(preorder, 0, preorder.length - 1,
                                inorder, 0, inorder.length - 1,
                                inIndexMap);
    }

    private static TreeNode buildPreInHelper(int[] preorder, int preStart, int preEnd,
                                             int[] inorder, int inStart, int inEnd,
                                             Map<Integer, Integer> inIndexMap) {
        if (preStart > preEnd || inStart > inEnd) return null;

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int inRootIndex = inIndexMap.get(rootVal);
        int leftTreeSize = inRootIndex - inStart;

        root.left = buildPreInHelper(preorder, preStart + 1, preStart + leftTreeSize,
                                     inorder, inStart, inRootIndex - 1, inIndexMap);
        root.right = buildPreInHelper(preorder, preStart + leftTreeSize + 1, preEnd,
                                      inorder, inRootIndex + 1, inEnd, inIndexMap);
        return root;
    }

    // 2. 根據後序 + 中序重建二元樹
    public static TreeNode buildTreePostIn(int[] postorder, int[] inorder) {
        if (postorder == null || inorder == null || postorder.length != inorder.length) return null;
        Map<Integer, Integer> inIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inIndexMap.put(inorder[i], i);
        return buildPostInHelper(postorder, 0, postorder.length - 1,
                                 inorder, 0, inorder.length - 1,
                                 inIndexMap);
    }

    private static TreeNode buildPostInHelper(int[] postorder, int postStart, int postEnd,
                                              int[] inorder, int inStart, int inEnd,
                                              Map<Integer, Integer> inIndexMap) {
        if (postStart > postEnd || inStart > inEnd) return null;

        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        int inRootIndex = inIndexMap.get(rootVal);
        int leftTreeSize = inRootIndex - inStart;

        root.left = buildPostInHelper(postorder, postStart, postStart + leftTreeSize - 1,
                                      inorder, inStart, inRootIndex - 1, inIndexMap);
        root.right = buildPostInHelper(postorder, postStart + leftTreeSize, postEnd - 1,
                                       inorder, inRootIndex + 1, inEnd, inIndexMap);
        return root;
    }

    // 3. 根據層序走訪重建完全二元樹
    public static TreeNode buildTreeFromLevelOrder(Integer[] levelOrder) {
        if (levelOrder == null || levelOrder.length == 0) return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode current = queue.poll();
            if (levelOrder[i] != null) {
                current.left = new TreeNode(levelOrder[i]);
                queue.offer(current.left);
            }
            i++;
            if (i < levelOrder.length && levelOrder[i] != null) {
                current.right = new TreeNode(levelOrder[i]);
                queue.offer(current.right);
            }
            i++;
        }
        return root;
    }

    // 4. 驗證兩棵樹是否相同（用於驗證重建樹）
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // 輔助方法：中序走訪，輸出結果
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderHelper(root, res);
        return res;
    }

    private static void inorderHelper(TreeNode node, List<Integer> res) {
        if (node == null) return;
        inorderHelper(node.left, res);
        res.add(node.val);
        inorderHelper(node.right, res);
    }

    // 測試主程式
    public static void main(String[] args) {
        // 範例資料
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        Integer[] levelOrder = {3,9,20,null,null,15,7};

        // 1. 前序+中序重建
        TreeNode treeFromPreIn = buildTreePreIn(preorder, inorder);
        System.out.println("前序+中序重建的中序走訪: " + inorderTraversal(treeFromPreIn));

        // 2. 後序+中序重建
        TreeNode treeFromPostIn = buildTreePostIn(postorder, inorder);
        System.out.println("後序+中序重建的中序走訪: " + inorderTraversal(treeFromPostIn));

        // 3. 層序走訪重建完全二元樹
        TreeNode treeFromLevel = buildTreeFromLevelOrder(levelOrder);
        System.out.println("層序重建的中序走訪: " + inorderTraversal(treeFromLevel));

        // 4. 驗證重建是否正確（以前序+中序重建的樹和後序+中序重建的樹比較）
        System.out.println("前序+中序重建和後序+中序重建是否相同: " + isSameTree(treeFromPreIn, treeFromPostIn));
    }
}
