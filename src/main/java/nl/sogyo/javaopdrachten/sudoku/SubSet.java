package nl.sogyo.javaopdrachten.sudoku;

import nl.sogyo.javaopdrachten.sudoku.exceptions.CellHasValueException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubSet {
    private final SubSetType type;

    private final ArrayList<Cell> cells = new ArrayList<>();

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
    public void removeAllOptions(){
        for (Cell cell : cells){
            if (cell.getValue()!=0){
                removeOption(cell.getValue());
            }
        }
    }
    public void removeOption(int value){
        for (Cell cell : cells){
            cell.removeOption(value);
        }
    }

    public void fillUniqueOptions() throws CellHasValueException{
        for (int value = 1; value < 10; value++) {
            int foundIndex = checkIfValueOptionIsUnique(value, getAllOptions());
            if (foundIndex != -1) {
//                System.out.printf("unique option found for col:\n");
                setCellValue(foundIndex, value);
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
        for (ArrayList<Integer> options : allOptions) { // iterate over all cells optionlists to check for number

            if (options.contains(value)) { // if the trial number is in the list it is added to count
                foundIndex = index; // and index stored
                count++;
            }
            index++;
        }
        if (count != 1) {
            foundIndex = -1;
        }
        return foundIndex;
    }

    public void setCellValue (int index, int value) throws CellHasValueException{
        cells.get(index).setValue(value);
    }

    public boolean hasConflict (){
        boolean hasConflict = false;
        ArrayList<Integer> allValues = getAllValues();
        for (int value = 1; value < 10; value++){
            int freq = Collections.frequency(allValues,Integer.valueOf(value));
            hasConflict = (freq>1);
            if (hasConflict) {
                break;
            }
        }
        return hasConflict;
    }

    public ArrayList<Integer> getAllValues(){
        ArrayList<Integer> allValues = new ArrayList<>();
        for (Cell cell : cells){
            if (cell.getValue() != 0) {
                allValues.add(cell.getValue());
            }
        }
        return allValues;
    }


}

