package algo.practice.graph.basic;

import algo.practice.graph.coregraphclasses.DirectedGraph;

import java.util.*;

/**
 * Utility methods for topological operations.
 */
public class ParallelJobScheduler {

  /**
   * Kahn’s algorithm
   * Returns levels of jobs that can be executed in parallel.
   *
   * Each inner list represents one "time step":
   *   - All vertices in that list can be started in parallel.
   *   - All dependencies of them are in previous lists.
   *
   * Assumes the graph is a DAG. If a cycle exists, it throws IllegalStateException.
   *
   * Computes levels of jobs that can be executed in parallel from a directed acyclic graph (DAG)
   * of job dependencies.
   *
   * Each vertex represents a job, and each directed edge u -> v means "job u must complete
   * before job v can start". The method groups vertices into ordered "levels" such that:
   *  - All jobs in the same level have no remaining unmet dependencies between them and
   *    can therefore be started in parallel.
   *  - All dependencies of jobs in level i appear in some level < i.
   *
   * For example, given the following dependencies:
   *
   *   5 → 2
   *   5 → 0
   *   4 → 0
   *   4 → 1
   *   2 → 3
   *   2 → 1
   *
   * One valid parallel execution plan is:
   *   Level 0: [4, 5]       // no prerequisites, can run together
   *   Level 1: [0, 2, 1]    // depend only on 4 and/or 5
   *   Level 2: [3]          // depends on 2
   *
   * Each level in the returned list corresponds to such a set of jobs that can be executed
   * concurrently, while respecting all dependency constraints.
   *
   *
   * Approach:
   * - Compute in‑degree for every vertex using the adjacency list.
   * - Initialize a queue with all vertices whose in‑degree is zero (no dependencies).
   * - Repeatedly form “levels” by taking all currently zero‑in‑degree vertices as one parallel batch.
   * - For each vertex in the current level, decrement the in‑degree of its neighbors; when a neighbor’s in‑degree becomes zero, add it to the queue for the next level.
   * - Continue until all vertices are processed; each level represents a set of jobs that can be executed in parallel.
   *
   *
   * Now we can submit these jobs concurrently like this:
   * - Map each vertex id to a job/task.
   * - Call findParallelLevels(graph) to get the parallelizable “waves”.
   * - For each level:
   *    - Submit all its jobs to a thread pool and wait until they’re finished.
   *    - Then move to the next level.
   *
   */
  public static List<List<Integer>> findParallelLevels(DirectedGraph graph) {
        int n = graph.V;

        // Compute in-degree of each vertex
        int[] inDegree = new int[n];
        for (int u = 0; u < n; u++) {
            for (int v : graph.adjList.get(u)) {
                inDegree[v]++;
            }
        }

        // Queue of "ready" vertices (in-degree 0)
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        List<List<Integer>> levels = new ArrayList<>();
        int visitedCount = 0;

        // Process level by level
        while (!q.isEmpty()) {
            //size of given level
            int size = q.size();
            List<Integer> currentLevel = new ArrayList<>(size);

            // All nodes currently in the queue can be run in parallel
            for (int i = 0; i < size; i++) {
                int u = q.poll();
                currentLevel.add(u);
                visitedCount++;

                // Reduce in-degree of neighbors
                for (int v : graph.adjList.get(u)) {
                    inDegree[v]--;
                    if (inDegree[v] == 0) {
                        q.add(v);
                    }
                }
            }

            levels.add(currentLevel);
        }

        // If we didn't process all vertices, there is a cycle
        if (visitedCount != n) {
            throw new IllegalStateException("Graph has a cycle; cannot determine parallel levels.");
        }

        return levels;
    }

    // Small demo using your sample from TopologicalSort.main
    public static void main(String[] args) {
        DirectedGraph directedGraph = new DirectedGraph(6);

        directedGraph.addEdge(5, 2);
        directedGraph.addEdge(5, 0);
        directedGraph.addEdge(4, 0);
        directedGraph.addEdge(4, 1);
        directedGraph.addEdge(2, 3);
        directedGraph.addEdge(2, 1);

        List<List<Integer>> levels = findParallelLevels(directedGraph);

        System.out.println("Parallel execution levels:");
        int step = 0;
        for (List<Integer> level : levels) {
            System.out.println("Step " + step++ + " (run in parallel): " + level);
        }
    }
}
