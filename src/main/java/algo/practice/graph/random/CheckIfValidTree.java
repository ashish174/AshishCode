package algo.practice.graph.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Queue;

public class CheckIfValidTree {
    public boolean validTree(int n, int[][] edges) {
        if(n==0 || edges.length != n-1){
            return false;
        }
        Map<Integer, List<Integer>> adjListMap = new HashMap<>();
        for(int[] edge : edges){
            //add edge to adj list of u
            if(!adjListMap.containsKey(edge[0])){
                adjListMap.put(edge[0], new ArrayList<>());
            }
            adjListMap.get(edge[0]).add(edge[1]);

            //add edge to adj list of v
            if(!adjListMap.containsKey(edge[1])){
                adjListMap.put(edge[1], new ArrayList<>());
            }
            adjListMap.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        //Queue for <Node, Parent>
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, -1});
        visited.add(0);
        while(!queue.isEmpty()){
            int[] pair = queue.remove();
            int u = pair[0];
            int p = pair[1];
            if(adjListMap.containsKey(u)){
                for(int v : adjListMap.get(u)) {
                    //ignore Same undirected edge
                    if(v==p){
                        continue;
                    }
                    //cycle
                    if(visited.contains(v)){
                        return false;
                    }
                    queue.add(new int[] {v, u});
                    visited.add(v);
                }
            }
        }
        return visited.size()==n;

    }
}
