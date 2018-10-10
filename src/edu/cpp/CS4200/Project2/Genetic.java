package edu.cpp.CS4200.Project2;

public class Genetic {
    static final int N = GameBoard.N;
    static int populationSize = 150;

    public static ReturnStructure solve() {
        Population p = new Population();
        Population newPop;
        int maxIterations = 5000;
        double mutationChance = .4;
        double doubleMutationChance = .05;
        int elites = (int) (populationSize * .30);
        int searchCost = 0;
        //create population.size random boards
        for (int i = 0; i < Population.size; i++) {
            p.p.add(new GameBoard());
            searchCost++;
        }
        //sort them by fitness


        for (int iterations = 0; iterations < maxIterations; iterations++) {
            newPop = new Population();

            //randomly selected parents will partake in crossover(can't select the same parent)
            //kept generally high chance
            //use an acceptance function of something like 1/x so that better values are more likely to be accepted
            //use modulus to loop over items again until the proper population size is reached
            //create 1 child, not 2
            //first create a temporary sorted array to iterate through
            GameBoard[] temp = new GameBoard[Population.size];

            for (int i = 0; i < Population.size; i++) {
                temp[i] = p.p.poll();
            }

            //using the array copy the n best boards to the next array

            for (int i = 0; i < elites; i++) {
                newPop.p.add(temp[i]);
            }

            double divisor = (double) Population.size / 20;
            int i = 0;
            while (newPop.p.size() < Population.size) {
                i = i % Population.size;
                double threshold = divisor / ((double) i + 1);
                if (RandomGenerator.generate() < threshold) {
                    //generate other parent here(can't be the same
                    int board = i;
                    while (board == i) {
                        board = RandomGenerator.generate(temp.length);
                    }
                    //crossover
                    int r = RandomGenerator.generate(N - 2) + 1;
                    int[] childBoard = new int[N];
                    for (int j = 0; j < r; j++) {
                        childBoard[j] = temp[i].board[j];
                    }
                    for (int j = r; j < N; j++) {
                        childBoard[j] = temp[board].board[j];
                    }
                    //return if pairs == 0
                    GameBoard tempBoard = new GameBoard(childBoard);
                    if (tempBoard.pairs == 0) {
                        UI.printBoard(tempBoard);
                        return new ReturnStructure(true, 0);
                    }
                    //mutate
                    double mutChance = RandomGenerator.generate();
                    if (mutChance < mutationChance) {
                        r = RandomGenerator.generate(N);
                        childBoard[r] = RandomGenerator.generate(N);
                        if (mutChance < doubleMutationChance) {
                            r = RandomGenerator.generate(N);
                            childBoard[r] = RandomGenerator.generate(N);
                        }
                    }
                    //return if pairs == 0
                    tempBoard = new GameBoard(childBoard);
                    if (tempBoard.pairs == 0) {
                        UI.printBoard(tempBoard);
                        return new ReturnStructure(true, 0);
                    }

                    //add child to the population
                    GameBoard child = new GameBoard(childBoard);
                    newPop.p.add(child);
                    searchCost++;
                }

                i++;
                p = newPop;
            }
        }
        //mutation probability
        //for every new individual that is created there is a chance of mutation

        UI.printBoard(p.p.peek());
        return new ReturnStructure(false, searchCost);
    }
}
