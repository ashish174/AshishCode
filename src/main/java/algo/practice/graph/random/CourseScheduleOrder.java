package algo.practice.graph.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if you want to take course a.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.
 *
 * Return a valid ordering of courses you can take to finish all courses. If there are many valid answers, return any of them. If it's not possible to finish all courses, return an empty array.
 *
 *
 */
public class CourseScheduleOrder {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> coursePreReqMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[numCourses];
        int[] courseOrdering = new int[numCourses];
        // Generate adjacency list
        for(int[] preq : prerequisites){
            if(!coursePreReqMap.containsKey(preq[0])){
                coursePreReqMap.put(preq[0], new ArrayList<>());
            }
            coursePreReqMap.get(preq[0]).add(preq[1]);
        }
        //check all nodes
        for(int crs = 0; crs < numCourses; crs++){
            if(visited[crs]==0){
                if(!dfs(crs, coursePreReqMap, visited, stack)){
                    return new int[0];
                }
            }
        }
        //topological sort is recorded in stack
        int i=numCourses;
        while(!stack.isEmpty()){
            courseOrdering[--i]=stack.pop();
        }
        return courseOrdering;



    }

    boolean dfs(int crs, Map<Integer, List<Integer>> coursePreReqMap, int[] visited, Stack<Integer> stack){
        //edge is to completed vertex
        if(visited[crs]==2){
            return true;
        }
        // cycle detected due to back edge
        if(visited[crs]==1) {
            return false;
        }
        //mark vertex as visiting
        visited[crs]= 1;
        if(coursePreReqMap.containsKey(crs)){
            for(int preq : coursePreReqMap.get(crs)){
                if(!dfs(preq, coursePreReqMap, visited, stack)){
                    return false;
                }
            }
        }
        visited[crs] = 2;
        stack.push(crs);
        return true;
    }
}
