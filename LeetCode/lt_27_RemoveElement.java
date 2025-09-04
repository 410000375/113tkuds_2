class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0; // 慢指標，指向下一個非 val 元素應放的位置
        for (int j = 0; j < nums.length; j++) { // 快指標遍歷整個陣列
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i; // 非 val 元素的數量
    }
}
