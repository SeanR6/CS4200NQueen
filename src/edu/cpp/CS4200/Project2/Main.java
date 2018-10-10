package edu.cpp.CS4200.Project2;

public class Main {
    public static void main(String[] args) {
        GameBoard mainboard;
        ReturnStructure returnStruct;
        int gSolutionSize = 0;
        long gTime = 0;
        double gNumSolved = 0;
        int aSolutionSize = 0;
        long aTime = 0;
        double aNumSolved = 0;

        int iterations = 1;
        for (int i = 0; i < iterations; i++) {
            System.out.println("Genetic Algorithm");
            long startTime = System.currentTimeMillis();
            returnStruct = Genetic.solve();
            long endTime = System.currentTimeMillis();
            gTime += (endTime - startTime);
            gSolutionSize += returnStruct.searchCost;
            if (returnStruct.valid) {
                gNumSolved++;
            }
        }


        for (int i = 0; i < iterations; i++) {
            System.out.println("Simulated Annealing");
            long startTime = System.currentTimeMillis();
            mainboard = new GameBoard();
            returnStruct = SimulatedAnnealing.solve(mainboard);
            long endTime = System.currentTimeMillis();
            aTime += (endTime - startTime);
            aSolutionSize += returnStruct.searchCost;
            if (returnStruct.valid) {
                aNumSolved++;
            }
        }
    }
}
