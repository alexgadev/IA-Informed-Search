import java.util.ArrayList;
import java.util.List;

public class State {
    // Position of state
    private int row, col;

    // Parent in the path
    private State parent = null;

    private List<State> neighbors;

    private int state;

    private double f = Double.MAX_VALUE;
    private double g = Double.MAX_VALUE;

    private double h;


    public State(int row, int col, int state) {
        this.row = row;
        this.col = col;
        this.state = state;
        this.h = h;
        this.neighbors = new ArrayList<>();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
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

    public void setState(int state) {
        this.state = state;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }


    public void addNeighbour(int row, int col, int state){
        State newState = new State(row, col, state);
        neighbors.add(newState);
    }

    public List<State> getNeighbors(){
        return this.neighbors;
    }

    public double calculateHeuristic(State target){
        return this.h;
    }
}
