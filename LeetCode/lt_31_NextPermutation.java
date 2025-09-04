class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        // 1. 從後往前找到第一個升序位置 i，使 nums[i] < nums[i+1]
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) { // 找到這個位置
            int j = n - 1;
            // 2. 從後往前找到第一個比 nums[i] 大的數 nums[j]
            while (nums[j] <= nums[i]) j--;
            // 3. 交換 nums[i] 和 nums[j]
            swap(nums, i, j);
        }
        // 4. 將 i 之後的數字反轉（升序排列）
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
