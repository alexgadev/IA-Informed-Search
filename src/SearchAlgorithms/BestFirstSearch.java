package SearchAlgorithms;

import DataStructures.State;
import Heuristics.Heuristic;

import java.util.*;

public class BestFirstSearch {
    public List<State> search(State start, State target, Heuristic h, int[][] stateMap){
        PriorityQueue<State> openSet = new PriorityQueue<>(); // sorted by heuristic value
        List<State> closedSet = new ArrayList<>();

        openSet.add(start);
        boolean solved = false;

        List<State> path = new ArrayList<>();

        while (!openSet.isEmpty() && !solved){
            State current = openSet.poll();

            path.add(current);

            System.out.println(current + " "); // for debugging

            if (target == current){
                solved = true;
            }
            else{
                for (State neighboringState : current.getNeighbours()){
                    if (!closedSet.contains(neighboringState) && !openSet.contains(neighboringState)){
                        double nHeuristic = h.calculate(neighboringState, target);
                        openSet.add(neighboringState);
                        //openSet.stream().sorted(); //TODO: Sort by heuristic value
                        // pends = afegir_orden(pends, [X, cami + op, h(X)])
                        //                                 ---------
                // actual distance from current to neighbor    ^
                    }
                }
                closedSet.add(current);
            }
        }
        return solved ? path : null;
    }
}