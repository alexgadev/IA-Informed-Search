package SearchAlgorithms;

import DataStructures.State;
import Heuristics.Heuristic;

import java.util.*;

public class AStarSearch {
    public List<State> search(State start, State target, Heuristic h, int[][] stateMap){
        PriorityQueue<State> openSet = new PriorityQueue<>();

        start.setG(0.0);
        start.setF(start.getG() + start.calculateHeuristic(target, h));

        start.setParent(null);

        // Set of discovered nodes that may need to be (re-)expanded
        openSet.add(start);

        while (!openSet.isEmpty()){
            State current = openSet.poll();
            if (current == target){
                return reconstructPath(current);
            }

            // apply every possible operator to the current state
            current.setNeighbours(stateMap);

            for (State neighbor : current.getNeighbours()){
                // totalWeight is the distance from start to the neighbor through current
                double totalWeight = current.getG() + calculateWeight(current, neighbor);

                if (totalWeight < neighbor.getG()){
                    neighbor.setParent(current);
                    neighbor.setG(totalWeight);
                    neighbor.setF(totalWeight + neighbor.calculateHeuristic(target, h));

                    if (!openSet.contains(neighbor)){
                        openSet.add(neighbor);
                    }
                }
            }
            // TODO: sort openSet by the "f" value of each state
        }
        return null;
    }

    // TODO: Calculate edge weight from current to neighbor
    private double calculateWeight(State ini, State fi){

        return -1;
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
