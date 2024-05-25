
public class Main {
    public static void main(String[] args) {

        System.out.println("\n\t=======Welcome to the Minesweeper Game!=======\n");
        System.out.println("How to Play:" +
                "\n1.First, choose the difficulty level you want to play.\n" +
                "2.Then, type the row and column numbers to open a cell.\n" +
                "3.If you open a mined cell, the game ends.\n" +
                "4.If the cell is not mined, it will display a number indicating how many mines are diagonally or adjacent to it.\n");

        Minesweeper minesweeper = new Minesweeper();
        minesweeper.run();
    }
}
