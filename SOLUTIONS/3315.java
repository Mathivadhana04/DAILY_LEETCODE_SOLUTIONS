import java.util.*;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            if (num == 2) {
                ans[i] = -1;
            } else {
                int bit = 1;
                while ((num & bit) != 0) {
                    bit <<= 1;
                }
                ans[i] = num - (bit >> 1);
            }
        }
        return ans;
    }
}

