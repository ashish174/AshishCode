package algo.practice.graph.random;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Largest Color Value in a Directed Graph.
 *
 * <p>Given a directed graph where each node i has a colour
 * (the i‑th character of {@code colors}), we must find the
 * maximum number of occurrences of any single colour along
 * any valid directed path. If the graph contains a cycle we
 * return {@code -1}.
 *
 * <p>The algorithm works in two phases:
 *
 * <ol>
 *   <li>Topological sorting (Kahn's algorithm) – this also
 *       detects cycles.</li>
 *   <li>Dynamic programming on the DAG. For every vertex we keep
 *       an array of size 26 that stores the best colour counts
 *       for paths ending at that vertex.</li>
 * </ol>
 *
 * <p>Time   : O((N + M) * 26)  ≈ O(N + M) <br>
 * Memory : O(N * 26 + M)
 *
 * @author  Oracle Code Assist (generated)
 */
public final class LargestColorValue {

    private LargestColorValue() {
        // utility class – prevent instantiation
    }

    /**
     * Returns the largest colour value of any directed path in the graph,
     * or {@code -1} if the graph contains a cycle.
     *
     * @param colors a string of length N where colors.charAt(i) is the colour of node i
     *               (always a lower‑case letter 'a'‑'z')
     * @param edges  directed edges; each edge is a two‑element int array [u, v]
     * @return the answer described above
     */
    public static int largestPathValue(String colors, int[][] edges) {
        final int n = colors.length();

        // -----------------------------------------------------------------
        // 1. Build adjacency list & indegree array
        // -----------------------------------------------------------------
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[n];
        for (int[] e : edges) {
            int from = e[0];
            int to   = e[1];
            graph[from].add(to);
            indegree[to]++;
        }

        // -----------------------------------------------------------------
        // 2. DP table: dp[v][c] = best count of colour c on any path ending at v
        // -----------------------------------------------------------------
        final int COLORS = 26;
        int[][] dp = new int[n][COLORS];
        for (int i = 0; i < n; i++) {
            int colIdx = colors.charAt(i) - 'a';
            dp[i][colIdx] = 1;               // single‑node path
        }

        // -----------------------------------------------------------------
        // 3. Kahn's topological sort
        // -----------------------------------------------------------------
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int processed = 0;
        int answer = 0;   // global maximum over all dp entries

        while (!q.isEmpty()) {
            int u = q.poll();
            processed++;

            // Update the global answer with the best value for vertex u
            for (int c = 0; c < COLORS; c++) {
                if (dp[u][c] > answer) {
                    answer = dp[u][c];
                }
            }

            // Propagate dp values to neighbours
            for (int v : graph[u]) {
                // For each colour try to improve dp[v][c] using dp[u][c]
                for (int c = 0; c < COLORS; c++) {
                    int candidate = dp[u][c];
                    // If the colour of v equals c we gain one more occurrence
                    if (c == colors.charAt(v) - 'a') {
                        candidate++;
                    }
                    if (candidate > dp[v][c]) {
                        dp[v][c] = candidate;
                    }
                }

                // Decrease indegree and maybe enqueue
                indegree[v]--;
                if (indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        // -----------------------------------------------------------------
        // 4. Cycle detection
        // -----------------------------------------------------------------
        if (processed != n) {          // some vertices were never reached -> cycle
            return -1;
        }
        return answer;
    }

    // -----------------------------------------------------------------
    // Simple demonstration (you may remove the main method in production)
    // -----------------------------------------------------------------
    public static void main(String[] args) {
        // Example 1: acyclic graph, answer = 3 (colour 'a' appears 3 times)
        String colors1 = "abaca";
        int[][] edges1 = {{0, 1}, {0, 2}, {2, 3}, {3, 4}};
        System.out.println(largestPathValue(colors1, edges1)); // prints 3

        // Example 2: graph contains a cycle -> -1
        String colors2 = "a";
        int[][] edges2 = {{0, 0}};
        System.out.println(largestPathValue(colors2, edges2)); // prints -1
    }
}
