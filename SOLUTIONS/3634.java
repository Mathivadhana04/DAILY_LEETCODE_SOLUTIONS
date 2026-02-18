import java.util.Arrays;

class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 0;
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r < n && 1L * nums[r] <= 1L * nums[l] * k) {
                r++;
            }
            best = Math.max(best, r - l);
        }
        return n - best;
    }
}
