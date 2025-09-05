import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        
        // 邊界：空字串
        if (s.length() == 0) {
            System.out.println(0);
            return;
        }
        
        Map<Character, Integer> lastIndex = new HashMap<>();
        int left = 0, maxLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (lastIndex.containsKey(c) && lastIndex.get(c) >= left) {
                // 發現重複字元，更新 left
                left = lastIndex.get(c) + 1;
            }
            
            // 更新當前字元最後位置
            lastIndex.put(c, i);
            
            // 計算視窗長度
            maxLen = Math.max(maxLen, i - left + 1);
        }
        
        System.out.println(maxLen);
    }
}
