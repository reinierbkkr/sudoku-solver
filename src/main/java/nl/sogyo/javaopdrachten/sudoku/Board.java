package nl.sogyo.javaopdrachten.sudoku;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Cell> board = new ArrayList<>();

    public Board (String input){
        makeBoard(input);
    }

    public void makeBoard(String input){
        List<Integer> values = inputToList(input);
        int ind = 0;
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                board.add(new Cell(i,j,values.get(ind)));
                ind++;            
            }
        }
    }

    public static List<Integer> inputToList(String input){
        List<Integer> values = new ArrayList<>();
        String[] splitinput = input.split("");
        for (String value : splitinput) {
            // System.out.println(value);
            values.add(Integer.parseInt(value));
        }
        return values;
    }


    // void printallsquares(){
    //     for (Cell cell : board) {
    //         System.out.println(cell.square);
    //     }
    // }

    public void run (){
        for (int i = 0; i < 1; i++) {
            removeOptions();
            fillCells();
        }
    }

    public void fillCells(){
        int ind = 1;
        for (Cell cell : board) {
            System.out.print(cell.getOptions().size()+" ");
            if (ind%9==0){
                System.err.println();
            }
            ind++;

            if (cell.getOptions().size()==1){
                System.out.println("this happens");
                cell.setValue(cell.getOptions().get(0));
            }
        }
    }

    public void removeOptions(){
        checkRows();
        checkCols();
        // checkSquares();
    }

    public void checkCols(){
        for (Cell cell : board) {
            if (cell.getValue()!=0){
                Integer value = cell.getValue();
                removeColOptions(cell.getCol(), value);
            }
        }
    }

    public void removeColOptions(Integer col, Integer value){
        for (Cell cell : board) {
            if (cell.getCol().equals(col)){
                cell.removeOption(value);
            }
        }
    }

    public void checkRows(){
        for (Cell cell : board) {
            if (cell.getValue()!=0){
                Integer value = cell.getValue();
                removeRowOptions(cell.getRow(), value);
            }
        }
    }

    public void removeRowOptions(Integer row, Integer value){
        for (Cell cell : board) {
            if (cell.getRow().equals(row)){
                cell.removeOption(value);
            }
        }
    }

    public void checkSquares(){
        for (Cell cell : board) {
            if (cell.getValue()!=0){
                Integer value = cell.getValue();
                removeRowOptions(cell.getSquare(), value);
            }
        }
    }

    public void removeSquareOptions(Integer square, Integer value){
        for (Cell cell : board) {
            if (cell.getSquare().equals(square)){
                cell.removeOption(value);
            }
        }
    }

    public void print(){
        int ind = 0;
        System.out.println("+---------+---------+---------+");
        for (int i = 0; i < 9; i++) {
            System.out.print("|");
            for (int j = 0; j < 9; j++) {
            
                if (board.get(ind).value != 0){
                    System.out.print(" "+board.get(ind).value+" ");
                } else {
                    System.out.print("   ");
                }

                ind++;
                if ((j+1)%3 == 0){
                    System.out.print("|");
                }
            }
            System.out.println();
            if ((i+1)%3 == 0){
                System.out.println("+---------+---------+---------+");
            }
        }
    }
    
}
