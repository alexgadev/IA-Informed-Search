package SearchAlgorithms;

import DataStructures.State;
import Heuristics.Heuristic;

import java.util.*;

public class AStarSearch implements SearchAlgorithm{
    public List<State> search(State start, State target, int[][] stateMap, Heuristic h){
        PriorityQueue<State> openSet = new PriorityQueue<>();

        start.setG(0.0);
        start.setF(start.getG() + h.calculate(start, target));

        start.setParent(null);

        // Set of discovered nodes that may need to be (re-)expanded
        openSet.add(start);

        while (!openSet.isEmpty()){
            State current = openSet.poll();
            if (current.equals(target)){
                return reconstructPath(current);
            }

            // apply every possible operator to the current state
            current.setNeighbours(stateMap);

            for (State neighbor : current.getNeighbours()) {
                if (neighbor.getState() != -1) {
                    // totalWeight is the distance from start to the neighbor through current
                    double totalWeight = current.getG() + current.calculateCost(neighbor);

                    if (totalWeight < neighbor.getG()) {
                        neighbor.setParent(current);
                        neighbor.setG(totalWeight);
                        neighbor.setF(totalWeight + h.calculate(neighbor, target));

                        if (!openSet.contains(neighbor)) {
                            openSet.add(neighbor);
                        }
                    }
                }
            }
        }
        return null;
    }

    private List<State> reconstructPath(State target){
        State n = target;

        if(n == null)
            return null;

        List<State> totalPath = new ArrayList<>();

        while(n.getParent() != null){
            totalPath.add(n);
            n = n.getParent();
        }
        totalPath.add(n);
        Collections.reverse(totalPath);

        return totalPath;
    }
}
