import java.util.*;

class AVLLeaderboardSystem {
    class Node {
        String name;
        int score;
        Node left, right;
        int height;
        int size; // 子樹大小（用來計算排名）

        Node(String name, int score) {
            this.name = name;
            this.score = score;
            height = 1;
            size = 1;
        }
    }

    private Node root;
    private Map<String, Integer> playerScores = new HashMap<>(); // 儲存玩家分數（方便更新）

    // 取得高度
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // 取得子樹大小
    private int size(Node node) {
        return (node == null) ? 0 : node.size;
    }

    // 更新節點高度與子樹大小
    private void update(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.size = 1 + size(node.left) + size(node.right);
        }
    }

    // 取得平衡因子
    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // 右旋
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        update(y);
        update(x);

        return x;
    }

    // 左旋
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        update(x);
        update(y);

        return y;
    }

    // 插入節點（根據分數降序，如果分數相同，按名字排序）
    private Node insert(Node node, String name, int score) {
        if (node == null) return new Node(name, score);

        if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
            node.left = insert(node.left, name, score);
        } else {
            node.right = insert(node.right, name, score);
        }

        update(node);

        int balance = getBalance(node);

        // LL
        if (balance > 1 && (score > node.left.score || (score == node.left.score && name.compareTo(node.left.name) < 0)))
            return rotateRight(node);

        // RR
        if (balance < -1 && (score < node.right.score || (score == node.right.score && name.compareTo(node.right.name) > 0)))
            return rotateLeft(node);

        // LR
        if (balance > 1 && (score < node.left.score || (score == node.left.score && name.compareTo(node.left.name) > 0))) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RL
        if (balance < -1 && (score > node.right.score || (score == node.right.score && name.compareTo(node.right.name) < 0))) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // 刪除節點
    private Node delete(Node node, String name, int score) {
        if (node == null) return null;

        if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
            node.left = delete(node.left, name, score);
        } else if (score < node.score || (score == node.score && name.compareTo(node.name) > 0)) {
            node.right = delete(node.right, name, score);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node successor = minValueNode(node.right);
                node.name = successor.name;
                node.score = successor.score;
                node.right = delete(node.right, successor.name, successor.score);
            }
        }

        if (node == null) return null;

        update(node);

        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0)
            return rotateRight(node);

        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RR
        if (balance < -1 && getBalance(node.right) <= 0)
            return rotateLeft(node);

        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private Node minValueNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // 添加玩家
    public void addPlayer(String name, int score) {
        if (playerScores.containsKey(name)) {
            updateScore(name, score);
            return;
        }
        root = insert(root, name, score);
        playerScores.put(name, score);
    }

    // 更新分數
    public void updateScore(String name, int newScore) {
        if (playerScores.containsKey(name)) {
            int oldScore = playerScores.get(name);
            root = delete(root, name, oldScore);
        }
        root = insert(root, name, newScore);
        playerScores.put(name, newScore);
    }

    // 查詢玩家排名（第 1 名是最高分）
    public int getRank(String name) {
        if (!playerScores.containsKey(name)) return -1;
        int score = playerScores.get(name);
        return getRank(root, name, score) + 1; // 排名從 1 開始
    }

    private int getRank(Node node, String name, int score) {
        if (node == null) return 0;

        if (score > node.score || (score == node.score && name.compareTo(node.name) < 0)) {
            return getRank(node.left, name, score);
        } else if (score < node.score || (score == node.score && name.compareTo(node.name) > 0)) {
            return size(node.left) + 1 + getRank(node.right, name, score);
        } else {
            return size(node.left);
        }
    }

    // 查詢前 K 名玩家
    public List<String> getTopKPlayers(int k) {
        List<String> result = new ArrayList<>();
        getTopKPlayers(root, result, k);
        return result;
    }

    private void getTopKPlayers(Node node, List<String> result, int k) {
        if (node == null || result.size() >= k) return;
        getTopKPlayers(node.left, result, k);
        if (result.size() < k) result.add(node.name + " (" + node.score + ")");
        getTopKPlayers(node.right, result, k);
    }

    // 測試
    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();
        leaderboard.addPlayer("Alice", 90);
        leaderboard.addPlayer("Bob", 80);
        leaderboard.addPlayer("Charlie", 95);
        leaderboard.addPlayer("David", 85);

        System.out.println("前 3 名玩家: " + leaderboard.getTopKPlayers(3));
        System.out.println("Alice 排名: " + leaderboard.getRank("Alice"));

        leaderboard.updateScore("Bob", 98);
        System.out.println("更新後前 3 名玩家: " + leaderboard.getTopKPlayers(3));
        System.out.println("Bob 排名: " + leaderboard.getRank("Bob"));
    }
}
