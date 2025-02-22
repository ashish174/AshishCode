package algo.practice.dp;

/**
 * The Algo solves a given complex problem by breaking it into sub-problems
 * and stores the results of sub-problems to avoid computing the same results again.
 * Problem must have these 2 properties:-
 *      1) Optimal Substructure :- The problem has a recursive definition. i.e. optimal solution of the problem can be obtained by optimal solutions of its sub-problems.
 *      2) Overlapping Sub-problems :-  solutions of same sub-problems are needed again and again
 *
 *
 *  It is similar to Divide & Conquer problem except one difference that the sub-problems in this case must be overlapping.
 *  In Divide & Conquer, sub-problems are distinct.
 *
 *       2) Overlapping Sub-problems
 *          - Memoization(Top Down) - (ON DEMAND) table is filled on demand
 *              sometimes avoid computing sub-problems that are not needed. Ex: LCS
 *
 *          - Tabulation (Bottom Up) - starting from the first entry, all entries are filled one by one
 *               Unlike memoization, it dont have to make nested function calls(recursion).
 *              Tabulation computes all solution from base even if some sub-problem solution is not required.
 *              Even in best case, tabulation have to solve all sub-problems.
 */
public class ADynamicProgramming {
}
