package algo.practice.graph.basic;

import java.util.List;
import java.util.Stack;

import algo.practice.graph.coregraphclasses.DirectedGraph;
import algo.practice.graph.coregraphclasses.GraphVisualizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Assumption: Graph is DAG(acyclic). If there is a cycle, then we have to modify code.
 *
 * Ordering of tasks: if there is a edge u→v, vertex u comes before v in the ordering
 * i.e. u is pre-requisite task for v. u must be completed first before v.
 *
 * For finding dependencies & scheduling jobs accordingly.
 * It is done for a directed acyclic graph(DAG) only.
 * Appln:
 * 1. Build Project - build library first & then project
 * 2. Task scheduling -interdependent tasks
 * 3. Pre-requisite problems - University course structure - do basic then advance
 *
 *
 * How: Run a DFS on all vertices, and once a vertice V is complete(all its adjacency list is pushed),
 * push the vertice  into the stack last. This ensure all dependency of V is already in stack and then only V is pushed.
 *
 */
public class TopologicalSorting {
  public static final Logger LOGGER = LoggerFactory.getLogger(TopologicalSorting.class);

  public static void doTopologicalSort(DirectedGraph directedGraph) {
    boolean[] visited = new boolean[directedGraph.V];
    Stack<Integer> stack = new Stack<>();
    for (int u = 0; u < directedGraph.V; u++) {
      if (!visited[u]) {
        topologicalSortUtil(directedGraph, u, stack, visited);
      }
    }
    System.out.print("Topological Sort for given directed acyclic graph is : ");
    while (!stack.isEmpty()) {
      System.out.print(stack.pop() + " ");
    }
  }

  /**
   * Note that a vertex is pushed to stack only when all of its adjacent vertices (and their
   * adjacent vertices and so on) are already in the stack.
   */
  private static void topologicalSortUtil(
      DirectedGraph directedGraph, int u, Stack<Integer> stack, boolean[] visited) {
    visited[u] = true;
    List<Integer> adjListOfU = directedGraph.adjList.get(u);
    for (Integer v : adjListOfU) {
      if (!visited[v]) {
        topologicalSortUtil(directedGraph, v, stack, visited);
      }
    }
    stack.push(u);
  }

  public static void main(String[] args) {
    DirectedGraph directedAcyclicGraph = new DirectedGraph(6);
    directedAcyclicGraph.addEdge(5, 2);
    directedAcyclicGraph.addEdge(5, 0);
    directedAcyclicGraph.addEdge(4, 0);
    directedAcyclicGraph.addEdge(4, 1);
    directedAcyclicGraph.addEdge(2, 3);
    directedAcyclicGraph.addEdge(3, 1);
    new GraphVisualizer(directedAcyclicGraph).draw();
    doTopologicalSort(directedAcyclicGraph);
  }
}
