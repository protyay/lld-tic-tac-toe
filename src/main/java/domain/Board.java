package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class Board {
    private final int boardSize;
    private final int[][] board;
    private List<Player> players;
    private Map<Integer, Character> pieceMap;
    
    public Board(int n){
        this.boardSize = n;
        this.board = new int[n][n];
        this.pieceMap = new HashMap<>();
        this.pieceMap.put(2,'X');
        this.pieceMap.put(3, 'O');
        this.pieceMap.put(0, '-');

        this.players = new ArrayList<>();
    }

    public void printBoard() {
        for(int i = 0 ; i < boardSize; i++){
            for(int j = 0 ; j < boardSize; j++){
                System.out.print(this.pieceMap.get(board[i][j]) + " ");
            }
            System.out.println();
        }
    }
}
