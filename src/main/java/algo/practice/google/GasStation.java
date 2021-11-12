package algo.practice.google;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GasStation {
    public static final Logger LOGGER = LoggerFactory.getLogger(GasStation.class);

    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 5};
        int[] B = new int[]{2, 1, 3};
        int canCompleteCircuit = canCompleteCircuit(A, B);
        LOGGER.info("Gas Station Position from where Person can complete the circuit : {}", canCompleteCircuit);
    }


    /**
     * Idea is to subtract the top number with bottom number and if positive then carryforward the remainder else quit
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
