package edu.cpp.CS4200.Project2;

public class UI {
    private static final int N = GameBoard.N;

    static void printBoard(GameBoard board) {
        int[] boardArray = board.board;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (boardArray[i] == j) {
                    System.out.print("[O]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
        System.out.println("There are " + board.calculatePairs() + " Attacking queen pairs");
    }
}
