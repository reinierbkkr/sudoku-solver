package nl.sogyo.javaopdrachten.sudoku;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    int value;
    int row;
    int column;
    int block;

    boolean changed = false;

    ArrayList<Integer> options = new ArrayList<>();
    public Cell(int row, int column, int value){
        this.row = row;
        this.column = column;
        this.value = value;
        findBlock();
        if (value == 0){
            initializeOptions();
        }
    }

    public boolean isChanged() {
        if (changed) {
            changed = false;
            return true;
        } else {
            return false;
        }
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(int value) {
        System.out.printf("cell (row %d, col %d) set to value %d\n",this.row, this.column, value);

        this.value = value;
        options.clear();
        changed = true;
    }

    public Integer getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Integer getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Integer getBlock() {
        return this.block;
    }

    public void setBlock(Integer square) {
        this.block = square;
    }

    public ArrayList<Integer> getOptions() {
        return this.options;
    }

    public void setOptions(ArrayList<Integer> options) {
        this.options = options;
    }

    public void initializeOptions(){
        for (int value = 1; value < 10; value++) {
            options.add(value);
        }
        // System.out.println("this happens");
    }

    public void removeOption(int value){
        options.remove(Integer.valueOf(value));
    }
    
    public void findBlock(){
        switch (findBlockRow()) {
            case 0:
                block = findBlockColumn();
                break;
            case 1:
                block = 3 + findBlockColumn();
                break;
            case 2:
                block = 6 + findBlockColumn();
        }
    }

    public int findBlockColumn(){
        int blockColumn = -1;
        switch (column) {
            case 0: case 1: case 2:
                blockColumn = 0;
                break;
            case 3: case 4: case 5:
                blockColumn = 1;
                break;
            case 6: case 7: case 8:
                blockColumn = 2;
        }
        return blockColumn;
    }

    public int findBlockRow(){
        int blockRow = -1;
        switch (row) {
            case 0: case 1: case 2:
                blockRow = 0;
                break;
            case 3: case 4: case 5:
                blockRow = 1;
                break;
            case 6: case 7: case 8:
                blockRow = 2;
        }
        return blockRow;
    }

}
