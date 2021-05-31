package com.tictactoe;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import domain.Board;
import domain.Move;
import domain.Player;
import exception.InvalidInputException;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Board gameBoard = new Board(3);
        // We need to maintain a start player
        Player x = null, o = null, nextPlayer = null;

        while (true) {
            String next = sc.nextLine();
            if (next.isBlank())
                throw new IllegalArgumentException("Invalid Entry. Try again");
            if (next.equalsIgnoreCase("exit"))
                System.exit(0);

            String[] vals = next.split(" ");

            if (vals.length != 2)
                throw new InvalidInputException("Invalid command");
            // We can assume that both the player information has been recorded
            // before the move location are provided
            if (Character.isDigit(vals[0].charAt(0))) {
                // Move input
                int r = Integer.parseInt(vals[0]), c = Integer.parseInt(vals[1]);
                Move nextMove = new Move(r, c);
                boolean isValid = nextPlayer.move(nextMove, gameBoard);
                if (!isValid) {
                    System.out.println("Invalid move");
                } else {
                    if (nextPlayer.isHasWon()) {
                        System.out.println(nextPlayer.getName() + " has won");
                        System.exit(0);
                    }
                    gameBoard.printBoard();
                    nextPlayer = nextPlayer == x ? o : x;
                }
            } else {
                // Piece + Player input
                Player a = new Player(vals[0].charAt(0), vals[1], gameBoard.getBoardSize());
                if (a.getPiece() == 'X')
                    x = a;
                else
                    o = a;
                if(x != null && o != null) nextPlayer = x;
                gameBoard.printBoard();
                System.out.println("######");

            }
        }
    }
}