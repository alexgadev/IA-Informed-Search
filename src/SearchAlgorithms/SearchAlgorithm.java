package SearchAlgorithms;

import DataStructures.State;
import Heuristics.Heuristic;

import java.util.List;

public interface SearchAlgorithm {
    List<State> search(State start, State target, int[][] stateMap, Heuristic h);
}
