package algo.practice.tree.binarytree.Heap;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 *
 * You are given an 2-D array points where points[i] = [xi, yi] represents the coordinates of a point on an X-Y axis plane. You are also given an integer k.
 *
 * Return the k closest points to the origin (0, 0).
 *
 * The distance between two points is defined as the Euclidean distance (sqrt((x1 - x2)^2 + (y1 - y2)^2)).
 *
 * You may return the answer in any order.
 *
 * Input: points = [[0,2],[2,2]], k = 1
 *
 * Example 1:
 * Output: [[0,2]]
 * Explanation : The distance between (0, 2) and the origin (0, 0) is 2. The distance between (2, 2) and the origin is sqrt(2^2 + 2^2) = 2.82842. So the closest point to the origin is (0, 2).
 *
 * Example 2:
 *
 * Input: points = [[0,2],[2,0],[2,2]], k = 2
 *
 * Output: [[0,2],[2,0]]
 *
 */
@Slf4j
public class KClosestPointToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        Queue<HeapNode> maxHeap = new PriorityQueue<>(((o1, o2) -> Double.compare(o2.distance, o1.distance)));
        for(int[] point : points) {
            maxHeap.add(new HeapNode(point, Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2))));
            if(maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.stream()
                .map(node -> node.point)
                .toArray(int[][]::new);

    }


    class HeapNode {
        int[] point;
        double distance;

        public HeapNode(int[] point, double distance) {
            this.point = point;
            this.distance = distance;
        }
    }

    public static void main(String[] args){
        int[][] points = new int[][]{{0,2},{2,2}};
        log.info("Closest points : {}", new KClosestPointToOrigin().kClosest(points, 1));
    }

}
