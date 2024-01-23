package nl.sogyo.javaopdrachten.sudoku;

import nl.sogyo.javaopdrachten.sudoku.exceptions.CellHasValueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    static int BoardIdSource = 0;
    List<Cell> cells = new ArrayList<>();

    Map<SubSetType, List<SubSet>> subSetListsMap = new HashMap<SubSetType, List<SubSet>>() {{
        put(SubSetType.Row, new ArrayList<SubSet>());
        put(SubSetType.Column, new ArrayList<SubSet>());
        put(SubSetType.Block, new ArrayList<SubSet>());
    }};

    int boardId = 0;

    int parentId;

    boolean changed = true;

    boolean conflict = false;

    boolean solved = false;
    boolean hasSolution = true;
    public Board(String input) {
        boardId = newBoardId();

        makeBoard(input);
        initializeSubSetsInSubSetLists();
        fillSubSetsWithCells();
    }

    public Board(String input, int parentId) {
        boardId = newBoardId();
        this.parentId = parentId;

        makeBoard(input);
        initializeSubSetsInSubSetLists();
        fillSubSetsWithCells();
    }

    public static int newBoardId(){
        int newBoardId = BoardIdSource;
        BoardIdSource++;
        return newBoardId;
    }

    public void makeBoard(String input) {
        List<Integer> values = inputToList(input);
        int index = 0;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                cells.add(new Cell(row, column, values.get(index)));
                index++;
            }
        }
    }

    public static List<Integer> inputToList(String input) {
        List<Integer> values = new ArrayList<>();
        String[] splitinput = input.split("");
        for (String value : splitinput) {
            values.add(Integer.parseInt(value));
        }
        return values;
    }

    void initializeSubSetsInSubSetLists() {
        for (Map.Entry<SubSetType, List<SubSet>> entry : subSetListsMap.entrySet()) {
            for (int i = 0; i < 9; i++) { // refactor
                entry.getValue().add(new SubSet(entry.getKey()));
            }
        }
    }

    void fillSubSetsWithCells() {
        for (Cell cell : cells) {
            subSetListsMap.get(SubSetType.Row).get(cell.getRow()).addCell(cell);
            subSetListsMap.get(SubSetType.Column).get(cell.getColumn()).addCell(cell);
            subSetListsMap.get(SubSetType.Block).get(cell.getBlock()).addCell(cell);
        }
    }

    public int getBoardId() {
        return boardId;
    }

    public int getParentId() {
        return parentId;
    }

    public boolean hasSolution() {
        return hasSolution;
    }

    void solve() throws CellHasValueException {
        detectConflict();
        int simplecounter = 0;
        int complexcounter = 0;

        while (!conflict && !solved && changed && hasSolution) {
            while (!conflict && !solved && changed && hasSolution) {
                detectConflict();
                removeAllOptions();
                setValueSingleOptionCells();
                detectChangedCell();
                simplecounter++;
            }
            removeAllOptions();
            setValueUniqueOptionCells();
            detectChangedCell();
            complexcounter++;
            detectSolved();
        }
        detectHasSolution();
        if (solved) {
            System.out.println("Solved!");
        }
        if (conflict) {
            System.out.println("Conflict!");
        }
//        System.out.printf("End. Simple loops %d, complex loops %d.\n", simplecounter, complexcounter);
    }


    public void setValueSingleOptionCells() throws CellHasValueException {
        for (Cell cell : cells) {
            if (cell.getOptions().size() == 1) {
                cell.setValue(cell.getOptions().get(0));
            }
        }
    }

    public void setValueUniqueOptionCells() throws CellHasValueException {
        for (Map.Entry<SubSetType, List<SubSet>> entry : subSetListsMap.entrySet()) {
            for (SubSet subset : entry.getValue()) {
                subset.setValueUniqueOptionCells();
                removeAllOptions();
            }
        }
    }


    public void detectChangedCell() {
        for (Cell cell : cells) {
            if (cell.isChanged()) {
                changed = true;
                break;
            } else {
                changed = false;
            }
        }
    }

    public void removeAllOptions() {
        for (Map.Entry<SubSetType, List<SubSet>> entry : subSetListsMap.entrySet()) {
            for (SubSet subset : entry.getValue()) {
                subset.removeAllOptions();
            }
        }
    }

    public void detectConflict() {
        for (Map.Entry<SubSetType, List<SubSet>> entry : subSetListsMap.entrySet()) {
            conflict = subSetsHaveConflicts(entry);
            if (conflict) {
                break;
            }
        }
    }

    private boolean subSetsHaveConflicts(Map.Entry<SubSetType, List<SubSet>> entry) {
        for (SubSet subset : entry.getValue()) {
            if (subset.hasConflict())
                return true;
        }
        return false;
    }

    public void print() {
        int ind = 0;
        System.out.println("+---------+---------+---------+");
        for (int i = 0; i < 9; i++) {
            System.out.print("|");
            for (int j = 0; j < 9; j++) {

                if (cells.get(ind).value != 0) {
                    System.out.print(" " + cells.get(ind).value + " ");
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

    public void detectSolved() {
        solved = true;
        for (Cell cell : cells) {
            if (cell.getValue() == 0) {
                solved = false;
                break;
            }
        }
    }

    public void detectHasSolution(){
        for (Cell cell : cells) {
            if (!cell.hasValue() && !cell.hasOptions()){
                hasSolution = false;
                break;
            }
        }
    }

    public Cell getCellWithFewestOptions(){
        Cell chosenCell = firstCellWithOptions();
        for (Cell cell:cells){
            if (cell.hasOptions() && cell.getOptions().size() < chosenCell.getOptions().size()){
                chosenCell = cell;
            }
        }
        return chosenCell;
    }

    public Cell firstCellWithOptions(){
        for (Cell cell : cells){
            if (cell.hasOptions()){
                return cell;
            }
        }
        return new Cell(8,8,10); //this should never happen
    }

    public String getBoardAsString(){
        String board = "";
        for (Cell cell : cells){
            board += cell.getValue();
        }
        return board;
    }

    public void detectState(){
        detectSolved();
        detectConflict();
        detectHasSolution();
    }

    public void printState(){
        System.out.printf("Solved: %b; Conflict: %b; Solution possible: %b\n",solved,conflict,hasSolution);
    }

}
