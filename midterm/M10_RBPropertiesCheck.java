import java.util.*;

public class M10_RBPropertiesCheck {

    static class Node {
        int val;
        char color; // 'R' 或 'B'
        Node(int v, char c) { val = v; color = c; }
    }

    static Node[] tree;

    // 回傳黑高度，如果違反黑高度則回傳 -1
    static int checkBlackHeight(int idx) {
        if (idx >= tree.length || tree[idx] == null) return 1; // null 視為黑節點

        Node cur = tree[idx];

        int leftBH = checkBlackHeight(2 * idx + 1);
        if (leftBH == -1) return -1;
        int rightBH = checkBlackHeight(2 * idx + 2);
        if (rightBH == -1) return -1;

        if (leftBH != rightBH) return -1; // 黑高度不一致

        return leftBH + (cur.color == 'B' ? 1 : 0);
    }

    // 檢查紅紅相鄰
    static boolean checkRedRed(int idx) {
        if (idx >= tree.length || tree[idx] == null) return true;

        Node cur = tree[idx];

        int leftIdx = 2 * idx + 1;
        int rightIdx = 2 * idx + 2;

        // 紅節點不能連接紅子節點
        if (cur.color == 'R') {
            if (leftIdx < tree.length && tree[leftIdx] != null && tree[leftIdx].color == 'R') return false;
            if (rightIdx < tree.length && tree[rightIdx] != null && tree[rightIdx].color == 'R') return false;
        }

        return checkRedRed(leftIdx) && checkRedRed(rightIdx);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        tree = new Node[n];

        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            String c = sc.next();
            if (val == -1) {
                tree[i] = null;
            } else {
                tree[i] = new Node(val, c.charAt(0));
            }
        }

        // 1. 根節點是否黑
        if (tree.length > 0 && tree[0] != null && tree[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 2. 紅紅相鄰
        if (!checkRedRed(0)) {
            System.out.println("RedRedViolation");
            return;
        }

        // 3. 黑高度一致
        if (checkBlackHeight(0) == -1) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }
}

