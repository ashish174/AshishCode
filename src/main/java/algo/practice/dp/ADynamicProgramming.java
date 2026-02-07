package algo.practice.dp;

/**
 * The Algo solves a given complex problem by breaking it into sub-problems
 * and stores the results of sub-problems to avoid computing the same results again.
 * Problem must have these 2 properties:-
 *      1) Optimal Substructure :- The problem has a recursive definition. i.e. optimal solution of the problem can be obtained by optimal solutions of its sub-problems.
 *      2) Overlapping Sub-problems :-  solutions of same sub-problems are needed again and again
 *
 *      Important Note: Also, think about the base case, when value of m or n is 0. Then count should be some finite value like 1.
 *
 *
 *  It is similar to Divide & Conquer problem except one difference that the sub-problems in this case must be overlapping.
 *  In Divide & Conquer, sub-problems are distinct.
 *
 *       2) Overlapping Sub-problems
 *          - Memoization(Top Down) - (ON DEMAND) table is filled on demand
 *              sometimes avoid computing sub-problems that are not needed. Ex: LCS
 *              Note: Before filling check that memoization cell is empty so as to avoid overwrite
 *
 *          - Tabulation (Bottom Up) - starting from the first entry, all entries are filled one by one
 *               Unlike memoization, it dont have to make nested function calls(recursion).
 *              Tabulation computes all solution from base even if some sub-problem solution is not required.
 *              Even in best case, tabulation have to solve all sub-problems.
 *              Note: Before filling, No need to check if Tabulation cell is empty as we are building bottom up.
 *              Also, array size here is {m+1, n+1}, bcoz we need to store base(boundary) case.
 *
 *
 *    Note: Instead of using static variables, prefer to use class variables.
 *     public static int[][] tabulation;
 *     public static int[][] memoization;
 *
 *     ex:
 *
 *     int[][] memoization;
 *
 *     editDistance(int m, int n) {
 *         memoization = new int[m+1][n+1]
 *         doEditDistance(m, n, memoization);
 *     }
 *
 *     doEditDistance(int m, int n, int[][] memoization){
 *
 *     }
 */
public class ADynamicProgramming {}
