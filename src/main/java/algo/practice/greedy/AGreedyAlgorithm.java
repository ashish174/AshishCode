package algo.practice.greedy;

/**
 * A greedy algorithm is used for solving optimization problems.
 * It makes the locally optimal choice at each step with the hope that
 * these local choices will lead to a globally optimum solution.
 * Examples of popular Greedy Algorithms are Fractional Knapsack, Dijkstra's algorithm, Kruskal's algorithm, Huffman coding and Prim's Algorithm
 *
 * Key Characteristics:
 * Optimal Substructure: The problem has a recursive definition.
     * The problem can be broken down into smaller sub-problems,
     * and the optimal solution to the larger problem can be constructed from the
     * optimal solutions of the sub-problems.
 * Greedy Choice Property: The algorithm chooses the best option available at each step,
     * without considering the future consequences of that choice.
 *
 * Important Note: Also, think about the base case, when value of m or n is 0. Then count should be some finite value like 1.
 */
public class AGreedyAlgorithm {}
