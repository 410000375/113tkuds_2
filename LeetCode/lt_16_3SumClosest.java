import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 先排序
        int n = nums.length;
        int closest = nums[0] + nums[1] + nums[2]; // 初始化為前三個數的和

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                // 如果更接近 target，就更新 closest
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }

                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    // sum == target，直接回傳
                    return sum;
                }
            }
        }
        return closest;
    }
}
