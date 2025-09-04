import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        // 當長度達到 2 * n 時，加入結果
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        // 可以加 '(' 的條件：左括號數量 < n
        if (open < max) {
            current.append('(');
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // 回溯
        }

        // 可以加 ')' 的條件：右括號數量 < 左括號數量
        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // 回溯
        }
    }
}
