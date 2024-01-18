package nl.sogyo.javaopdrachten.sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.List;

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

}
