package algo.practice.dp;

/**
 * K ==> Number of floors
 * N ==> Number of Eggs
 *
 * eggDrop(N, K) ==> Minimum number of trials needed to find the critical floor in worst case.
 * eggDrop(N, K) = 1 + min {
 *                          max( eggDrop(N – 1, x – 1), eggDrop(N, K – x) ), where x is in {1, 2, …, K}
*                          }
 */
public class EggDroppingPrblm {

}
