package com.bryanneumann.dsa.intervals;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Test class for CourseSchedule.
 * 
 * This class contains unit tests for the methods in CourseSchedule.
 * 
 * Constraints:
 * 1 <= numCourses <= 20000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= a, b < numCourses
 * a != b
 * All the pairs [a, b] are distinct.
 */
public class CourseScheduleTest {
    @Test
    void testCanFinish() {
        CourseSchedule courseSchedule = new CourseSchedule();
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        boolean result = courseSchedule.canFinish(numCourses, prerequisites);
        assert result : "Should be able to finish all courses"; 

        numCourses = 2;
        prerequisites = new int[][]{{1, 0}, {0, 1}};
        result = courseSchedule.canFinish(numCourses, prerequisites);
        assert !result : "Should not be able to finish all courses due to cycle";

        numCourses = 3;
        prerequisites = new int[][]{{0, 1}, {1, 2}};
        result = courseSchedule.canFinish(numCourses, prerequisites);
        assert result : "Should be able to finish all courses";

        numCourses = 4;
        prerequisites = new int[][]{{0, 1}, {1, 2}, {2, 3}};
        result = courseSchedule.canFinish(numCourses, prerequisites);
        assert result : "Should be able to finish all courses"; 

        numCourses = 5;
        prerequisites = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        result = courseSchedule.canFinish(numCourses, prerequisites);
        assert result : "Should be able to finish all courses";

       numCourses = 200;
        prerequisites = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}};
        result = courseSchedule.canFinish(numCourses, prerequisites);
        assert result : "Should be able to finish all courses";

        numCourses = 2000;
        prerequisites = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}};
        result = courseSchedule.canFinish(numCourses, prerequisites);
        assert result : "Should be able to finish all courses";

        numCourses = 20000;
        prerequisites = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, {10, 11}, {11, 12}, {12, 13}, {13, 14}, {14, 15}, {15, 16}, {16, 17}, {17, 18}, {18, 19}, {19, 20}};
        result = courseSchedule.canFinish(numCourses, prerequisites);
        assert result : "Should be able to finish all courses";
    }

    @Test
    void testCheckIfPrerequisite() {
        CourseSchedule courseSchedule = new CourseSchedule();
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        int[][] queries = {{0, 1}, {1, 0}};
        List<Boolean> result = courseSchedule.checkIfPrerequisite(numCourses, prerequisites, queries);
        assert result.get(0) : "0 is a prerequisite of 1";
        assert !result.get(1) : "1 is not a prerequisite of 0";

        numCourses = 3;
        prerequisites = new int[][]{{0, 1}, {1, 2}};
        queries = new int[][]{{0, 2}, {1, 2}, {2, 0}};
        result = courseSchedule.checkIfPrerequisite(numCourses, prerequisites, queries);
        assert result.get(0) : "0 is a prerequisite of 2";
        assert result.get(1) : "1 is a prerequisite of 2";
        assert !result.get(2) : "2 is not a prerequisite of 0";

        numCourses = 4;
        prerequisites = new int[][]{{0, 1}, {1, 2}, {2, 3}};
        queries = new int[][]{{0, 3}, {1, 3}, {2, 3}, {3, 0}};
        result = courseSchedule.checkIfPrerequisite(numCourses, prerequisites, queries);
        assert result.get(0) : "0 is a prerequisite of 3";
        assert result.get(1) : "1 is a prerequisite of 3";
        assert result.get(2) : "2 is a prerequisite of 3";
        assert !result.get(3) : "3 is not a prerequisite of 0"; 
    }

    @Test
    void testFindOrder() {

    }
}
