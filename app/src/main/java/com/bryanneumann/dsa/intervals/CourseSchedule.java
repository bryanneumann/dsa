package com.bryanneumann.dsa.intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Demonstrates topological sorting to find a valid order of courses to take
 * given prerequisites.
 *
 * https://leetcode.com/problems/course-schedule-iv/ (checkIfPrerequisite)
 * https://leetcode.com/problems/course-schedule/ (canFinish)
 * https://leetcode.com/problems/course-schedule-ii/ (findOrder)

 *  *Constraints:

 *  *1 <= numCourses <= 2000 0 <= prerequisites.length <= numCourses * (numCourses
 * - 1) prerequisites[i].length == 2 0 <= a, b < numCourses a != b All the pairs
 * [a, b] are distinct.
 *
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Setup adjacency list and indegree array
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            int src = p[0];
            int dst = p[1];
            indegree[dst]++;
            adj.get(src).add(dst);
        }

        // Setup the queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int finishedCourses = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            finishedCourses++;
            for (int i = 0; i < numCourses; i++) {
                indegree[i]--;
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }
        }
        return finishedCourses == numCourses;
    }

    public List<Boolean> checkIfPrerequisite(int numCourses,
            int[][] prerequisites, int[][] queries) {

        List<Boolean> result = new ArrayList<>();

        // Build adj list
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        Map<Integer, Set<Integer>> isPre = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new HashSet<>());
            isPre.put(i, new HashSet<>());
        }

        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            int src = p[0];
            int dst = p[1];
            adj.get(src).add(dst);
            indegree[dst]++;
        }

        // Build queue 
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // Process queue 
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    q.add(neighbor);
                }
                isPre.get(neighbor).add(node);
                isPre.get(neighbor).addAll(isPre.get(node));
            }
        }
        for (int[] query : queries) {
            result.add(isPre.get(query[1]).contains(query[0]));
        }
        return result;
    }

     public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            int course = p[0], prereq = p[1];
            graph.get(prereq).add(course); // directed edge: prereq → course
        }

        int[] visited = new int[numCourses]; // 0 = unvisited, 1 = visiting, 2 = visited
        List<Integer> order = new ArrayList<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (!dfs(i, graph, visited, order)) {
                    return new int[0]; // cycle detected
                }
            }
        }

        // Reverse post-order DFS gives valid topological sort
        Collections.reverse(order);
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            result[i] = order.get(i);
        }
        return result;
    }

    private boolean dfs(int node, List<List<Integer>> graph, int[] visited, List<Integer> order) {
        visited[node] = 1; // mark as visiting
        for (int neighbor : graph.get(node)) {
            if (visited[neighbor] == 1) {
                return false; // cycle detected
            }
            if (visited[neighbor] == 0) {
                if (!dfs(neighbor, graph, visited, order)) {
                    return false;
                }
            }
        }
        visited[node] = 2; // mark as visited
        order.add(node);   // add to result in post-order
        return true;
    }
}
