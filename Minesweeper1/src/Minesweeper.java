import java.util.Scanner;

public class Minesweeper {
    char[][] playerBoard;
    char[][] gameBoard;
    int ROWS, COLUMNS, MINES, difficulty, win = 0;
    Scanner input = new Scanner(System.in);
    boolean game = true;

    Minesweeper() {
        this.gameBoard = new char[ROWS][COLUMNS];
        this.playerBoard = new char[ROWS][COLUMNS];
    }

    public void chooseDifficulty() {
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Please enter difficulty level: ");
            System.out.println("Press 1 for Beginner (6*6 Cells and 10 Mines)");
            System.out.println("Press 2 for Intermediate (9*9 Cells and 20 Mines)");
            System.out.println("Press 3 for Advanced (16*16 Cells and 40 Mines)");
            difficulty = input.nextInt();
            switch (difficulty) {
                case 1:
                    ROWS = 6;
                    COLUMNS = 6;
                    MINES = 10;
                    isValid = true;
                    break;
                case 2:
                    ROWS = 9;
                    COLUMNS = 9;
                    MINES = 20;
                    isValid = true;
                    break;
                case 3:
                    ROWS = 16;
                    COLUMNS = 16;
                    MINES = 40;
                    isValid = true;
                    break;
                default:
                    System.out.println("Invalid character!");
            }
        }
        gameBoard = new char[ROWS][COLUMNS];
        playerBoard = new char[ROWS][COLUMNS];
    }

    public void run() {
        chooseDifficulty();
        placeMines();
        System.out.println("\n\t==========Game Start!==========" + "\n");
        while (game) {
            printPlayerBoard(playerBoard);
            getPlayerMove();
        }
    }

    public void placeMines() {
        int minesPlaced = 0;
        while (minesPlaced < MINES) {
            int row = (int) (Math.random() * ROWS);
            int column = (int) (Math.random() * COLUMNS);
            if (gameBoard[row][column] != 'M') {
                gameBoard[row][column] = 'M';
                minesPlaced++;
            }
        }
    }

    public void printGameBoard(char[][] array) {
        System.out.print("  ");
        for (int k = 0; k < COLUMNS; k++) {
            System.out.printf(" %3d", k);
        }
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%2d ", i);
            System.out.print("|");
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(" " + array[i][j] + " ");
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public void printPlayerBoard(char[][] array) {
        System.out.print("  ");
        int k;
        for (k = 0; k < COLUMNS; k++) {
            if (k >= 10)
                break;
            System.out.printf(" %3d", k);
        }
        System.out.print("  ");
        if (COLUMNS >= 10) {
            for (int i = k; i < COLUMNS; i++)
                System.out.printf(" %-2d ", i);
        }
        System.out.println();

        for (int i = 0; i < ROWS; i++) {
            System.out.printf("%2d ", i);
            System.out.print("|");
            for (int j = 0; j < COLUMNS; j++) {
                if (array[i][j] == '\u0000')
                    array[i][j] = '*';
                System.out.print(" " + array[i][j] + " ");
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public void countNeighboringMines(int row, int col) {
        if (playerBoard[row][col] == '*') {
            int count = 0;
            for (int m = -1; m < 2; m++) {
                for (int n = -1; n < 2; n++) {
                    if ((m != 0 || n != 0) && isValidCell(row + m, col + n) && gameBoard[row + m][col + n] == 'M')
                        count++;
                }
            }
            if (count > 0)
                playerBoard[row][col] = (char) (count + '0');
            else
                playerBoard[row][col] = '0';
        }
    }

    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
    }

    public void getPlayerMove() {
        int row, column;
        System.out.println("\nEnter Row Number: ");
        row = input.nextInt();
        System.out.println("Enter Column Number: ");
        column = input.nextInt();
        if (row < 0 || row >= ROWS) {
            System.out.println("\n" + "Invalid coordination!" + "\n");
            return;
        }
        if (column < 0 || column >= COLUMNS) {
            System.out.println("\n" + "Invalid coordination!" + "\n");
            return;
        }
        if (gameBoard[row][column] != 'M') {
            countNeighboringMines(row, column);
            win++;
            if (win == ((COLUMNS * ROWS) - MINES)) {
                game = false;
                System.out.println("Congratulations! You cleared all the mines.");
            }
        } else {
            game = false;
            System.out.println("\nGame Over! You stepped on a mine.\n");
            printGameBoard(gameBoard);
        }
    }
}
