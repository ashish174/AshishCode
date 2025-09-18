package algo.practice.tree.binarytree.Heap;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given an array of integers stones where stones[i] represents the weight of the ith stone.
 *
 * We want to run a simulation on the stones as follows:
 *
 * At each step we choose the two heaviest stones, with weight x and y and smash them togethers
 * If x == y, both stones are destroyed
 * If x < y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * Continue the simulation until there is no more than one stone remaining.
 *
 * Return the weight of the last remaining stone or return 0 if none remain.
 *
 * Example 1:
 *
 * Input: stones = [2,3,6,2,4]
 *
 * Output: 1
 * Explanation:
 * We smash 6 and 4 and are left with a 2, so the array becomes [2,3,2,2].
 * We smash 3 and 2 and are left with a 1, so the array becomes [1,2,2].
 * We smash 2 and 2, so the array becomes [1].
 *
 *
 *
 */
@Slf4j
public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        // Priority Queue by default is minHeap. So need to pass a custom comparator, to make it maxheap
        Queue<Integer> maxHeap = new PriorityQueue<>(((o1,o2) -> o2-o1));
        //create max heap
        for(int stone : stones) {
            maxHeap.add(stone);
        }
        // Perform destroy

        while(maxHeap.size() > 1) {
            int firstWeight = maxHeap.poll();
            int secondWeight = maxHeap.poll();
            int remainingWeight = firstWeight - secondWeight;
            if(remainingWeight!=0) {
                maxHeap.add(remainingWeight);
            }
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    public static void main(String[] args){
        int[] stones = {2,3,6,2,4};
        log.info("final stone weight : {}", new LastStoneWeight().lastStoneWeight(stones));

    }

}
