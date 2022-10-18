package DataStructures;

import Heuristics.Heuristic;

import java.util.*;

public class State {
    // State's coordinates
    private final int row, col;

    // Parent in the path
    private State parent = null;

    private final List<State> neighbours;

    private final int state;

    private double g;
    private double f;


    public State(int row, int col, int state){
        this.row = row;
        this.col = col;
        this.state = state;

        // maximum default value
        this.g = Double.MAX_VALUE;
        this.f = Double.MAX_VALUE;

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

    public void setNeighbours(int[][] stateMap){
        // TODO: add "ifs"
        neighbours.add(new State(row - 1, col, stateMap[row - 1][col]));
        neighbours.add(new State(row, col - 1, stateMap[row][col - 1]));
        neighbours.add(new State(row, col + 1, stateMap[row][col + 1]));
        neighbours.add(new State(row + 1, col, stateMap[row + 1][col]));
    }

    public List<State> getNeighbours(){
        return this.neighbours;
    }

    public double calculateHeuristic(State target, Heuristic h){
        return h.calculate(target);
    }

    //TODO: add toString method
    public String toString(){
        return null;
    }
}
