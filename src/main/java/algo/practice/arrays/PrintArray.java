package algo.practice.arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintArray {
    public static Logger logger = LoggerFactory.getLogger(PrintArray.class);

    public static void print2DSquareMatrix(int[][] arr) {
        StringBuilder rowBuilder = new StringBuilder();
        //Assuming row & column equal = square Matrix
        int rowLength = arr.length;
        rowBuilder.append("    ");
        for (int i = 0; i < rowLength; i++) {
            rowBuilder.append(i + "   ");
        }
        logger.info(rowBuilder.toString());
        rowBuilder.setLength(0);
        rowBuilder.append("--------------------------");
        logger.info(rowBuilder.toString());
        rowBuilder.setLength(0);

        for (int i = 0; i < rowLength; i++) {
            rowBuilder.append(i+" | ");
            for (int j = 0; j < rowLength; j++) {
                rowBuilder.append(arr[i][j]+"   ");
            }
            logger.info(rowBuilder.toString());
            rowBuilder.setLength(0);
        }
        logger.info("\n");
    }

    public static void main(String[] args) {
        int[][] arr = new int[3][3];
        arr[0] = new int[]{1, 0, 1};
        arr[1] = new int[]{1, 1, 1};
        arr[2] = new int[]{0, 1, 0};
        print2DSquareMatrix(arr);
    }
}
