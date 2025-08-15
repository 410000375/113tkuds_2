// 完整 AVL Tree 實作
class AVLNode {
    int data;
    AVLNode left, right;
    int height;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1; // 新節點高度為 1
    }
}

class AVLTree {
    private AVLNode root;

    // 取得節點高度
    private int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    // 取得平衡因子
    private int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // 右旋轉
    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // 執行旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 左旋轉
    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // 執行旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // 插入節點
    public void insert(int data) {
        root = insertRec(root, data);
    }

    private AVLNode insertRec(AVLNode node, int data) {
        if (node == null) {
            return new AVLNode(data);
        }

        if (data < node.data) {
            node.left = insertRec(node.left, data);
        } else if (data > node.data) {
            node.right = insertRec(node.right, data);
        } else {
            return node; // 不允許重複值
        }

        // 更新高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 檢查平衡
        int balance = getBalance(node);

        // LL
        if (balance > 1 && data < node.left.data) {
            return rotateRight(node);
        }

        // RR
        if (balance < -1 && data > node.right.data) {
            return rotateLeft(node);
        }

        // LR
        if (balance > 1 && data > node.left.data) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RL
        if (balance < -1 && data < node.right.data) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // 搜尋節點
    public boolean search(int key) {
        return searchRec(root, key) != null;
    }

    private AVLNode searchRec(AVLNode node, int key) {
        if (node == null || node.data == key) {
            return node;
        }
        if (key < node.data) {
            return searchRec(node.left, key);
        }
        return searchRec(node.right, key);
    }

    // 刪除節點
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private AVLNode deleteRec(AVLNode node, int key) {
        if (node == null) {
            return node;
        }

        if (key < node.data) {
            node.left = deleteRec(node.left, key);
        } else if (key > node.data) {
            node.right = deleteRec(node.right, key);
        } else {
            // 找到要刪除的節點
            if ((node.left == null) || (node.right == null)) {
                AVLNode temp = (node.left != null) ? node.left : node.right;

                if (temp == null) {
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                // 有兩個子節點，取右子樹最小值替換
                AVLNode temp = minValueNode(node.right);
                node.data = temp.data;
                node.right = deleteRec(node.right, temp.data);
            }
        }

        if (node == null) {
            return node;
        }

        // 更新高度
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // 檢查平衡
        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }

        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RR
        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }

        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // 印出中序遍歷
    public void printTree() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + " ");
            printInOrder(node.right);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        System.out.println("=== 插入測試 ===");
        int[] values = {10, 20, 30, 40, 50, 25};
        for (int value : values) {
            System.out.println("插入: " + value);
            tree.insert(value);
            tree.printTree();
        }

        System.out.println("=== 搜尋測試 ===");
        System.out.println("搜尋 25: " + tree.search(25));
        System.out.println("搜尋 35: " + tree.search(35));

        System.out.println("=== 刪除測試 ===");
        tree.delete(40);
        tree.printTree();
        tree.delete(30);
        tree.printTree();
    }
}
