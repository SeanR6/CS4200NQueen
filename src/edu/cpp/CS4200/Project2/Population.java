package edu.cpp.CS4200.Project2;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Population {
    static int size = Genetic.populationSize;
    PriorityQueue<GameBoard> p = new PriorityQueue<>(Comparator.comparingInt(a -> a.calculatePairs()));

}
