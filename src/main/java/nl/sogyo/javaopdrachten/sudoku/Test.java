package nl.sogyo.javaopdrachten.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Cell cell = new Cell(1,1,1);
        Cell cell2 = new Cell(1,2,3);

        List<Cell> board = new ArrayList<>();
        List<Cell> board2 = new ArrayList<>();
        board.add(cell);
        board2.add(cell);
        board.get(0).setValue(2);
        System.out.println(board2.get(0).getValue());

        HashMap<Integer, Cell> dict = new HashMap<>();
        dict.put(1,cell);
        dict.put(2,cell2);

        HashMap<Integer, Cell> dict2 = new HashMap<>();
        dict2.put(1,cell);
        dict2.put(2,cell2);
        dict2.get(2).setValue(10);

        System.out.println(dict.get(1).getValue());
        System.out.println(dict.get(2).getValue());






    }

}

