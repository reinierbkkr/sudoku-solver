package nl.sogyo.javaopdrachten.sudoku;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    Integer value;
    Integer row;
    Integer col;
    Integer square;
    List<Integer> options = new ArrayList<>();

    public Cell(Integer row, Integer col, Integer value){
        this.row = row;
        this.col = col;
        this.value = value;
        findSquare();
        if (value == 0){
            initializeOptions();
        }
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        System.out.printf("cell (row %d, col %d) set to value %d\n",this.row, this.col, value);

        this.value = value;
        options.clear();
    }

    public Integer getRow() {
        return this.row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return this.col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getSquare() {
        return this.square;
    }

    public void setSquare(Integer square) {
        this.square = square;
    }

    public List<Integer> getOptions() {
        return this.options;
    }

    public void setOptions(List<Integer> options) {
        this.options = options;
    }

    public void initializeOptions(){
        for (int i = 1; i < 10; i++) {
            options.add(i);
            
        }
        // System.out.println("this happens");
    }

    public void removeOption(Integer value){
        options.remove(value);
    }
    
    public void findSquare(){
        switch (findSquareRow()) {
            case 1:
                square = findSquareCol();
                break;
            case 2:
                square = 3 + findSquareCol();
                break;
            case 3:
                square = 6 + findSquareCol();
        }
    }

    public Integer findSquareCol(){
        Integer squareCol = null;
        switch (col) {
            case 1: case 2: case 3:
                squareCol = 1;
                break;
            case 4: case 5: case 6:
                squareCol = 2;
                break;
            case 7: case 8: case 9:
                squareCol = 3;
        }
        return squareCol;
    }

    public Integer findSquareRow(){
        Integer squareRow = null;
        switch (row) {
            case 1: case 2: case 3:
                squareRow = 1;
                break;
            case 4: case 5: case 6:
                squareRow = 2;
                break;
            case 7: case 8: case 9:
                squareRow = 3;
        }
        return squareRow;
    }

}
