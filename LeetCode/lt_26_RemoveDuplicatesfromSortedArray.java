class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int i = 0; // 慢指標，指向最後一個唯一元素的位置
        for (int j = 1; j < nums.length; j++) { // 快指標遍歷整個陣列
            if (nums[j] != nums[i]) {
                i++; // 慢指標前進
                nums[i] = nums[j]; // 更新唯一元素
            }
        }

        return i + 1; // 唯一元素的個數
    }
}
