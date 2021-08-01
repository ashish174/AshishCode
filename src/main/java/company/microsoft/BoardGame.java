package company.microsoft;

public class BoardGame {

    public int[] solution(String[] board, String moves) {
        // write your code in Java SE 8
        int boardLength = board.length;
        int boardWidth = board[0].length();
        char[][] charArray = new char[boardLength][boardWidth];
        for (int i = 0; i < boardLength; i++) {
            charArray[i] = board[i].toCharArray();
        }
        int row = 0;
        int column = 0;
        for (char ch : moves.toCharArray()) {
            switch (ch) {
                case 'D':
                    if (row + 1 < boardLength && charArray[row + 1][column] == '.') {
                        row++;
                    }
                    break;
                case 'U':
                    if (row - 1 >= 0 && charArray[row - 1][column] == '.') {
                        row--;
                    }
                    break;
                case 'R':
                    if (column + 1 < boardWidth && charArray[row][column + 1] == '.') {
                        column++;
                    }
                    break;

                case 'L':
                    if (column - 1 >= 0 && charArray[row][column - 1] == '.') {
                        column--;
                    }
                    break;
                default:
                    break;
            }
        }
        return new int[]{row, column};
    }
}
