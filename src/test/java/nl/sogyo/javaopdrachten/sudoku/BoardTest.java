package nl.sogyo.javaopdrachten.sudoku;

import org.junit.jupiter.api.Test;

import java.util.List;

import nl.sogyo.javaopdrachten.sudoku.exceptions.CellHasValueException;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    void initializeSubSetsTest(){
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        List<SubSet> list = b.subSetListsMap.get(SubSetType.Column);

        assertEquals(9, list.size());
    }

    @Test
    void fillSubSetsWithCellsTest(){
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        SubSet columns = b.subSetListsMap.get(SubSetType.Column).get(0);

        assertEquals(9, columns.getCells().size());
    }

    @Test
    void setRemoveOptionTest(){
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        b.removeAllOptions();

        assertTrue(b.board.get(2).getOptions().size()!=9);
    }
    @Test
    void setValueSingleOptionCellsTest() throws CellHasValueException{
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        b.removeAllOptions();
        b.setValueSingleOptionCells();
        assertTrue(b.isChanged());
    }


    @Test
    void isChangedTestOneCellChanged() throws CellHasValueException{
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        b.board.get(2).setValue(3);

        assertTrue(b.isChanged());
    }

    @Test
    void isChangedTest2CellsChanged() throws CellHasValueException{
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        b.board.get(2).setValue(3);
        b.board.get(3).setValue(3);

        assertTrue(b.isChanged());
    }

    @Test
    void isChangedTestIfCellChangedReset() throws CellHasValueException{
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");
        b.board.get(2).setValue(3);
        b.board.get(3).setValue(3);

        b.isChanged();

        assertFalse(b.isChanged());

    }

    @Test
    void isChangedTestNoCellsChanged(){
        Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730");

        assertFalse(b.isChanged());
    }
}
