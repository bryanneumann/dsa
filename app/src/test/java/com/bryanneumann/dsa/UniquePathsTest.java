package com.bryanneumann.dsa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UniquePathsTest {

    /**
     * Test unique paths in a grid without obstacles.
     *
     * The grid is represented as m x n, where m is the number of rows and n is
     * the number of columns.
     */
    @ParameterizedTest
    @CsvSource({
        "2, 4, 4",
        "3, 7, 28",
        "9, 9, 12870",
        "3, 2, 3",
        "7, 3, 28",
        "3, 3, 6",
        "1, 1, 1",
        "1, 2, 1"
    })
    void testUniquePaths(int m, int n, int expected) {
        System.out.println(m + "x" + n);
        UniquePaths uniquePaths = new UniquePaths();
        final int actual = uniquePaths.uniquePaths(m, n);
        final String message = String.format("Expected %d unique paths for a %d x %d grid", m, n, expected);
        assertEquals(expected, actual, message);
    }

    @Test
    public void testUniquePaths_dp() {
        UniquePaths uniquePaths = new UniquePaths();
        int[][] grid = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        int expected = 2; // Two unique paths
        int actual = uniquePaths.uniquePathsWithObstacles(grid);
        assertEquals(expected, actual);
    }

    @Test
    void testUniquePathsWithObstacles() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void testUniquePathsWithObstacles_dp() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void testUniquePathsWithObstacles_dp_optimized() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
