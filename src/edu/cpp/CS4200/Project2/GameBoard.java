package edu.cpp.CS4200.Project2;

public class GameBoard {
    static final int N = 21;
    int[] board = new int[N];
    public double fitnessScore;
    GameBoard(){
        for(int i = 0; i < N; i++){
            board[i] = RandomGenerator.generate(N);
        }
    }

    GameBoard(int[] input) {
        board = input;
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

    public void fitnessFunc() {
        //this allows for the fitness function to be increasing with a better score.
        //best possible score is 1
        int n = calculatePairs();
        fitnessScore = 1 / (1 + n);
    }

}
