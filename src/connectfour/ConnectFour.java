package connectfour;

import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final char EMPTY_SLOT = '-';
    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';
    private char[][] board = new char[ROWS][COLS];
    private char currentPlayer = PLAYER_ONE;

    public ConnectFour() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY_SLOT;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean placeMark(int col) {
        if (col < 0 || col >= COLS) {
            System.out.println("Column out of bounds. Try again.");
            return false;
        }

        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][col] == EMPTY_SLOT) {
                board[i][col] = currentPlayer;
                return true;
            }
        }

        System.out.println("Column is full. Try again.");
        return false;
    }

    public boolean checkForWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private boolean checkRows() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j] == currentPlayer && board[i][j + 1] == currentPlayer &&
                    board[i][j + 2] == currentPlayer && board[i][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == currentPlayer && board[i + 1][j] == currentPlayer &&
                    board[i + 2][j] == currentPlayer && board[i + 3][j] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        // Check for downward diagonals
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j] == currentPlayer && board[i + 1][j + 1] == currentPlayer &&
                    board[i + 2][j + 2] == currentPlayer && board[i + 3][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        // Check for upward diagonals
        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j] == currentPlayer && board[i - 1][j + 1] == currentPlayer &&
                    board[i - 2][j + 2] == currentPlayer && board[i - 3][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConnectFour game = new ConnectFour();
        game.printBoard();

        while (true) {
            System.out.println("Player " + game.getCurrentPlayer() + ", enter your move (column 0-6): ");
            int col = scanner.nextInt();

            if (game.placeMark(col)) {
                game.printBoard();
                if (game.checkForWin()) {
                    System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                    break;
                }
                game.changePlayer();
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        scanner.close();
    }
}
