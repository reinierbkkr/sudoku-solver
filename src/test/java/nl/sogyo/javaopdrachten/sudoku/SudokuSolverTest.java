package nl.sogyo.javaopdrachten.sudoku;

import org.junit.jupiter.api.Test;

import java.util.List;

import nl.sogyo.javaopdrachten.sudoku.exceptions.CellHasValueException;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuSolverTest {
    @Test
    void constructorTest(){
        SudokuSolver sudokuSolver = new SudokuSolver("500007010040000000190000000900000045000309006403006000730500000000002079010060000");

        assertEquals(0, sudokuSolver.currentBoardId);
    }

    @Test
    void tryToSolveTest() throws CellHasValueException {
        SudokuSolver sudokuSolver = new SudokuSolver("500007010040000000190000000900000045000309006403006000730500000000002079010060000");

        sudokuSolver.tryToSolve();

//        sudokuSolver.currentBoard.print();
        assertTrue(sudokuSolver.currentBoard.solved);

    }

    @Test
    void tryToSolveHardestEverTest() throws CellHasValueException {
        SudokuSolver sudokuSolver = new SudokuSolver("800000000003600000070090200050007000000045700000100030001000068008500010090000400");

        sudokuSolver.currentBoard.print();

        sudokuSolver.tryToSolve();

        sudokuSolver.currentBoard.print();

//        sudokuSolver.currentBoard.print();
        assertTrue(sudokuSolver.currentBoard.solved);

    }

    @Test
    void tryToSolveAnotherHardOneTest() throws CellHasValueException {
        SudokuSolver sudokuSolver = new SudokuSolver("050908600800006007006020000009000070203000809010000400000030700900800004005604030");

        sudokuSolver.currentBoard.print();

        sudokuSolver.tryToSolve();

        sudokuSolver.currentBoard.print();

//        sudokuSolver.currentBoard.print();
        assertTrue(sudokuSolver.currentBoard.solved);

    }

    @Test
    void tryToSolveHardestOneIFoundTest() throws CellHasValueException {
        SudokuSolver sudokuSolver = new SudokuSolver("600008940900006100070040000200610000000000200089002000000060005000000030800001600");

        sudokuSolver.currentBoard.print();

        sudokuSolver.tryToSolve();

        sudokuSolver.currentBoard.print();

//        sudokuSolver.currentBoard.print();
        assertTrue(sudokuSolver.currentBoard.solved);

    }

    @Test
    void tryToSolveYetAnotherHardOneTest() throws CellHasValueException {
        SudokuSolver sudokuSolver = new SudokuSolver("12.3.....4.5...6...7.....2.6..1..3....453.........8..9...45.1.........8......2..7");

        sudokuSolver.currentBoard.print();

        sudokuSolver.tryToSolve();

        sudokuSolver.currentBoard.print();

//        sudokuSolver.currentBoard.print();
        assertTrue(sudokuSolver.currentBoard.solved);

    }

}
