import java.util.*;

class AVLNode {
    int key;
    int height;
    AVLNode left, right;

    AVLNode(int key) {
        this.key = key;
        height = 1;
    }
}

public class AVLRangeQueryExercise {
    private AVLNode root;

    // 取得高度
    private int getHeight(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    // 取得平衡因子
    private int getBalance(AVLNode node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    // 更新節點高度
    private void updateHeight(AVLNode node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    // 右旋
    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    // 左旋
    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    // 插入節點
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private AVLNode insertRec(AVLNode node, int key) {
        if (node == null) return new AVLNode(key);
        if (key < node.key)
            node.left = insertRec(node.left, key);
        else if (key > node.key)
            node.right = insertRec(node.right, key);
        else
            return node; // 不允許重複

        updateHeight(node);
        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key) return rotateRight(node);
        // RR
        if (balance < -1 && key > node.right.key) return rotateLeft(node);
        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // 範圍查詢
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryRec(root, min, max, result);
        return result;
    }

    private void rangeQueryRec(AVLNode node, int min, int max, List<Integer> result) {
        if (node == null) return;

        // 左子樹有可能有符合範圍的值
        if (node.key > min) rangeQueryRec(node.left, min, max, result);

        // 節點本身符合範圍
        if (node.key >= min && node.key <= max) result.add(node.key);

        // 右子樹有可能有符合範圍的值
        if (node.key < max) rangeQueryRec(node.right, min, max, result);
    }

    // 測試
    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();
        int[] nums = {10, 20, 5, 4, 15, 25, 30, 8};
        for (int num : nums) {
            tree.insert(num);
        }

        System.out.println("範圍 [8, 20] 的查詢結果:");
        List<Integer> res = tree.rangeQuery(8, 20);
        System.out.println(res); // 預期: [8, 10, 15, 20]
    }
}
