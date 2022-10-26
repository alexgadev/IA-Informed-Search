package SearchAlgorithms;

import DataStructures.State;
import Heuristics.Heuristic;

import java.util.*;

public class BestFirstSearch implements SearchAlgorithm{
    public List<State> search(State start, State target, int[][] stateMap, Heuristic h){
        PriorityQueue<State> openSet = new PriorityQueue<State>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return Double.compare(o1.getH(), o2.getH());
            }
        }); // sorted by heuristic value

        List<State> closedSet = new ArrayList<>();

        List<State> path = new ArrayList<>();

        openSet.add(start);
        boolean solved = false;


        int iter = 0;

        while (!openSet.isEmpty() && !solved){
            State current = openSet.poll();

            path.add(current);

            if (target.equals(current)){
                solved = true;
                System.out.println("Iteracions = " + iter);
            }
            else{
                iter++;

                // apply every possible operator to the current state
                current.setNeighbours(stateMap);

                for (State neighboringState : current.getNeighbours()){
                    if (neighboringState.getState() != -1) { // avoid cliffs
                        if (!closedSet.contains(neighboringState) && !openSet.contains(neighboringState)) {
                            neighboringState.setH(h.calculate(neighboringState, target));
                            openSet.add(neighboringState);
                        }
                    }
                }
                closedSet.add(current);
            }
        }
        return solved ? path : null;
    }
}