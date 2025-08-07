import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 1. 將每層節點分別存在不同的 List 中
    public static List<List<Integer>> levelOrderByLayer(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                layer.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(layer);
        }
        return res;
    }

    // 2. 之字形層序走訪（奇數層左到右，偶數層右到左）
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> layer = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (leftToRight) {
                    layer.addLast(cur.val);
                } else {
                    layer.addFirst(cur.val);
                }
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(layer);
            leftToRight = !leftToRight;
        }
        return res;
    }

    // 3. 只列印每層的最後一個節點
    public static List<Integer> printLastNodeOfEachLevel(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int lastVal = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                lastVal = cur.val;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(lastVal);
        }
        return res;
    }

    // 4. 垂直層序走訪（按照水平位置分組）
    public static List<List<Integer>> verticalOrderTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        // TreeMap key是column編號（水平位置），value是該列節點值列表
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            int col = pair.getValue();

            map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(node.val);

            if (node.left != null) queue.offer(new Pair<>(node.left, col - 1));
            if (node.right != null) queue.offer(new Pair<>(node.right, col + 1));
        }

        res.addAll(map.values());
        return res;
    }

    // Java 自己沒有 Pair 內建，我們自己定義一個簡單版本
    static class Pair<K, V> {
        private K key;
        private V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    // 測試程式
    public static void main(String[] args) {
        /*
                  1
                 / \
                2   3
               / \   \
              4   5   6
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println("1. 每層節點列表:");
        System.out.println(levelOrderByLayer(root));

        System.out.println("\n2. 之字形層序走訪:");
        System.out.println(zigzagLevelOrder(root));

        System.out.println("\n3. 每層最後一個節點:");
        System.out.println(printLastNodeOfEachLevel(root));

        System.out.println("\n4. 垂直層序走訪:");
        System.out.println(verticalOrderTraversal(root));
    }
}
