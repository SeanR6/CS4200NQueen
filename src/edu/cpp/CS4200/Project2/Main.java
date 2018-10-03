package edu.cpp.CS4200.Project2;

public class Main {
    public static void main(String[] args) {
	    GameBoard mainboard = new GameBoard();
        SimulatedAnnealing.solve(mainboard);
    }
}
