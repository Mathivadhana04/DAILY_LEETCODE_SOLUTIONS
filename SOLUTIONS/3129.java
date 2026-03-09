class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int mod = 1000000007;
        long[][][] dp = new long[zero + 1][one + 1][2];

        for (int i = 1; i <= Math.min(limit, zero); i++) dp[i][0][0] = 1;
        for (int j = 1; j <= Math.min(limit, one); j++) dp[0][j][1] = 1;

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                long x = i - limit - 1 >= 0 ? dp[i - limit - 1][j][1] : 0;
                long y = j - limit - 1 >= 0 ? dp[i][j - limit - 1][0] : 0;

                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1] - x) % mod;
                dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1] - y) % mod;

                if (dp[i][j][0] < 0) dp[i][j][0] += mod;
                if (dp[i][j][1] < 0) dp[i][j][1] += mod;
            }
        }

        return (int)((dp[zero][one][0] + dp[zero][one][1]) % mod);
    }
}
