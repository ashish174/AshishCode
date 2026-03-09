package algo.practice.graph.shortestpath;
import java.util.*;

/**
 * The A* algorithm is a pathfinding and graph traversal algorithm used to find the shortest path between two points on a graph.
 * It's known for its efficiency and optimality, making it a popular choice in various applications
 * like game development, robotics, and route planning
 */


/**
 * A* (A-Star) Shortest Path Algorithm
 *
 * Problem Statement
 * -----------------
 * Given a weighted graph (non-negative edge costs), a start node, and a goal node,
 * find the minimum-cost path from start to goal. A heuristic function h(n) estimates
 * the cost from node n to the goal.
 *
 * Requirements:
 * - Edge costs are non-negative.
 * - Heuristic should be:
 *   - Admissible: never overestimates the true cost to the goal.
 *   - Preferably consistent (monotonic).
 *
 * High-Level Idea
 * ---------------
 * For each node n, A* uses:
 * - g(n): exact cost from start to n found so far.
 * - h(n): heuristic estimate from n to the goal.
 * - f(n) = g(n) + h(n): priority used by the algorithm.
 *
 * Algorithm Sketch
 * ----------------
 * 1. Initialize:
 *    - g(start) = 0
 *    - f(start) = h(start)
 *    - Put start in a priority queue ordered by f
 *
 * 2. While the queue is not empty:
 *    - Pop the node n with the smallest f(n)
 *    - If n is goal: reconstruct and return the path
 *    - For each neighbor v of n:
 *        tentativeG = g(n) + cost(n, v)
 *        If tentativeG < g(v):
 *           update g(v)
 *           set parent(v) = n
 *           compute f(v) = g(v) + h(v)
 *           push v into the priority queue
 *
 * 3. If the queue empties without reaching goal, no path exists.
 *
 * Example Use Case
 * ----------------
 * Nodes are cities; edges are roads with distances; heuristic is straight-line
 * distance to the goal city. A* finds a shortest driving route.
 *
 * Real-World Use Cases
 * --------------------
 * - Game development: NPC pathfinding on grids and maps.
 * - Robotics: robot navigation and obstacle avoidance.
 * - Navigation / GPS: route planning with map data.
 * - Network routing: finding low-latency paths.
 * - AI planning: solving puzzles and search problems.
 * - Logistics: route optimization in warehouses and delivery systems.
 *
 * How to Use This Class
 * ---------------------
 * - Create a Graph instance.
 * - Add edges with addEdge(from, to, cost, bidirectional).
 * - Implement a Heuristic that returns an estimate from any node to the goal.
 * - Call findPath(graph, start, goal, heuristic).
 * - You get a List of nodes from start to goal (inclusive), or an empty list if no path.
 */
public class AStar {

    /**
     * Represents a directed weighted edge in the graph.
     * T is the type of the node identifier (e.g., String, Integer).
     */
    public static class Edge<T> {
        final T to;        // Target node of the edge.
        final double cost; // Non-negative cost to traverse this edge.

