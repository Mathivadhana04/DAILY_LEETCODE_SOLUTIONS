import java.util.*;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < m; i++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }
        
        int maxArea = 0;
        
        for (int i = 0; i < m; i++) {
            int[] row = Arrays.copyOf(matrix[i], n);
            Arrays.sort(row);
            
            for (int j = 0; j < n; j++) {
                int height = row[j];
                int width = n - j;
                maxArea = Math.max(maxArea, height * width);
            }
        }
        
        return maxArea;
    }
}
