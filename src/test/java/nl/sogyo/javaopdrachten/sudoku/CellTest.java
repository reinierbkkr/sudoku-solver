package nl.sogyo.javaopdrachten.sudoku;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


}
