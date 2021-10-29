package company.adobe;

/**
 * Return the list of strings from the given list of strings
 * which matches to the given pattern string on the basis of frequency and position of characters.
 *
 * Example : 1
 * Pattern : ‘abb’
 * Given List of Strings : [‘fee’, ‘ptp’ , ‘zmm’]
 * Answer : [‘fee’, ‘zmm’]
 * Example 2 :
 * Pattern : ‘papq’
 * Given List of Strings : [‘dada’, ‘dadd’ , ‘dadk’]
 * Answer : [‘dadk’]
 *
 * a - 1
 * b - 2
 *
 * zmm, dbb
 *
 * [1,2]
 *
 * f -> a
 * f -> b  - abb - O(m)
 *
 * a -> f
 * b -> d
 *
 * fee, fds
 *
 * aba, fee
 * a -> f     f -> a   (abb)
 * b -> e     e -> b
 * c -> e
 *
 *  a -> f  (fef)     f -> a   (abb)
 *  b -> e            e -> b
 *
 *
 *
 *
 *
 *
 * fds
 * f -> a
 * d -> b
 *
 *
 *
 * p -> a
 * t -> b = aba
 *
 * n (m) = nm
 *
 *
 * CHaracter -> Character  - O(m)
 *
 *
 *
 *
 *
 *
 */
public class AdobeSample {

}
