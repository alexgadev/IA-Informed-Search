package Heuristics;

import DataStructures.State;

public class MinMin implements Heuristic{

    public double calculate(State current, State target) {
        return 0.5 * manhattanDistance(current, target);
    }
}
