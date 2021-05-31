package domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String name;
    private int[][] lastMove;
    private char piece;
    private int boardSize;
    private int[] row;
    private int[] col;
    private int diag;
    private int revDiag;
    private boolean hasWon;

    public Player(char piece, String name, int boardSize) {
        this.piece = piece;
        this.name = name;
        this.boardSize = boardSize;
        this.hasWon = false;
        this.row = new int[this.boardSize];
        this.col = new int[this.boardSize];
        this.diag = 0;
        this.revDiag = 0;
    }

    public boolean move(Move nextMove, Board gameBoard) {
        // Return false for the scenarios where the gameboard is NOT empty or is OUT of
        // the board
        boolean isInBoard = this.isInsideBoard(nextMove.getX(), nextMove.getY());
        boolean isCellEmpty = gameBoard.getBoard()[nextMove.getX() - 1][nextMove.getY() - 1] == 0;

        if (!isInBoard || !isCellEmpty)
            return false;
        // mark the current cell
        gameBoard.getBoard()[nextMove.getX() - 1][nextMove.getY() - 1] = this.piece == 'X' ? 2 : 3;

        // Look at all the valid directions and update the adjacent pieces the current
        // player has
        updateCache(nextMove, gameBoard);

        return true;
    }

    // We can maintain the number of consecutive marked cells for the current player
    private void updateCache(Move nextMove, Board gameBoard) {
        int r = nextMove.getX(), c = nextMove.getY();
        this.row[r - 1]++;
        this.col[c - 1]++;
        if (r == c)
            this.diag++;
        if (c == r - this.boardSize - 1)
            this.revDiag++;

        if (this.row[r - 1] == this.boardSize || this.col[c - 1] == this.boardSize || this.diag == this.boardSize
                || this.revDiag == this.boardSize)
            this.hasWon = true;
    }

    /**
     * Take care of 1-based indexing in the input of cell positions
     */
    private boolean isInsideBoard(int x, int y) {
        if (x < 1 || x > this.boardSize || y < 1 || y > this.boardSize)
            return false;
        return true;
    }
}
