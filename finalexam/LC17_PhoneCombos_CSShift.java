import java.util.*;

public class LC17_PhoneCombos_CSShift {
    // 鍵盤映射
    private static final String[] KEYPAD = {
        "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine().trim();
        sc.close();

        List<String> result = letterCombinations(digits);
        for (String combo : result) {
            System.out.println(combo);
        }
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }

    private static void backtrack(String digits, int idx, StringBuilder path, List<String> res) {
        if (idx == digits.length()) {
            res.add(path.toString());
            return;
        }
        String letters = KEYPAD[digits.charAt(idx) - '2'];
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(digits, idx + 1, path, res);
            path.deleteCharAt(path.length() - 1); // 回溯
        }
    }
}
