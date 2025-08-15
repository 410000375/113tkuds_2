// 檔名：AVLBasicExercise.java

class AVLNode {
    int key;
    AVLNode left, right;
    int height;

    AVLNode(int key) {
        this.key = key;
        this.height = 1; // 新節點高度預設為 1
    }
}

public class AVLBasicExercise {
    private AVLNode root;

    // 取得節點高度
    private int getHeight(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    // 更新節點高度
    private void updateHeight(AVLNode node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    // 計算平衡因子
    private int getBalanceFactor(AVLNode node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    // 插入節點 (標準 BST 插入，暫不做旋轉)
    public void insert(int key) {
        root = insertRecursive(root, key);
    }

    private AVLNode insertRecursive(AVLNode node, int key) {
        if (node == null) {
            return new AVLNode(key);
        }

        if (key < node.key) {
            node.left = insertRecursive(node.left, key);
        } else if (key > node.key) {
            node.right = insertRecursive(node.right, key);
        } else {
            // 重複值不插入
            return node;
        }

        // 更新高度
        updateHeight(node);

        return node;
    }

    // 搜尋節點
    public boolean search(int key) {
        return searchRecursive(root, key);
    }

    private boolean searchRecursive(AVLNode node, int key) {
        if (node == null) return false;
        if (key == node.key) return true;
        if (key < node.key) return searchRecursive(node.left, key);
        return searchRecursive(node.right, key);
    }

    // 計算樹的高度
    public int height() {
        return getHeight(root);
    }

    // 檢查是否為有效 AVL 樹
    public boolean isValidAVL() {
        return isValidAVLRecursive(root);
    }

    private boolean isValidAVLRecursive(AVLNode node) {
        if (node == null) return true;

        int balance = getBalanceFactor(node);
        if (balance < -1 || balance > 1) {
            return false; // 平衡因子超出範圍
        }

        return isValidAVLRecursive(node.left) && isValidAVLRecursive(node.right);
    }

    // 測試程式
    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();

        // 插入測試
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(4);
        tree.insert(15);

        // 搜尋測試
        System.out.println("搜尋 15: " + tree.search(15)); // true
        System.out.println("搜尋 99: " + tree.search(99)); // false

        // 高度
        System.out.println("樹高度: " + tree.height());

        // AVL 驗證
        System.out.println("是否為有效 AVL: " + tree.isValidAVL());
    }
}
