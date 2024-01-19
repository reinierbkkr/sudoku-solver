package nl.sogyo.javaopdrachten.sudoku;

import nl.sogyo.javaopdrachten.sudoku.exceptions.CellHasValueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    List<Cell> board = new ArrayList<>();
    Map<SubSetType, List<SubSet>> subSetListsMap = new HashMap<SubSetType, List<SubSet>>(){{
        put(SubSetType.Row, new ArrayList<SubSet>());
        put(SubSetType.Column, new ArrayList<SubSet>());
        put(SubSetType.Block, new ArrayList<SubSet>());
    }};

    public Board(String input) {
        makeBoard(input);
        initializeSubSetsInSubSetLists();
        fillSubSetsWithCells();
    }

    public void makeBoard(String input) {
        List<Integer> values = inputToList(input);
        int index = 0;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                board.add(new Cell(row, column, values.get(index)));
                index ++;
            }
        }
    }

    public static List<Integer> inputToList(String input) {
        List<Integer> values = new ArrayList<>();
        String[] splitinput = input.split("");
        for (String value : splitinput) {
            // System.out.println(value);
            values.add(Integer.parseInt(value));
        }
        return values;
    }

    void initializeSubSetsInSubSetLists(){
        for (Map.Entry<SubSetType, List<SubSet>> entry : subSetListsMap.entrySet()) {
            for (int i = 0; i < 9; i++) { // refactor
                entry.getValue().add(new SubSet(entry.getKey()));
            }
        }
    }

    void fillSubSetsWithCells() {
        for (Cell cell : board){
            subSetListsMap.get(SubSetType.Row).get(cell.getRow()).addCell(cell);
            subSetListsMap.get(SubSetType.Column).get(cell.getColumn()).addCell(cell);
            subSetListsMap.get(SubSetType.Block).get(cell.getBlock()).addCell(cell);
        }
    }

    public void setValueSingleOptionCells() throws CellHasValueException {
        for (Cell cell : board) {
            if (cell.getOptions().size() == 1) {
                cell.setValue(cell.getOptions().get(0));
            }
        }
    }

    public void setValueUniqueOptionCells()throws CellHasValueException {
        for (Map.Entry<SubSetType, List<SubSet>> entry : subSetListsMap.entrySet()) {
            for (SubSet subset : entry.getValue()){
                subset.fillUniqueOptions();
            }
        }
    }


    public boolean isChanged(){
        boolean isChanged = false;
        for (Cell cell : board){
            if (cell.isChanged()){
                isChanged = true;
            }
        }
        return isChanged;
    }

    public void removeAllOptions() {
        for (Map.Entry<SubSetType, List<SubSet>> entry : subSetListsMap.entrySet()) {
            for (SubSet subset : entry.getValue()){
                subset.removeAllOptions();
            }
        }
    }

    public void print() {
        int ind = 0;
        System.out.println("+---------+---------+---------+");
        for (int i = 0; i < 9; i++) {
            System.out.print("|");
            for (int j = 0; j < 9; j++) {

                if (board.get(ind).value != 0) {
                    System.out.print(" " + board.get(ind).value + " ");
                } else {
                    System.out.print("   ");
                }

                ind++;
                if ((j + 1) % 3 == 0) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if ((i + 1) % 3 == 0) {
                System.out.println("+---------+---------+---------+");
            }
        }
    }

}
