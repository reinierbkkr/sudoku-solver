package nl.sogyo.javaopdrachten.sudoku;

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
                board.add(new Cell(row, column, values.get(index )));
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

//        subSets.put("rows", new ArrayList<>());
//        subSets.put("cols", new ArrayList<>());
//        subSets.put("blocks", new ArrayList<>());
//        for (int i = 0; i < 9; i++) {
//            subSets.get("rows").add(new ArrayList<>());
//            subSets.get("cols").add(new ArrayList<>());
//            subSets.get("blocks").add(new ArrayList<>());
//        }
    }

    void fillSubSetsWithCells() {
        for (Cell cell : board){
            subSetListsMap.get(SubSetType.Row).get(cell.getRow()).addCell(cell);
            subSetListsMap.get(SubSetType.Column).get(cell.getColumn()).addCell(cell);
            subSetListsMap.get(SubSetType.Block).get(cell.getBlock()).addCell(cell);
        }
    }

//    void printallsquares() {
//        int ind = 1;
//        for (Cell cell : board) {
//            System.out.print(cell.block + " ");
//            if (ind % 9 == 0) {
//                System.out.println();
//            }
//            ind++;
//        }
//    }

//    void printoptions(int limit) {
//        int ind = 1;
//        for (Cell cell : board) {
//            if (ind <= limit) {
//                System.out.print(ind + " ");
//            }
//            for (Integer option : cell.options) {
//                if (ind <= limit) {
//                    System.out.print(option);
//                }
//            }
//            if (ind <= limit) {
//                System.out.println();
//            }
//            // System.out.print(ind);
//            ind++;
//        }
//    }

//    void printoptions(List<List<Integer>> optionslist) {
//        int ind = 1;
//        for (List<Integer> options : optionslist) {
//            System.out.print(ind + " ");
//            for (Integer option : options) {
//                System.out.print(option);
//            }
//            ind++;
//            System.out.println();
//        }
//
//    }

//    public void run() {
//        for (int i = 0; i < 1000; i++) {
//
//            for (int j = 0; j < 100; j++) {
//                removeOptions();
//                fillSingleOptionCells();
//            }
//            for (int k = 1; k < 10; k++) {
//                if (k % 3 == 0) {
//                    checkUniqueOptionsRow(k);
//                } else if ((k + 1) % 3 == 0) {
//                    checkUniqueOptionsCol(k);
//                }
//            }
//        }
//    }

    public void fillSingleOptionCells() {
        int ind = 1;
        for (Cell cell : board) {

            // // this part is to print all options
            // System.out.print(cell.getOptions().size()+" ");
            // if (ind%9==0){
            // System.out.println();
            // }
            // ind++;

            if (cell.getOptions().size() == 1) {
                // System.out.printf("cell set to value");
                cell.setValue(cell.getOptions().get(0));
            }
        }
    }

    public void fillCell(Integer value, Integer row, Integer col) {
        // System.out.printf("looking for cell at row %d col %d to give value
        // %d\n",row,col, value);
        for (Cell cell : board) {
            if (cell.getRow().equals(row) && cell.getColumn().equals(col)) {
                cell.setValue(value);
            }
        }

    }

    public void removeOptions() {
        checkOptionsSameRow();
        checkOptionsSameCol();
        checkOptionsSameSquare();

        // for (int i = 1; i < 10; i++) {
        // checkUniqueOptionsCol(i);
        // }

        // for (int i = 1; i < 10; i++) {
        // checkUniqueOptionsRow(i);
        // }

    }

//    public void checkUniqueOptionsCol(Integer col) {
//        List<List<Integer>> alloptions = new ArrayList<>();
//        for (Cell cell : board) {
//            if (cell.getColumn().equals(col)) {
//                alloptions.add(cell.getOptions());
//            }
//        }
//        // System.out.println(alloptions.size());
//        // if (col.equals(6)) {printoptions(alloptions);}
//
//        // Integer trial = 1;
//
//        for (Integer value = 1; value < 10; value++) { // iterate over all possible numbers
//            int foundind = checkUniqueOptions(value, alloptions);
//            if (foundind != 0) {
//                System.out.printf("unique option found for col:\n");
//                fillCell(value, foundind, col);
//            }
//        }
//
//    }

//    public void checkUniqueOptionsRow(Integer row) {
//        List<List<Integer>> alloptions = new ArrayList<>();
//        for (Cell cell : board) {
//            if (cell.getRow().equals(row)) {
//                alloptions.add(cell.getOptions());
//            }
//        }
//
//        for (Integer value = 1; value < 10; value++) { // iterate over all possible numbers
//            int foundind = checkUniqueOptions(value, alloptions);
//            if (foundind != 0) {
//                System.out.printf("unique option found for row:\n");
//                fillCell(value, row, foundind);
//            }
//        }
//
//    }

//    int checkUniqueOptions(Integer value, List<List<Integer>> alloptions) {
//        int count = 0;
//        int ind = 1;
//        int foundind = 0;
//        // System.out.print(trial);
//        for (List<Integer> options : alloptions) { // iterate over all cells optionlists to check for number
//
//            if (options.contains(value)) { // if the trial number is in the list it is added to count
//                foundind = ind; // and index stored
//                count++;
//            }
//            ind++;
//        }
//        if (count != 1) {
//            foundind = 0;
//        }
//        return foundind;
//    }

    public void checkOptionsSameCol() {
        for (Cell cell : board) {
            if (cell.getValue() != 0) {
                Integer value = cell.getValue();
                removeOptionsSameCol(cell.getColumn(), value);
            }
        }
    }

    public void removeOptionsSameCol(Integer col, Integer value) {
        for (Cell cell : board) {
            if (cell.getColumn().equals(col)) {
                cell.removeOption(value);
            }
        }
    }

    public void checkOptionsSameRow() {
        for (Cell cell : board) {
            if (cell.getValue() != 0) {
                Integer value = cell.getValue();
                removeOptionsSameRow(cell.getRow(), value);
            }
        }
    }

    public void removeOptionsSameRow(Integer row, Integer value) {
        for (Cell cell : board) {
            if (cell.getRow().equals(row)) {
                cell.removeOption(value);
            }
        }
    }

    public void checkOptionsSameSquare() {
        for (Cell cell : board) {
            if (cell.getValue() != 0) {
                Integer value = cell.getValue();
                removeOptionsSameSquare(cell.getBlock(), value);
            }
        }
    }

    public void removeOptionsSameSquare(Integer square, Integer value) {
        for (Cell cell : board) {
            if (cell.getBlock().equals(square)) {
                cell.removeOption(value);
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
