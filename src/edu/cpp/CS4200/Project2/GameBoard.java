package edu.cpp.CS4200.Project2;

public class GameBoard {
    static final int N = 8;
    int[] board = new int[N];
    GameBoard(){
        for(int i = 0; i < N; i++){
            board[i] = RandomGenerator.generate(N);
        }
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

    public static GameBoard move(int column, int r, GameBoard b) {
        //TODO check this for accuracy
        b.board[column] = r;
        return b;
    }
}
