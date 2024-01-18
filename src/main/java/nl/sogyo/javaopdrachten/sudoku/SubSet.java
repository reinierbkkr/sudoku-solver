package nl.sogyo.javaopdrachten.sudoku;

import java.util.ArrayList;
import java.util.List;

public class SubSet {
    private final SubSetType type;

    private ArrayList<Cell> cells = new ArrayList<>();

    public SubSet(SubSetType type){
        this.type = type;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public SubSetType getType() {
        return type;
    }

    public Cell getCell(int index){
        return cells.get(index);
    }

    public void addCell(Cell cell){
        cells.add(cell);
    }

    public void removeOption(int value){
        for (Cell cell : cells){
            cell.removeOption(value);
        }
    }

    public void findUniqueOption(){ // still working on this
        ArrayList<ArrayList<Integer>> allOptions = getAllOptions();

        for (int value = 1; value < 10; value++) {
            int foundIndex = checkIfValueOptionIsUnique(value, allOptions);
            if (foundIndex != -1) {
                System.out.printf("unique option found for col:\n");
//                fillCell(value, foundind, col);
            }
        }

    }

    ArrayList<ArrayList<Integer>> getAllOptions() {
        ArrayList<ArrayList<Integer>> allOptions = new ArrayList<>();
        for (Cell cell : cells){
            allOptions.add(cell.getOptions());
        }
        return allOptions;
    }

    public int checkIfValueOptionIsUnique(int value, ArrayList<ArrayList<Integer>> allOptions) {
        int count = 0;
        int index = 0;
        int foundIndex = -1;
        // System.out.print(trial);
        for (List<Integer> options : allOptions) { // iterate over all cells optionlists to check for number

            if (options.contains(value)) { // if the trial number is in the list it is added to count
                foundIndex = index; // and index stored
                count++;
            }
            index++;
        }
        if (count == 1) {
            foundIndex = index;
        }
        return foundIndex;
    }


}

