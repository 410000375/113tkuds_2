public class RecursionVsIteration {

    // 1. 計算二項式係數 (C(n, k))，遞迴版本
    public static int binomialRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    // 1. 計算二項式係數，迭代版本（動態規劃）
    public static int binomialIterative(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    // 2. 陣列乘積，遞迴版本
    public static int productRecursive(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    // 2. 陣列乘積，迭代版本
    public static int productIterative(int[] arr) {
        int product = 1;
        for (int num : arr) {
            product *= num;
        }
        return product;
    }

    // 3. 計算字串中元音數量，遞迴版本
    public static int countVowelsRecursive(String str, int index) {
        if (index == str.length()) return 0;
        char c = Character.toLowerCase(str.charAt(index));
        int count = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0;
        return count + countVowelsRecursive(str, index + 1);
    }

    // 3. 計算字串中元音數量，迭代版本
    public static int countVowelsIterative(String str) {
        int count = 0;
        for (char c : str.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) count++;
        }
        return count;
    }

    // 4. 檢查括號是否配對正確，遞迴版本
    public static boolean isBalancedRecursive(String str) {
        return isBalancedRecursiveHelper(str, 0, 0);
    }

    private static boolean isBalancedRecursiveHelper(String str, int index, int balance) {
        if (balance < 0) return false;
        if (index == str.length()) return balance == 0;
        if (str.charAt(index) == '(') return isBalancedRecursiveHelper(str, index + 1, balance + 1);
        if (str.charAt(index) == ')') return isBalancedRecursiveHelper(str, index + 1, balance - 1);
        return isBalancedRecursiveHelper(str, index + 1, balance);
    }

    // 4. 檢查括號是否配對正確，迭代版本
    public static boolean isBalancedIterative(String str) {
        int balance = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    // 測試與效能比較
    public static void main(String[] args) {
        int n = 20, k = 10;
        int[] arr = {1, 2, 3, 4, 5};
        String testStr = "Recursion and Iteration are both powerful (but tricky)!";
        String bracketStr = "(((())))";

        // 二項式係數
        long start = System.nanoTime();
        System.out.println("Binomial Recursive: " + binomialRecursive(n, k));
        System.out.println("Time: " + (System.nanoTime() - start) + " ns");

        start = System.nanoTime();
        System.out.println("Binomial Iterative: " + binomialIterative(n, k));
        System.out.println("Time: " + (System.nanoTime() - start) + " ns");

        // 陣列乘積
        System.out.println("\nProduct Recursive: " + productRecursive(arr, 0));
        System.out.println("Product Iterative: " + productIterative(arr));

        // 字串元音數
        System.out.println("\nVowels Recursive: " + countVowelsRecursive(testStr, 0));
        System.out.println("Vowels Iterative: " + countVowelsIterative(testStr));

        // 括號配對
        System.out.println("\nBalanced Recursive: " + isBalancedRecursive(bracketStr));
        System.out.println("Balanced Iterative: " + isBalancedIterative(bracketStr));
    }
}
