class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = findBound(nums, target, true);
        if (left == -1) return new int[]{-1, -1}; // 沒找到
        int right = findBound(nums, target, false);
        return new int[]{left, right};
    }

    // findLeft = true 找左邊界，false 找右邊界
    private int findBound(int[] nums, int target, boolean findLeft) {
        int left = 0, right = nums.length - 1;
        int bound = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                bound = mid;
                if (findLeft) right = mid - 1; // 繼續往左找
                else left = mid + 1;           // 繼續往右找
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return bound;
    }
}
