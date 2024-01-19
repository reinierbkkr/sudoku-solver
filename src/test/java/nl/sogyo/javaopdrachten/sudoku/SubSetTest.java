package nl.sogyo.javaopdrachten.sudoku;

import org.junit.jupiter.api.Test;

import nl.sogyo.javaopdrachten.sudoku.exceptions.CellHasValueException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubSetTest {

    @Test
    void checkIfValueOptionIsUniqueTest(){
        SubSet subset = new SubSet(SubSetType.Row);
        for (int i =0;i<9;i++){
            subset.addCell(new Cell(1,1,0));
        }
        subset.removeOption(9);
        subset.getCells().get(2).options.add(9);

        int returnedIndex = subset.checkIfValueOptionIsUnique(9, subset.getAllOptions());


        assertEquals(2, returnedIndex);
    }
    @Test
    void fillUniqueOptionsTest() throws CellHasValueException{
        SubSet subset = new SubSet(SubSetType.Row);
        for (int i =0;i<9;i++){
            subset.addCell(new Cell(1,1,0));
        }
        subset.removeOption(9);
        subset.getCells().get(2).options.add(9);

        subset.fillUniqueOptions();

        int value = subset.getCells().get(2).getValue();

        assertEquals(9, value);
    }


    @Test
    void checkIfValueOptionIsUniqueButNoUniqueTest(){
        SubSet subset = new SubSet(SubSetType.Row);
        for (int i =0;i<9;i++){
            subset.addCell(new Cell(1,1,0));
        }
        subset.removeOption(9);

        int returnedIndex = subset.checkIfValueOptionIsUnique(9, subset.getAllOptions());

        assertEquals(-1, returnedIndex);
    }

}

