package Heuristics;

import DataStructures.State;

public class ThreeTimeDiff implements Heuristic{
    @Override
    public double calculate(State current, State target) {
        double dist = manhattanDistance(current, target);
        double part = dist / 3;

        double costToTarget = current.calculateCost(target);

        return  (costToTarget * part) + // assume a third part of the path will be going up
                (0.5 * part) + // assume a third part of the path will be going up
                ((costToTarget / 2) * part); // assume a third part of the path will be in between going up and down
    }
}
