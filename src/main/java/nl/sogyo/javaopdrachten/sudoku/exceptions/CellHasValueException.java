package nl.sogyo.javaopdrachten.sudoku.exceptions;


public class CellHasValueException extends Exception {

    public CellHasValueException() {
        super("Trying to add a value to a cell that already has a value");
    }

}