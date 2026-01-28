package algo.practice.graph.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if you want to take course a.
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
 *
 *
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // check if graph has cycle (deadlock)
        // use DFS, if visited node come again in stack path, then there is a cycle

        Map<Integer, List<Integer>> coursePrequisiteMap = new HashMap<>();
        int[] visited = new int[numCourses];
        //Form the adjacency list MAp
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

        //check all prereqs
        if (coursePrequisiteMap.containsKey(crs)) {
            for (int preq : coursePrequisiteMap.get(crs)) {
                if (!dfs(preq, visited, coursePrequisiteMap)) {
                    return false;
                }
            }
        }
        visited[crs] = 2;
        return true;
    }

}
