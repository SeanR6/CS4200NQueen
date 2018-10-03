package edu.cpp.CS4200.Project2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimulatedAnnealing {
    public static ReturnStructure solve(GameBoard board){
        //try this value, may need to get a better number later
        double temp = 10;
        GameBoard child;
        //used to keep track of nodes calculated that turn, is emptied once a child has been chosen
        List<GameBoard> calculatedList = new LinkedList<>();
        //this is the temperature threshold for knowing when to stop
        //might need to change to num of iterations later, or fine tune this number
        double threshold = .1;
        double probThreshold;
        boolean isSolved = false;
        int deltaScore;
        double rand;
        int searchCost = 0;

        while(temp >= threshold){
            //get a child
            //TODO prevent child from pointing to board

            int column = RandomGenerator.generate(GameBoard.N);
            int currentRow = board.board[column];
            int r = currentRow;
            while(r == currentRow){
                r = RandomGenerator.generate(GameBoard.N);
            }
            child = GameBoard.move(column, r, board.);
            searchCost++;
            //if delta score is positive
            //let that be the child
            //if cost is 0 stop
            deltaScore = scoreDiff(board.calculatePairs(), child.calculatePairs());
            if(!calculatedList.contains(child)) {
                calculatedList.add(child);
                //check for correctness
                if (deltaScore > 0) {
                    board = child;
                    calculatedList.clear();
                    if(board.calculatePairs() == 0){
                        isSolved = true;
                        break;
                    }
                }else {
                    //else
                    //calculate probability threshold based off of cost
                    //roll the dice between 0 and 1
                    //if it is under the value, it is the child
                    probThreshold = probabilityFunction(deltaScore, temp);
                    rand = RandomGenerator.generate();
                    if(rand < probThreshold){
                        board = child;
                        calculatedList.clear();
                    }
                }
            }
            //then calculate the new temp
            temp = getNewTemp(temp);
        }

        //print solution
        //return search cost and whether or not it is solved
        return new ReturnStructure(isSolved, searchCost);
    }
    private static double getNewTemp(double n){
        //may need to modify the decay rate
        return 100/n;
    }

    private static double probabilityFunction(int deltaScore, double temp){
        return Math.exp(deltaScore/temp);
    }

    private static int scoreDiff(int p, int c){
        return p - c;
    }
}
