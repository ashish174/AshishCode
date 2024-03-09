package algo.practice.google;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GasStation {
    public static final Logger LOGGER = LoggerFactory.getLogger(GasStation.class);

    /**
     * Given two integer arrays A and B of size N. There are N gas stations along a circular route, where the amount of gas at station i is A[i].
     *
     * You have a car with an unlimited gas tank and it costs B[i] of gas to travel from station i to its next station (i+1).
     * You begin the journey with an empty tank at one of the gas stations.
     * Return the minimum starting gas station's index if you can travel around the circuit once, otherwise return -1.
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 5};
        int[] B = new int[]{2, 1, 3};
        int canCompleteCircuit = canCompleteCircuit(A, B);
        int canCompleteCircuitEfficient = canCompleteCircuitEfficient(A, B);
        LOGGER.info("Gas Station Position from where Person can complete the circuit : {}", canCompleteCircuit);
        LOGGER.info("Efficient Gas Station Position from where Person can complete the circuit : {}", canCompleteCircuitEfficient);
    }

    /**
     * In Efficient version, we can check at any position where fuel requirement not met, we can set our i to next position directly
     * as carryforward is always >=0 and hence before failure position all points will fail at current failure position
     *
     * @param A
     * @param B
     * @return
     */
    public static int canCompleteCircuitEfficient(int[] A, int[] B) {
        for (int i = 0; i < A.length; i++) {
            int excessFuel = 0;
            boolean gasStationFound = true;
            for (int j = 0; j < A.length; j++) {
                int currGasStationPosition = (i + j) % A.length;
                excessFuel = excessFuel + A[currGasStationPosition] - B[currGasStationPosition];
                if (excessFuel < 0) {
                    i = currGasStationPosition;
                    gasStationFound = false;
                    break;
                }
            }
            if (gasStationFound) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Idea is to subtract the top number with bottom number and if positive then carryforward the remainder else quit
     * This runs in n^2
     *
     * @param A
     * @param B
     * @return
     */
    public static int canCompleteCircuit(int[] A, int[] B) {
        for (int i = 0; i < A.length; i++) {
            boolean canCompleteCircuit = checkIfCanCompleteCircuitFromPosition(A, B, i);
            if (canCompleteCircuit) {
                return i;
            }
        }
        return -1;
    }

    private static boolean checkIfCanCompleteCircuitFromPosition(int[] A, int[] B, int gasStationPosition) {
        int excessFuel = 0;
        for (int i = 0; i < A.length; i++) {
            int currGasStationPosition = (gasStationPosition + i) % A.length;
            excessFuel = excessFuel + A[currGasStationPosition] - B[currGasStationPosition];
            if (excessFuel < 0) {
                return false;
            }
        }
        return true;
    }

}
