public class TicTacToeBoard {
    private char[][] board;
    private char currentPlayer;

    public TicTacToeBoard() {
        board = new char[3][3];
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public boolean placeMark(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) return false;
        if (board[row][col] != ' ') return false;
        board[row][col] = currentPlayer;
        return true;
    }

    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2]) return true;
        }
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' &&
                board[0][j] == board[1][j] &&
                board[1][j] == board[2][j]) return true;
        }
        if (board[0][0] != ' ' &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) return true;
        if (board[0][2] != ' ' &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) return true;

        return false;
    }

    public boolean isDraw() {
        if (checkWin()) return false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("---+---+---");
        }
    }

    public static void main(String[] args) {
        TicTacToeBoard game = new TicTacToeBoard();
        game.placeMark(0, 0);
        game.placeMark(1, 1);
        game.placeMark(0, 1);
        game.placeMark(2, 2);
        game.placeMark(0, 2);
        game.printBoard();
        if (game.checkWin()) {
            System.out.println("Player " + game.currentPlayer + " wins!");
        } else if (game.isDraw()) {
            System.out.println("Draw!");
        } else {
            System.out.println("Game continues...");
        }
    }
}
