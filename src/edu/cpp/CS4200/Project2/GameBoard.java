package edu.cpp.CS4200.Project2;

public class GameBoard {
    static final int N = 21;
    int[] board = new int[N];
    int pairs;
    GameBoard(){
        for(int i = 0; i < N; i++){
            board[i] = RandomGenerator.generate(N);
        }
        pairs = calculatePairs();
    }

    GameBoard(int[] input) {
        board = input;
        pairs = calculatePairs();
    }

    public int calculatePairs(){
        int h = 0;
        int diff;
        for(int i = 0; i < N-1; i++){
            for(int j = i+1; j < N; j++){
                if(board[i] == board[j]){
                    ++h;
                }
                diff=j-i;
                if(board[i] + diff < N){
                    if(board[i] + diff == board[j]){
                        ++h;
                    }
                }
                if(board[i] - diff >= 0){
                    if(board[i] - diff == board[j]){
                        ++h;
                    }
                }
            }
        }

        return h;
    }


}
