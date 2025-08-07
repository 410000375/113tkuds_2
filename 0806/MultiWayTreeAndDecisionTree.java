import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // 多路樹節點 (任意多子節點)
    static class MultiWayNode {
        String val;
        List<MultiWayNode> children;

        MultiWayNode(String val) {
            this.val = val;
            this.children = new ArrayList<>();
        }

        void addChild(MultiWayNode child) {
            children.add(child);
        }
    }

    // 1. 多路樹深度優先走訪 (DFS)
    public static void dfs(MultiWayNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        for (MultiWayNode child : root.children) {
            dfs(child);
        }
    }

    // 2. 多路樹廣度優先走訪 (BFS)
    public static void bfs(MultiWayNode root) {
        if (root == null) return;
        Queue<MultiWayNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiWayNode curr = queue.poll();
            System.out.print(curr.val + " ");
            for (MultiWayNode child : curr.children) {
                queue.offer(child);
            }
        }
    }

    // 3. 簡單決策樹模擬 - 猜數字遊戲（固定範圍1~10）
    static class DecisionNode {
        String question;  // 這裡以猜數字示範，問題即提示
        Map<String, DecisionNode> answers; // 用答案字串對應下一節點
        boolean isLeaf;
        String result; // 猜中或結束提示

        DecisionNode(String question) {
            this.question = question;
            this.answers = new HashMap<>();
            this.isLeaf = false;
            this.result = null;
        }

        void addAnswer(String ans, DecisionNode nextNode) {
            answers.put(ans, nextNode);
        }

        void setLeafResult(String res) {
            this.isLeaf = true;
            this.result = res;
        }
    }

    // 簡單的猜數字決策樹示範，回傳結果字串
    public static String guessNumber(DecisionNode root, Scanner sc) {
        DecisionNode current = root;
        while (!current.isLeaf) {
            System.out.println(current.question);
            String answer = sc.nextLine().trim().toLowerCase();
            if (current.answers.containsKey(answer)) {
                current = current.answers.get(answer);
            } else {
                System.out.println("請輸入有效答案！");
            }
        }
        return current.result;
    }

    // 4. 計算多路樹高度 (根節點高度為1)
    public static int treeHeight(MultiWayNode root) {
        if (root == null) return 0;
        int maxChildHeight = 0;
        for (MultiWayNode child : root.children) {
            maxChildHeight = Math.max(maxChildHeight, treeHeight(child));
        }
        return maxChildHeight + 1;
    }

    // 5. 計算每個節點的度數（子節點數），並印出
    public static void printNodeDegrees(MultiWayNode root) {
        if (root == null) return;
        System.out.println("節點 '" + root.val + "' 的度數: " + root.children.size());
        for (MultiWayNode child : root.children) {
            printNodeDegrees(child);
        }
    }

    // 測試主程式
    public static void main(String[] args) {
        // 建立多路樹範例
        MultiWayNode root = new MultiWayNode("A");
        MultiWayNode b = new MultiWayNode("B");
        MultiWayNode c = new MultiWayNode("C");
        MultiWayNode d = new MultiWayNode("D");
        MultiWayNode e = new MultiWayNode("E");
        MultiWayNode f = new MultiWayNode("F");
        MultiWayNode g = new MultiWayNode("G");

        root.addChild(b);
        root.addChild(c);
        b.addChild(d);
        b.addChild(e);
        c.addChild(f);
        c.addChild(g);

        System.out.print("多路樹深度優先走訪: ");
        dfs(root);
        System.out.println();

        System.out.print("多路樹廣度優先走訪: ");
        bfs(root);
        System.out.println();

        System.out.println("多路樹高度: " + treeHeight(root));
        System.out.println("各節點度數:");
        printNodeDegrees(root);

        // 建立簡單猜數字決策樹
        DecisionNode q1 = new DecisionNode("猜一個 1 到 10 之間的數字，數字大於 5 嗎？(yes/no)");
        DecisionNode q2 = new DecisionNode("數字大於 7 嗎？(yes/no)");
        DecisionNode q3 = new DecisionNode("數字是 6 嗎？(yes/no)");

        q1.addAnswer("yes", q2);
        q1.addAnswer("no", q3);

        q2.addAnswer("yes", new DecisionNode("你猜的數字是 8、9 或 10"));
        q2.addAnswer("no", new DecisionNode("你猜的數字是 6 或 7"));

        q3.addAnswer("yes", new DecisionNode("你猜的數字是 6"));
        q3.addAnswer("no", new DecisionNode("你猜的數字是 1 到 5"));

        // 設定葉節點結果
        q2.answers.get("yes").setLeafResult("你猜的數字是 8、9 或 10");
        q2.answers.get("no").setLeafResult("你猜的數字是 6 或 7");
        q3.answers.get("yes").setLeafResult("你猜的數字是 6");
        q3.answers.get("no").setLeafResult("你猜的數字是 1 到 5");

        System.out.println("\n開始猜數字決策樹遊戲:");
        Scanner sc = new Scanner(System.in);
        String result = guessNumber(q1, sc);
        System.out.println("結果: " + result);

        sc.close();
    }
}
