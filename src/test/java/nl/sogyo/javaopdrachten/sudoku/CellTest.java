package nl.sogyo.javaopdrachten.sudoku;

import org.junit.jupiter.api.Test;

import nl.sogyo.javaopdrachten.sudoku.exceptions.CellHasValueException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CellTest {
    @Test
    void initializeCellBlockAssignTest(){
        Cell cell = new Cell(0,0,0);

        assertEquals(0, cell.getBlock());

    }
    @Test
    void initializeCellBlockAssign8Test(){
        Cell cell = new Cell(8,8,8);

        assertEquals(8, cell.getBlock());

    }

    @Test
    void setValueForCellThatHasValueTest(){
        Cell cell = new Cell(8,8,8);

        assertThrows(CellHasValueException.class, ()->{{cell.setValue(1);}});

    }


}
