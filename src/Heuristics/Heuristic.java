package Heuristics;

import DataStructures.State;

public interface Heuristic {
    double calculate(State current, State target);

}
