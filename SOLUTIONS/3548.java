import java.util.*;

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        long sum = 0;
        for (int[] row : grid) for (int v : row) sum += v;
        return solve(grid, sum) || solve(rotate(grid), sum);
    }

    private boolean solve(int[][] A, long sum) {
        int n = A.length, m = A[0].length;
        if (n == 1) return false;

        long[] rowSum = new long[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                rowSum[i] += A[i][j];

        Map<Integer, Integer> botFreq = new HashMap<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                botFreq.merge(A[i][j], 1, Integer::sum);

        Map<Integer, Integer> topFreq = new HashMap<>();
        long topSum = 0;

        for (int i = 0; i < n - 1; i++) {
            topSum += rowSum[i];
            for (int j = 0; j < m; j++) {
                topFreq.merge(A[i][j], 1, Integer::sum);
                botFreq.merge(A[i][j], -1, Integer::sum);
                if (botFreq.get(A[i][j]) == 0) botFreq.remove(A[i][j]);
            }

            long botSum = sum - topSum;
            long diff = topSum - botSum;
            if (diff == 0) return true;

            int topRows = i + 1;
            int botRows = n - i - 1;

            if (diff > 0 && diff <= 100000) {
                int d = (int) diff;
                if (topRows == 1) {
                    if (A[0][0] == d || A[0][m - 1] == d) return true;
                } else if (m == 1) {
                    if (A[0][0] == d || A[i][0] == d) return true;
                } else {
                    if (topFreq.containsKey(d)) return true;
                }
            } else if (diff < 0 && -diff <= 100000) {
                int d = (int) -diff;
                if (botRows == 1) {
                    if (A[i + 1][0] == d || A[i + 1][m - 1] == d) return true;
                } else if (m == 1) {
                    if (A[i + 1][0] == d || A[n - 1][0] == d) return true;
                } else {
                    if (botFreq.containsKey(d)) return true;
                }
            }
        }
        return false;
    }

    private int[][] rotate(int[][] A) {
        int n = A.length, m = A[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                res[j][n - 1 - i] = A[i][j];
        return res;
    }
}
