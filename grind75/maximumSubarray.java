// https://leetcode.com/problems/maximum-subarray/
class Solution {
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum <0) {
                sum = nums[i];
            } else if (nums[i] + sum > 0) {
                sum += nums[i];
            }

            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }
}