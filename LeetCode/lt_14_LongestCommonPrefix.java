class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // 先假設第一個字串為前綴
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            // 不斷縮短 prefix 直到 strs[i] 開頭匹配
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }
}
