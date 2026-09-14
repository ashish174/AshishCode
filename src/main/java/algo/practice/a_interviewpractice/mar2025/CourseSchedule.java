package algo.practice.a_interviewpractice.mar2025;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 *
 */
public class CourseSchedule {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses<=0) {
            return new int[]{};
        }
        int[] courseSchedule = new int[numCourses];
        if(prerequisites==null || prerequisites.length==0){
            for(int i=0; i < numCourses; i++){
                courseSchedule[i] = i;
            }
            return courseSchedule;
        }
        //create adjacency list
        Map<Integer, List<Integer>> prerequisitesMap = new HashMap<>();
        for(int[] prerequisite : prerequisites){
            if(!prerequisitesMap.containsKey(prerequisite[0])){
                prerequisitesMap.put(prerequisite[0], new ArrayList<>());
            }
            prerequisitesMap.get(prerequisite[0]).add(prerequisite[1]);
        }
        int[] visited = new int[numCourses];
        Stack<Integer> courseStack = new Stack<>();
        for(int course=0; course < numCourses; course++){
            if(visited[course]==0){
                if(!findOrderUsingDFS(course, prerequisitesMap, visited, courseStack)){
                    return new int[0];
                }
            }
        }
        int i=numCourses-1;
        while(!courseStack.isEmpty()){
            courseSchedule[i--] = courseStack.pop();
        }
        return courseSchedule;
    }

    private boolean findOrderUsingDFS(int course, Map<Integer, List<Integer>> prerequisitesMap, int[] visited, Stack<Integer> courseStack) {
        if(visited[course]==1){
            return false;
        }
        if(visited[course]==2){
            return true;
        }
        //mark as visited
        visited[course] = 1;
        if(prerequisitesMap.containsKey(course)){
            for(int prerequisites : prerequisitesMap.get(course)){
                if(!findOrderUsingDFS(prerequisites, prerequisitesMap, visited, courseStack)){
                    return false;
                }
            }
        }
        visited[course] = 2;
        courseStack.push(course);
        return true;
    }
}
