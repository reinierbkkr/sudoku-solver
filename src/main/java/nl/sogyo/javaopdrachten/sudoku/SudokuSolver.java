package nl.sogyo.javaopdrachten.sudoku;

import nl.sogyo.javaopdrachten.sudoku.exceptions.CellHasValueException;

import java.util.HashMap;

public class SudokuSolver {
    Board currentBoard;
    int currentBoardId;
    int parentBoardId = -1;
    HashMap<Integer, Board> allBoards = new HashMap<>();
    Cell currentCell = null;
    boolean done = false;

    public SudokuSolver(String input){
        currentBoard = new Board(input);
        currentBoardId = currentBoard.getBoardId();
    }

    public void tryToSolve() throws CellHasValueException {
        currentBoard.solve();
//        currentBoard.print();

        // the next part only if not solved and solution is possible
        while (!currentBoard.solved && !currentBoard.conflict && currentBoard.hasSolution){
            branchBoard();
            currentBoard.solve();
            while (currentBoard.conflict || !currentBoard.hasSolution){
                revertToParent();
                currentBoard.detectState();
            }
        }


//        currentBoard.printState();
//        System.out.println(allBoards.size());

    }

    private void branchBoard() throws CellHasValueException {
        int guess = makeGuess();

        allBoards.put(currentBoardId,currentBoard);
        currentBoard = new Board(currentBoard.getBoardAsString(), currentBoardId);
        parentBoardId = currentBoardId;
        currentBoardId = currentBoard.getBoardId();
        System.out.printf("Generating new board %d by making guess for cell in row %d, column %d; guessing value %d out of %d options.\n",
                currentBoardId,currentCell.getRow(),currentCell.getColumn(), guess,currentCell.getOptions().size()+1);
        currentBoard.cells.get(currentCell.getBoardIndex()).setValue(guess);
//        currentBoard.print();
        currentCell = null;
    }

    private void revertToParent(){
        currentBoard = allBoards.get(parentBoardId);
        currentBoardId = currentBoard.getBoardId();
        parentBoardId = currentBoard.getParentId();
        System.out.printf("reverted back to parent %d\n", currentBoardId);
    }

    private int makeGuess() {
//        System.out.println(currentBoardId);
        currentCell = currentBoard.getCellWithFewestOptions();
        int guess = currentCell.getOptions().get(0);
        currentCell.removeOption(guess);
        return guess;
    }

}

