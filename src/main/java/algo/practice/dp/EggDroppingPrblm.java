package algo.practice.dp;

/**
 * H ==> Number of consecutive floors to test
 * N ==> Number of Eggs
 *
 * eggDrop(N, H) ==> Minimum number of trials needed to find the critical floor in worst case.
 * eggDrop(N, H) = 1 + min {
 *                          max( eggDrop(N – 1, i – 1), eggDrop(N, H – i) ) , where i is in {1, 2, …, H}
*                          }
 *                = H                                                       , if N = 1
 *                = 0                                                       , if H = 0
 *
 */
public class EggDroppingPrblm {

}
