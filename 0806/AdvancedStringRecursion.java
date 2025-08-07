import java.util.HashSet;

public class AdvancedStringRecursion {

    // 1. 遞迴產生字串的所有排列組合
    public static void generatePermutations(String str, String result) {
        if (str.isEmpty()) {
            System.out.println(result);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            String remaining = str.substring(0, i) + str.substring(i + 1);
            generatePermutations(remaining, result + str.charAt(i));
        }
    }

    // 2. 遞迴實作字串匹配演算法 (類似 strStr 函式)
    public static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return true;
        if (text.isEmpty()) return false;
        if (text.charAt(0) == pattern.charAt(0)) {
            return isMatch(text.substring(1), pattern.substring(1));
        } else {
            return isMatch(text.substring(1), pattern);
        }
    }

    // 3. 遞迴移除字串中的重複字符
    public static String removeDuplicates(String str) {
        return removeDuplicatesHelper(str, "", new HashSet<>());
    }

    private static String removeDuplicatesHelper(String str, String result, HashSet<Character> seen) {
        if (str.isEmpty()) {
            return result;
        }
        char first = str.charAt(0);
        if (!seen.contains(first)) {
            seen.add(first);
            result += first;
        }
        return removeDuplicatesHelper(str.substring(1), result, seen);
    }

    // 4. 遞迴計算字串的所有子字串組合
    public static void generateSubstrings(String str, String current, int index) {
        if (index == str.length()) {
            if (!current.isEmpty()) {
                System.out.println(current);
            }
            return;
        }
        // 包含當前字元
        generateSubstrings(str, current + str.charAt(index), index + 1);
        // 不包含當前字元
        generateSubstrings(str, current, index + 1);
    }

    // 測試主程式
    public static void main(String[] args) {
        String str = "abc";
        System.out.println("所有排列組合:");
        generatePermutations(str, "");

        System.out.println("\n字串匹配:");
        System.out.println(isMatch("hello", "ell")); // true
        System.out.println(isMatch("abcde", "ace")); // true

        System.out.println("\n移除重複字元:");
        System.out.println(removeDuplicates("aabbccdde"));

        System.out.println("\n所有子字串組合:");
        generateSubstrings("abc", "", 0);
    }
}
