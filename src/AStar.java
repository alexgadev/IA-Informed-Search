import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {
    public static State search(State start, State target){
        PriorityQueue<State> closedSet = new PriorityQueue<>();
        PriorityQueue<State> openSet = new PriorityQueue<>();

        start.setF(start.getG() + start.calculateHeuristic(target));
        openSet.add(start);

        while (!openSet.isEmpty()){
            State n = openSet.peek();
            if (n == target){
                return n;
            }

            for (State m : n.getNeighbors()){
                double totalWeight = n.getG() + m.getState();

                if (!openSet.contains(m) && !closedSet.contains(m)){
                    m.setParent(n);
                    m.setG(totalWeight);
                    m.setF(m.getG() + m.calculateHeuristic(target));
                    openSet.add(m);
                }
                else {
                    if (totalWeight < m.getG()){
                        m.setParent(n);
                        m.setG(totalWeight);
                        m.setF(m.getG() + m.calculateHeuristic(target));

                        if (closedSet.contains(m)){
                            closedSet.remove(m);
                            openSet.add(m);
                        }
                    }
                }
            }

            openSet.remove(n);
            closedSet.add(n);
        }
        return null;
    }

    public static void printPath(State target){
        State n = target;

        if(n == null)
            return;

        List<Integer> states = new ArrayList<>();

        while(n.getParent() != null){
            states.add(n.getState());
            n = n.getParent();
        }
        states.add(n.getState());
        Collections.reverse(states);

        states.forEach(System.out::println);
    }
}
