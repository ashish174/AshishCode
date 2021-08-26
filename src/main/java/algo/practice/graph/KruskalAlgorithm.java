package algo.practice.graph;

/**
 * This algo is used To find MST
 *  Appln for MST problems:
 *  1. Network design :- telephone, electrical, hydraulic, TV cable, computer, road
 *  Ex: Want to connect 10 places/offices with minimum road/telephone wire cost.
 *  2. Solving NP hard prblm - travelling salesman prblm -  find the shortest path that visits each point at least once.
 *
 * Implementation:- This is a greedy approach as we are picking the minimum edge at each step
 * 1. Sort all the edges in non-decreasing order of their weight.
 * 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If cycle is not formed, include this edge. Else, discard it.
 * 3. Repeat step#2 until there are (V-1) edges in the spanning tree.
 */
public class KruskalAlgorithm {
}
