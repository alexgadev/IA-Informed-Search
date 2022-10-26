package Heuristics;

import DataStructures.State;

public interface Heuristic {
    double calculate(State current, State target);

    default double manhattanDistance(State current, State target){
        return Math.abs(target.getRow() - current.getRow())
                + Math.abs(target.getCol() - current.getCol());
    }

}
