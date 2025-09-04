class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0; // 空字串特殊情況
        
        int n = haystack.length();
        int m = needle.length();
        
        for (int i = 0; i <= n - m; i++) { // 遍歷可能起始位置
            if (haystack.substring(i, i + m).equals(needle)) {
                return i; // 找到第一個符合
            }
        }
        return -1; // 沒有找到
    }
}
