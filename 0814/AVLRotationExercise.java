// 檔案：AVLRotationExercise.java
class AVLNode {
    int key;
    int height;
    AVLNode left, right;

    AVLNode(int key) {
        this.key = key;
        height = 1; // 新節點高度為 1
    }
}

public class AVLRotationExercise {
    AVLNode root;

    // 取得節點高度
    int getHeight(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    // 計算平衡因子
    int getBalance(AVLNode node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    // 更新節點高度
    void updateHeight(AVLNode node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
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

    // 插入節點 (含旋轉修正)
    AVLNode insert(AVLNode node, int key) {
        if (node == null) return new AVLNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // 不允許重複值

        updateHeight(node);
        int balance = getBalance(node);

        // LL 型
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // RR 型
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // LR 型
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL 型
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 前序遍歷
    void preOrder(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLRotationExercise tree = new AVLRotationExercise();

        // 測試四種旋轉
        System.out.println("測試 LL 型（右旋）:");
        tree.root = null;
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 10); // 觸發右旋
        tree.preOrder(tree.root);
        System.out.println("\n");

        System.out.println("測試 RR 型（左旋）:");
        tree.root = null;
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30); // 觸發左旋
        tree.preOrder(tree.root);
        System.out.println("\n");

        System.out.println("測試 LR 型（左右旋）:");
        tree.root = null;
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20); // 觸發左右旋
        tree.preOrder(tree.root);
        System.out.println("\n");

        System.out.println("測試 RL 型（右左旋）:");
        tree.root = null;
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 20); // 觸發右左旋
        tree.preOrder(tree.root);
        System.out.println();
    }
}
