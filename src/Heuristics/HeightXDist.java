package Heuristics;

import DataStructures.State;

public class HeightXDist implements Heuristic{
    @Override
    public double calculate(State current, State target) {
        return current.calculateCost(target) * manhattanDistance(current, target);
    }
}
