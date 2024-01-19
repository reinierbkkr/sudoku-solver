package nl.sogyo.javaopdrachten.sudoku;

import org.junit.jupiter.api.Test;

import java.util.List;

import nl.sogyo.javaopdrachten.sudoku.exceptions.CellHasValueException;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    void initializeSubSetsTest() {
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        List<SubSet> list = b.subSetListsMap.get(SubSetType.Column);

        assertEquals(9, list.size());
    }

    @Test
    void fillSubSetsWithCellsTest() {
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        SubSet columns = b.subSetListsMap.get(SubSetType.Column).get(0);

        assertEquals(9, columns.getCells().size());
    }

    @Test
    void setRemoveOptionTest() {
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        b.removeAllOptions();

        assertTrue(b.board.get(2).getOptions().size() != 9);
    }

    @Test
    void setValueSingleOptionCellsTest() throws CellHasValueException {
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        b.removeAllOptions();
        b.setValueSingleOptionCells();
        b.detectChangedCell();

        assertTrue(b.changed);
    }

    @Test
    void setValueUniqueOptionCellsTest() throws CellHasValueException {
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        b.removeAllOptions();
        b.setValueSingleOptionCells();
        b.removeAllOptions();
//        System.out.println("unique");
        b.setValueUniqueOptionCells();
        b.detectChangedCell();

        assertTrue(b.changed);

    }


    @Test
    void isChangedTestOneCellChanged() throws CellHasValueException {
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        b.board.get(2).setValue(3);
        b.detectChangedCell();

        assertTrue(b.changed);
    }

    @Test
    void isChangedTest2CellsChanged() throws CellHasValueException {
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        b.board.get(2).setValue(3);
        b.board.get(3).setValue(3);

        b.detectChangedCell();

        assertTrue(b.changed);
    }

    @Test
    void isChangedTest() throws CellHasValueException {
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        b.board.get(2).setValue(3);
        b.board.get(3).setValue(3);

        b.detectChangedCell();

        assertTrue(b.changed);

    }


    @Test
    void isChangedTestNoCellsChanged() {
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        b.detectChangedCell();

        assertFalse(b.changed);
    }

    @Test
    void hasConflictTest() {
        Board b = new Board("660000000000000000000000000000000000000000000000000000000000000000000000000000000");
        b.detectConflict();

        assertTrue(b.conflict);

    }

    @Test
    void hasConflict2Test() {
        Board b = new Board("000000000000000000000000000000000000000000000000000000000000000000000000000000033");
        b.detectConflict();

        assertTrue(b.conflict);

    }

    @Test
    void hasConflictBlockTest() {
        Board b = new Board("600000000006000000000000000000000000000000000000000000000000000000000000000000000");
        b.detectConflict();

        assertTrue(b.conflict);

    }

    @Test
    void hasConflictRowTest() {
        Board b = new Board("000000000000000000000000000000000000000000000000000000000000000000000003000000003");
        b.detectConflict();

        assertTrue(b.conflict);

    }

    @Test
    void hasConflictNoConflictTest() {
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        b.detectConflict();

        assertFalse(b.conflict);

    }

    @Test
    void detectSolvedTest() {
        Board b = new Board("666666666666666666666666666666666666666666666666666666666666666666666666666666666");
        b.detectSolved();

        assertTrue(b.solved);
    }

    @Test
    void detectSolvedNotSolvedTest() {
        Board b = new Board("666666666666666666666666666666666666666666666666666666666666666666666666666066666");
        b.detectSolved();

        assertFalse(b.solved);
    }

    @Test
    void solveEasySudokuTest() throws CellHasValueException {
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
//        b.print();
        b.solve();
//        b.print();
        assertTrue(b.solved);
    }

    @Test
    void solveHarderSudokuTest() throws CellHasValueException {
        Board b = new Board("000820090500000000308040007100000040006402503000090010093004000004035200000700900");
//        b.print();
        b.solve();
//        b.print();
        assertTrue(b.solved);
    }

    @Test
    void solveHardestSudokuTest() throws CellHasValueException {
        Board b = new Board("500007010040000000190000000900000045000309006403006000730500000000002079010060000");
        b.print();
        b.solve();
        b.print();
//        assertTrue(b.solved);
    }
}
