package algo.practice.graph.random;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Courses dependency can form a directed graph.
 * You are given an array prerequisites where prerequisites[i] = [a, b] indicates that
 * you must take course b first if you want to take course a.
 *
 * The pair [0, 1], indicates that must take course 1 before taking course 0.
 *
 * There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.
 *
 * Return true if it is possible to finish all courses, otherwise return false.
 *
 * Visited  = 0 means not visited
 *          = 1 means in recursion stack
 *          = 2 means done
 *
 *
 * Approach:
 * - Model courses and prerequisites as a directed graph, where an edge [a, b] means course a depends on course b.
 * - Use DFS to detect cycles in the graph:
 *    - Mark courses as unvisited (0), visiting (1), or visited (2).
 *    - If a course is revisited while still in the recursion stack (visiting), a cycle exists and it is impossible to finish all courses.
 *    - If DFS completes without cycles, all courses can be finished.
 * - Handles disconnected graphs by running DFS from each course.
 * - Time Complexity: O(N + E), where N is the number of courses and E is the number of prerequisites.
 *
 */
@Slf4j
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // check if graph has cycle (deadlock)
        // use DFS, if visited node come again in stack path, then there is a cycle

        //adjacency list
        Map<Integer, List<Integer>> coursePrequisiteMap = new HashMap<>();
        int[] visited = new int[numCourses];
        // Build the adjacency list: course -> list of prerequisites
        for(int[] preq : prerequisites){
            if(!coursePrequisiteMap.containsKey(preq[0])){
                coursePrequisiteMap.put(preq[0], new ArrayList<>());
            }
            coursePrequisiteMap.get(preq[0]).add(preq[1]);
        }

        //do DFS for all courses, considering disconnected tree
        for(int i = 0; i < numCourses; i++) {
            if(visited[i]==0){
                if(!dfs(i, visited, coursePrequisiteMap)){
                    return false;
                }
            }
        }
        return true;
    }


    private boolean dfs(int crs, int[] visited, Map<Integer, List<Integer>> coursePrequisiteMap){
        if (visited[crs] == 1) return false; // cycle
        if (visited[crs] == 2) return true;  // already processed

        visited[crs] = 1; // Mark as visiting

        //check all prereqs
        if (coursePrequisiteMap.containsKey(crs)) {
            for (int preq : coursePrequisiteMap.get(crs)) {
                if (!dfs(preq, visited, coursePrequisiteMap)) {
                    return false;
                }
            }
        }
        // Mark as fully processed
        // courses are marked done only after their prerequisites are processed
        visited[crs] = 2;
        return true;
    }

    private boolean dfsCopy(int crs,
                        int[] visited,
                        Map<Integer, List<Integer>> coursePrequisiteMap) {
        // 1️⃣ mark this course as being visited (on the recursion stack)
        visited[crs] = 1;
        log.info("Visiting course {}", crs);

        // 2️⃣ fetch its prerequisite list – may be null if the course has none
        List<Integer> prereqs = coursePrequisiteMap.get(crs);
        if (prereqs != null) {
            for (int pre : prereqs) {
                if (visited[pre] == 1) {                 // back‑edge → cycle found
                    log.info("Cycle detected: {} -> {}", crs, pre);
                    return false;                        // propagate failure upward
                }
                if (visited[pre] == 0) {                 // not visited yet → recurse
                    if(!dfsCopy(pre, visited, coursePrequisiteMap)) { // a deeper call found a cycle
                        return false;
                    }
                }
                // visited[pre] == 2 → already processed – nothing to do
            }
        }

        // 3️⃣ all prerequisites are safe → mark this node as completely processed
        visited[crs] = 2;
        log.info("Course {} finished", crs);
        return true;
    }

}
