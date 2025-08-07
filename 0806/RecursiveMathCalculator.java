public class RecursiveMathCalculator {

    // 1. 計算組合數 C(n, k)
    public static int combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 2. 計算卡塔蘭數 Catalan(n)
    public static int catalan(int n) {
        if (n <= 1) return 1;

        int result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    // 3. 計算漢諾塔移動步數
    public static int hanoiMoves(int n) {
        if (n == 1) return 1;
        return 2 * hanoiMoves(n - 1) + 1;
    }

    // 4. 判斷是否為回文數
    public static boolean isPalindrome(int num) {
        return num == reverse(num, 0);
    }

    private static int reverse(int num, int rev) {
        if (num == 0) return rev;
        return reverse(num / 10, rev * 10 + num % 10);
    }

    // 測試用主程式
    public static void main(String[] args) {
        System.out.println("C(5, 2) = " + combination(5, 2));      // Output: 10
        System.out.println("Catalan(4) = " + catalan(4));          // Output: 14
        System.out.println("Hanoi(3) steps = " + hanoiMoves(3));   // Output: 7
        System.out.println("Is 12321 palindrome? " + isPalindrome(12321)); // true
        System.out.println("Is 12345 palindrome? " + isPalindrome(12345)); // false
    }
}
