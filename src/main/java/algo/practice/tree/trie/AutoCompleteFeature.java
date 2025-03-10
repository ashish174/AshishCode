package algo.practice.tree.trie;

/**
 * Return Prefix Matching results Sorted based on frequency.
 * More frequently search strings should be on top.
 * Return Top 5 suggestions for each prefix.
 * Approach 1:
 * Do a search upto a depth of 10 for the given prefix. Get all valid words/sentence.
 * And return top 5 based on some ranking or just frequency of these word being searched
 *
 * Approach 2:
 * Another alternate solution will cache top 5 or 10 words people searched for any prefix on each Node or in some external cache.
 * Here you dont need to limit depth to 10 or any threshold. Plus it will be fast.
 * Ex: For Node with prefix 'de', it will have top 5 search Item cached in Node/external Cache.
 * Node {be : beer(36), best(27), before(20), bet(15), better(12)}
 * Node {be} : RedisCache(or Cassandra) {be : [beer(36), best(27), before(20), bet(15), better(12)]}
 */
public class AutoCompleteFeature {}
