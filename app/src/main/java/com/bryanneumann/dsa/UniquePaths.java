package com.bryanneumann.dsa;

public class UniquePaths {
    /**
    * Calculate unique paths from grid[0][0] to grid[m-1][n-1] considering obstacles.
     */
    public int uniquePathsWithObstacles(int[][] grid) {
        // runtime beats 100%, mem beats 50.79%
        // return uniquePathsWithObstacles_dp_optimized(grid);

        // Runtime beats 100%, space 28.50%
        return uniquePathsWithObstacles_dp(grid);
    }

    public int uniquePathsWithObstacles_dp_optimized(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        // If the start or end are blocked, we are SOL.
        if (grid[0][0] == 1 || grid[rows - 1][cols - 1] == 1) {
            return 0;
        }
        // path count depends on two things:
        // the cell below it (same column, previous row),
        // and the cell to the right (same row, next column).

        int[] dp = new int[cols + 1];
        // Seed the path just outside the bottom-right corner dp[rows][cols - 1] = 1;
        dp[cols - 1] = 1;
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 0; c--) {
                // If blocked
                if (grid[r][c] == 1) {
                    // Don't include this cell in the path.
                    dp[c] = 0;
                } else {
                    dp[c] += dp[c + 1];
                }
            }
        }
        return dp[0];
    }

    public int uniquePathsWithObstacles_dp(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        // If the start or end are blocked, we are SOL.
        if (grid[0][0] == 1 || grid[rows - 1][cols - 1] == 1) {
            return 0;
        }
        int[][] dp = new int[rows + 1][cols + 1];
        // Seed the path just outside the bottom-right corner
        dp[rows][cols - 1] = 1;
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 0; c--) {
                // If blocked
                if (grid[r][c] == 1) {
                    // Don't include this cell in the path.
                    dp[r][c] = 0;
                } else {
                    // Add cells from both directions using cached valeus
                    dp[r][c] += dp[r + 1][c]; // Right
                    dp[r][c] += dp[r][c + 1]; // Down
                }
            }
        }
        return dp[0][0];
    }

    /**
      Count unique paths from upper left of the grid to the bottom right of the grid using bottom-up dynamic programming approach for O(m*n) time and O(n) space
      @param m number of rows
      @param n number of columns
      @return number of unique paths
    */
    public int uniquePaths(int m, int n) {
        int[] prevRow = new int[n];
        // Keep track of the current and previous rows
        for (int i = m - 1; i >= 0; i--) {
            int[] currRow = new int[n];
            // Init the last cell of the row to 1 so something will be added (other than 0) for successive calls.
            currRow[n - 1] = 1;
            for (int j = n - 2; j >= 0; j--) {
                currRow[j] = currRow[j + 1] + prevRow[j];
            }
            prevRow = currRow;
        }
        return prevRow[0];
    }
}