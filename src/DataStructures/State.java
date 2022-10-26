package DataStructures;

import java.util.ArrayList;
import java.util.List;

public class State implements Comparable<State>{
    private final int row, col;

    // Parent in the path
    private State parent = null;

    private final List<State> neighbours;

    private final int state;

    private double g;
    private double f;

    private double h;


    public State(int row, int col, int state){
        this.row = row;
        this.col = col;
        this.state = state;

        // maximum default value
        this.g = Double.MAX_VALUE;
        this.f = Double.MAX_VALUE;

        // neighbors won't be discovered until state has been dealt with
        this.neighbours = new ArrayList<>();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public State getParent() {
        return parent;
    }

    // parent is only set once we know a path from state "X" to "this"
    // is the optimal one
    public void setParent(State parent) {
        this.parent = parent;
    }

    public int getState() {
        return state;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getG() {
        return g;
    }

    public void setH(double h) {this.h = h;}

    public double getH(){return h;}

    // discovering neighbors respecting state map's boundaries
    // cliffs are handled at each algorithm but could have been implemented in
    // this method
    public void setNeighbours(int[][] stateMap){
        if (!(row - 1 < 0))
            neighbours.add(new State(row - 1, col, stateMap[row - 1][col]));
        if (!(col - 1 < 0))
            neighbours.add(new State(row, col - 1, stateMap[row][col - 1]));
        if (!(row + 1 > stateMap.length - 1))
            neighbours.add(new State(row + 1, col, stateMap[row + 1][col]));
        if (!(col + 1 > stateMap[0].length - 1)) {
            neighbours.add(new State(row, col + 1, stateMap[row][col + 1]));
        }
    }

    public List<State> getNeighbours(){
        return this.neighbours;
    }

    // calculation of formal specification of cost given in the assignment
    public double calculateCost(State target){
        int dif = target.getState() - this.getState();
        return dif >= 0 ? 1.0 + dif : 0.5;
    }

    // check if state is in position x and y
    public boolean isPosition(int x, int y){
        return (x == this.row) && (y == this.col);
    }

    // overriding compareTo comparing f values for A*'s PriorityQueue
    // could have implemented a Comparator as done in the Best First algorithm
    @Override
    public int compareTo(State s) {
        return Double.compare(this.getF(), s.getF());
    }

    // checks if sates are equals by their position in the state map
    @Override
    public boolean equals(Object obj) {
        State o = (State) obj;
        return (this.row == o.getRow()) && (this.col == o.getCol());
    }

    @Override
    public String toString(){
        return "Coordinates = [" + getRow() + "," + getCol() + "]\tState = " + getState();
    }
}