        public Edge(T to, double cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    /**
     * Simple adjacency-list graph representation.
     * T is the type of the node identifier (e.g., String, Integer).
     */
    public static class Graph<T> {
        // For each node, store the list of outgoing edges.
        private final Map<T, List<Edge<T>>> adjacency = new HashMap<>();

        /**
         * Add a directed edge (from -> to) with a given cost.
         *
         * @param from source node
         * @param to   target node
         * @param cost non-negative edge cost
         */
        public void addDirectedEdge(T from, T to, double cost) {
            adjacency
                    .computeIfAbsent(from, k -> new ArrayList<>())
                    .add(new Edge<>(to, cost));

            // Ensure target node is present in the adjacency map
            // even if it has no outgoing edges (yet).
            adjacency.computeIfAbsent(to, k -> new ArrayList<>());
        }

        /**
         * Convenience method to add an edge.
         *
         * @param from         source node
         * @param to           target node
         * @param cost         non-negative edge cost
         * @param bidirectional if true, add edges from->to and to->from
         */
        public void addEdge(T from, T to, double cost, boolean bidirectional) {
            addDirectedEdge(from, to, cost);
            if (bidirectional) {
                addDirectedEdge(to, from, cost);
            }
        }

        /**
         * Get outgoing edges (neighbors) of a node.
         *
         * @param node the node whose neighbors are requested
         * @return list of edges (possibly empty, never null)
         */
        public List<Edge<T>> getNeighbors(T node) {
            return adjacency.getOrDefault(node, Collections.emptyList());
        }

        /**
         * Get all nodes in the graph.
         *
         * @return set of node identifiers
         */
        public Set<T> getNodes() {
            return adjacency.keySet();
        }
    }

    /**
     * Heuristic function h(n).
     * Should estimate the cost from current to goal.
     * For optimal A*, it must never overestimate the true cost.
     */
    @FunctionalInterface
    public interface Heuristic<T> {
        double estimate(T current, T goal);
    }

    /**
     * Internal helper to store a node and its f-score in the priority queue.
     */
    private static class NodeRecord<T> {
        final T node;        // Node identifier.
        final double fScore; // f(n) = g(n) + h(n).

        NodeRecord(T node, double fScore) {
            this.node = node;
            this.fScore = fScore;
        }
    }

    /**
     * Run the A* algorithm to find the shortest path from start to goal.
     *
     * @param graph     graph on which to perform the search
     * @param start     start node
     * @param goal      goal node
     * @param heuristic heuristic function h(n)
     * @param <T>       type of node identifier
     * @return list of nodes from start to goal (inclusive),
     *         or empty list if no path exists
     */
    public static <T> List<T> findPath(
            Graph<T> graph,
            T start,
            T goal,
            Heuristic<T> heuristic
    ) {
        // Best known cost from start to each node.
        Map<T, Double> gScore = new HashMap<>();

        // For reconstructing the path: child -> parent.
        Map<T, T> cameFrom = new HashMap<>();

        // Priority queue ordered by fScore (smallest first).
        PriorityQueue<NodeRecord<T>> openSet = new PriorityQueue<>(
                Comparator.comparingDouble(nr -> nr.fScore)
        );

        final double INF = Double.POSITIVE_INFINITY;

        // Initialize all gScores to infinity.
        for (T node : graph.getNodes()) {
            gScore.put(node, INF);
        }
        // Start node has cost 0.
        gScore.put(start, 0.0);

        // f(start) = g(start) + h(start) = 0 + h(start).
        double startF = heuristic.estimate(start, goal);
        openSet.add(new NodeRecord<>(start, startF));

        // Main A* loop
        while (!openSet.isEmpty()) {
            // Take node with smallest fScore from the open set.
            NodeRecord<T> currentRecord = openSet.poll();
            T current = currentRecord.node;

            // If we reached the goal, reconstruct and return the path.
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            // Get the gScore we currently believe for this node.
            double currentG = gScore.getOrDefault(current, INF);

            // Compute what the "correct" fScore would be from stored g and heuristic.
            double expectedF = currentG + heuristic.estimate(current, goal);

            // If this record is stale (worse than our latest view), skip it.
            if (expectedF < currentRecord.fScore - 1e-9) {
                continue;
            }

            // Explore neighbors.
            for (Edge<T> edge : graph.getNeighbors(current)) {
                T neighbor = edge.to;
                double cost = edge.cost;

                // A* normally assumes non-negative edge costs.
                if (cost < 0) {
                    throw new IllegalArgumentException("Edge cost must be non-negative for A*.");
                }

                // Tentative g-score for neighbor via current.
                double tentativeG = currentG + cost;
                double neighborG = gScore.getOrDefault(neighbor, INF);

                // If this path to neighbor is better, record it.
                if (tentativeG < neighborG) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeG);

                    double fScore = tentativeG + heuristic.estimate(neighbor, goal);

                    // Push new/updated record; stale ones will be ignored when popped.
                    openSet.add(new NodeRecord<>(neighbor, fScore));
                }
            }
        }

        // No path found.
        return Collections.emptyList();
    }

    /**
     * Reconstruct path from start to current using cameFrom map.
     *
     * @param cameFrom map: node -> parent node
     * @param current  goal node
     * @param <T>      node identifier type
     * @return list of nodes from start to current
     */
    private static <T> List<T> reconstructPath(Map<T, T> cameFrom, T current) {
        LinkedList<T> path = new LinkedList<>();
        path.addFirst(current);

        // Follow parent links back to the start.
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.addFirst(current);
        }
        return path;
    }

    /**
     * Simple demonstration of how to use the A* implementation.
     * This main builds a small graph, uses a trivial heuristic (0 everywhere),
     * so A* behaves exactly like Dijkstra's algorithm.
     */
    public static void main(String[] args) {
        // Create a graph with String node identifiers.
        Graph<String> graph = new Graph<>();

        // Build a sample undirected graph:
        //
        // A --1-- B --2-- C
        // |       |
        // 4       1
        // |       |
        // D --1-- E --3-- F
        //
        graph.addEdge("A", "B", 1, true);
        graph.addEdge("B", "C", 2, true);
        graph.addEdge("A", "D", 4, true);
        graph.addEdge("B", "E", 1, true);
        graph.addEdge("D", "E", 1, true);
        graph.addEdge("E", "F", 3, true);

        String start = "A";
        String goal = "F";

        // Trivial heuristic: always 0.
        // This makes A* identical to Dijkstra's algorithm.
        Heuristic<String> heuristic = (current, g) -> 0.0;

        List<String> path = findPath(graph, start, goal, heuristic);

        if (path.isEmpty()) {
            System.out.println("No path found from " + start + " to " + goal);
        } else {
            System.out.println("Shortest path from " + start + " to " + goal + ": " + path);
        }
    }
}

