package nl.sogyo.javaopdrachten.sudoku;

public class Sudoku {

    public static void main(String[] args) {
        // Board b = new Board(args[0]);
        // Board b = new Board("650080090070301000310509020005000000030070002000030940004010005000207000000008730"); // easy board
        Board b = new Board("000820090500000000308040007100000040006402503000090010093004000004035200000700900"); // hard board
        // // System.out.println(b.board.get(78).value);
        b.print();
        // b.checkCols();
        // // System.out.println(b.board.get(0).options.size());
        // for (Integer option : b.board.get(0).options) {
        //     System.out.print(option);
        // }

        // System.out.println("");

        // b.checkRows();
        // for (Integer option : b.board.get(0).options) {
        //     System.out.print(option);

        // }

        System.out.println("");


        // Cell c = new Cell(1,2,3);
        // System.err.println(c.findSquareRow());


        // b.printallcols();
        // b.printallsquares();
        // b.removeOptions();

        // for (Integer option : b.board.get(0).options) {
        //     System.out.print(option);
        // }


        // System.out.println("");
        // b.printoptions(9);

        b.run();
        // b.checkUniqueOptionsCol(6);

        // for (int i = 1; i < 10; i++) {
            
        //     b.checkUniqueOptionsCol(i);
        // }

        // for (int i = 1; i < 10; i++) {
            
        //     b.checkUniqueOptionsRow(i);
        // }

        // b.print();
        // b.printoptions(9);

        b.print();
        

    }

}

//  /usr/bin/env /usr/lib/jvm/java-17-openjdk-amd64/bin/java -XX:+ShowCodeDetailsInExceptionMessages -cp /home/reinier/java-opdrachten/target/classes nl.sogyo.javaopdrachten.sudoku.Sudoku 000820090500000000308040007100000040006402503000090010093004000004035200000700900
// /usr/bin/env /usr/lib/jvm/java-17-openjdk-amd64/bin/java -XX:+ShowCodeDetailsInExceptionMessages -cp /home/reinier/java-opdrachten/target/classes nl.sogyo.javaopdrachten.sudoku.Sudoku 650080090070301000310509020005000000030070002000030940004010005000207000000008730