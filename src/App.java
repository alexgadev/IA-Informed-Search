import DataStructures.State;
import Heuristics.MinMin;
import SearchAlgorithms.AStarSearch;
import SearchAlgorithms.BestFirstSearch;
import SearchAlgorithms.SearchAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        // State map's path
        String path = "mapa1.txt";

        // Read and save the state map from the file
        int[][] map = readFile(path);

        // Initial position of the problem
        int rowi = 0;
        int coli = 0;

        // End state of the problem
        int rowf = 9;
        int colf = 9;

        // Algorithm for searching
        SearchAlgorithm search = new AStarSearch();
        //SearchAlgorithm search = new BestFirstSearch();

        // Set initial and end states
        State ini = new State(rowi, coli, map[rowi][coli]);
        State fi = new State(rowf, colf, map[rowf][colf]);

        // Search the optimal path
        List<State> result = search.search(ini, fi, map,
                new MinMin()) /* new HeightXDist() */ /* new  ThreeTimeDiff()*/;

        // Print states in the optimal (or not) path given by the algorithm
        result.forEach(System.out::println);

        // Print graphical representation of the path given
        printMap(map, result);
    }

    // not the best way to read it and could have used a List<List<State>> instead of int[][]
    // but didn't have much more time
    public static int[][] readFile(String path){
        List<Integer> states = new ArrayList<>();

        int x = 0, y = 0;

        Scanner sc;
        try {
            sc = new Scanner(new File(path));
            while (sc.hasNextLine()){
                String line = sc.nextLine();

                String[] parsed = line.split(" ");
                for (String s : parsed) {
                    states.add(Integer.valueOf(s));
                }
                x = parsed.length;
                y++;
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        int[][] stateMap = new int[x][y];
        for (int n = 0; n < states.size(); n++){
            stateMap[n / x][n % y] = states.get(n);
        }

        return stateMap;
    }

    public static void printMap(int[][] map, List<State> path){
        for (int i = 0; i < map.length; i++){
            String show = "";

            for (int j = 0; j < map[0].length; j++){
                String ANSI_GREEN = "\u001B[32;1m";
                String ANSI_RED = "\u001B[31m";
                String resetCode = "\u001B[0m";

                boolean highlight = false;
                for (State state: path) {
                    if (state.isPosition(i, j)){
                        highlight = true;
                        break;
                    }
                }

                if (highlight) {
                    show += ANSI_GREEN + map[i][j] + resetCode;
                }
                else {
                    if (map[i][j] == -1) {
                        show += ANSI_RED + "X" + resetCode;
                    }
                    else {
                        show += map[i][j];
                    }
                }
                show += " | ";
            }
            System.out.println(show);
        }
    }
}
