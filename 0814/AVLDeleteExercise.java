class AVLNode {
    int key, height;
    AVLNode left, right;

    AVLNode(int d) {
        key = d;
        height = 1; // 新節點高度預設為 1
    }
}

public class AVLDeleteExercise {

    // 取得節點高度
    int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    // 更新節點高度
    void updateHeight(AVLNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    // 取得平衡因子
    int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // 右旋
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // 左旋
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // 插入節點（AVL）
    AVLNode insert(AVLNode node, int key) {
        if (node == null)
            return new AVLNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // 重複的值不處理

        updateHeight(node);
        return balance(node, key);
    }

    // 刪除節點（AVL）
    AVLNode deleteNode(AVLNode root, int key) {
        if (root == null)
            return root;

        // 標準 BST 刪除
        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            // 情況 1: 無子節點
            if (root.left == null && root.right == null)
                return null;

            // 情況 2: 只有一個子節點
            else if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // 情況 3: 兩個子節點 -> 找後繼節點替代
            AVLNode successor = minValueNode(root.right);
            root.key = successor.key;
            root.right = deleteNode(root.right, successor.key);
        }

        updateHeight(root);

        // 重新平衡
        return rebalanceAfterDeletion(root);
    }

    // 找最小值節點
    AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // 刪除後重新平衡
    AVLNode rebalanceAfterDeletion(AVLNode node) {
        int balance = getBalance(node);

        // 左重
        if (balance > 1) {
            if (getBalance(node.left) >= 0)
                return rightRotate(node);
            else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        // 右重
        if (balance < -1) {
            if (getBalance(node.right) <= 0)
                return leftRotate(node);
            else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    // 插入後平衡
    AVLNode balance(AVLNode node, int key) {
        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // RR
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 中序列印
    void inOrder(AVLNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.key + " ");
            inOrder(root.right);
        }
    }

    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();
        AVLNode root = null;

        int[] nums = { 10, 20, 30, 40, 50, 25 };
        for (int num : nums)
            root = tree.insert(root, num);

        System.out.print("中序遍歷: ");
        tree.inOrder(root);
        System.out.println();

        // 刪除葉子節點
        root = tree.deleteNode(root, 25);
        System.out.print("刪除 25 後: ");
        tree.inOrder(root);
        System.out.println();

        // 刪除只有一個子節點的節點
        root = tree.deleteNode(root, 40);
        System.out.print("刪除 40 後: ");
        tree.inOrder(root);
        System.out.println();

        // 刪除有兩個子節點的節點
        root = tree.deleteNode(root, 20);
        System.out.print("刪除 20 後: ");
        tree.inOrder(root);
        System.out.println();
    }
}
