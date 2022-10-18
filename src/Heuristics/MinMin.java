package Heuristics;

import DataStructures.State;

public class MinMin implements Heuristic{

    public double calculate(State current, State target) {
        return 0.5 * manhattanDistance(current, target);
    }

    private double manhattanDistance(State current, State target){
        return Math.abs(target.getRow() - current.getRow())
                + Math.abs(target.getCol() - current.getCol());
    }
}
