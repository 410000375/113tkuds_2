import java.util.*;

public class PersistentAVLExercise {

    // 不可變節點
    static class Node {
        final int key;
        final Node left, right;
        final int height;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = Math.max(height(left), height(right)) + 1;
        }
    }

    private List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null); // 版本0: 空樹
    }

    private static int height(Node n) {
        return n == null ? 0 : n.height;
    }

    private static int getBalance(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    // 左旋
    private static Node rotateLeft(Node y) {
        Node x = y.right;
        Node t2 = x.left;
        return new Node(x.key, new Node(y.key, y.left, t2), x.right);
    }

    // 右旋
    private static Node rotateRight(Node x) {
        Node y = x.left;
        Node t2 = y.right;
        return new Node(y.key, y.left, new Node(x.key, t2, x.right));
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key, null, null);

        if (key < node.key) {
            node = new Node(node.key, insert(node.left, key), node.right);
        } else if (key > node.key) {
            node = new Node(node.key, node.left, insert(node.right, key));
        } else {
            return node; // 重複值不插入
        }

        int balance = getBalance(node);

        // 左左
        if (balance > 1 && key < node.left.key)
            return rotateRight(node);
        // 右右
        if (balance < -1 && key > node.right.key)
            return rotateLeft(node);
        // 左右
        if (balance > 1 && key > node.left.key)
            return rotateLeft(new Node(node.key, rotateLeft(node.left), node.right));
        // 右左
        if (balance < -1 && key < node.right.key)
            return rotateRight(new Node(node.key, node.left, rotateRight(node.right)));

        return node;
    }

    // 建立新版本
    public void insert(int versionId, int key) {
        Node root = versions.get(versionId);
        Node newRoot = insert(root, key);
        versions.add(newRoot);
    }

    public void printInOrder(int versionId) {
        printInOrder(versions.get(versionId));
        System.out.println();
    }

    private void printInOrder(Node node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.key + " ");
        printInOrder(node.right);
    }

    // 測試
    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();

        tree.insert(0, 10); // v1
        tree.insert(1, 20); // v2
        tree.insert(2, 5);  // v3

        System.out.print("版本0: "); tree.printInOrder(0);
        System.out.print("版本1: "); tree.printInOrder(1);
        System.out.print("版本2: "); tree.printInOrder(2);
        System.out.print("版本3: "); tree.printInOrder(3);
    }
}
