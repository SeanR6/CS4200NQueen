package edu.cpp.CS4200.Project2;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    public static int generate(int i){
        return ThreadLocalRandom.current().nextInt(0, i);
    }

    public static double generate(){
        return ThreadLocalRandom.current().nextDouble();

    }
}
