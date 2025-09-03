class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int i = 0, n = s.length();
        while (i < n && s.charAt(i) == ' ') i++; // skip leading spaces
        
        if (i == n) return 0;
        
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }
        
        int result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            
            // 溢位檢查
            if (result > Integer.MAX_VALUE / 10 || 
               (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            result = result * 10 + digit;
            i++;
        }
        
        return result * sign;
    }
}