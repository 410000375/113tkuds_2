import java.util.*;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // 轉小寫後用雙指標比對
        int left = 0, right = s.length() - 1;
        boolean isPalindrome = true;

        while (left < right) {
            // 忽略非英文字母
            while (left < right && !Character.isLetter(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetter(s.charAt(right))) {
                right--;
            }

            // 比對字元（不分大小寫）
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                isPalindrome = false;
                break;
            }
            left++;
            right--;
        }

        System.out.println(isPalindrome ? "Yes" : "No");
    }
}


