import java.util.*;

public class TicTacToeGame {

    //Position symbols will be stored here
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> computerPositions = new ArrayList<>();

    static Scanner scInt = new Scanner(System.in);
    static Scanner scChar = new Scanner(System.in);

    static int symbolPosition;
    static char symbolPlayer;
    static char symbolComputer;

    public static void main(String[] args) {

        System.out.println("\n*** Welcome to Tic-Tac-Toe Game ***\n");

        Random random = new Random();

        //Games graphic
        System.out.println("\t 1 | 2 | 3\n\t - + - + -\n\t 4 | 5 | 6\n\t - + - + -\n\t 7 | 8 | 9");

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},};

        //Choose symbol X or O
        char chosenSymbol = 'y';
        while(chosenSymbol != 'X' && chosenSymbol != 'x' && chosenSymbol != 'O' && chosenSymbol != 'o' && chosenSymbol != '0') {
            System.out.print("\n> Choose your symbol to play with (\"X\" or \"O\"): ");
            chosenSymbol = scChar.next().charAt(0);
        }
        if(chosenSymbol == 'X' || chosenSymbol == 'x') {
            symbolPlayer = 'X';
            symbolComputer = 'O';
        } else {
            symbolPlayer = 'O';
            symbolComputer = 'X';
        }

        //The winning combinations
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List middleCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);
        List<List> winning = new ArrayList<>(Arrays.asList(topRow, middleRow, bottomRow, leftCol, middleCol, rightCol, cross1, cross2));

        //Game mechanics
        while (true) {
            placePlayerSymbol(gameBoard);

            if (playerPositions.size() + computerPositions.size() < 9) {
                symbolPosition = random.nextInt(9) + 1;
                while (playerPositions.contains(symbolPosition) || computerPositions.contains(symbolPosition)) {
                    symbolPosition = random.nextInt(9) + 1;
                }
                placePieces(gameBoard, symbolPosition, "computer");
            }

            printGameBoard(gameBoard);

            String resultWinner = checkWinner(winning);
            if (resultWinner.length() > 0) {
                System.out.println(resultWinner);
                break;
            }
        }

    }

    //Game graphics
    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            System.out.print("\t");
            for (char c : row) {
                System.out.print(" " + c);
            }
            System.out.println();
        }
    }

    //Place the symbols on the positions
    private static void placePieces(char[][] gameBoard, int symbolPosition, String user) {
        char symbol;

        if (user.equals("player")) {
            symbol = symbolPlayer;
            playerPositions.add(symbolPosition);
        } else {
            symbol = symbolComputer;
            computerPositions.add(symbolPosition);
        }

        switch (symbolPosition) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                System.out.println("\n> You have not entered a correct position");
                placePlayerSymbol(gameBoard);
        }
    }

    //The method used to position the player's symbols
    private static void placePlayerSymbol(char[][] gameBoard) {
        symbolPosition = 0;
        while(symbolPosition < 1 || symbolPosition > 9) {
            try {
                System.out.print("\n> Enter the position of " + symbolPlayer + " (1 - 9): ");
                symbolPosition = scInt.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("> Invalid input. Please enter a valid number.");
                scInt.nextLine();
                continue;
            }
        }
        while (playerPositions.contains(symbolPosition) || computerPositions.contains(symbolPosition)) {
            try {
                System.out.print("\n> The position is already filled.\n> Please choose another position (1 - 9): ");
                symbolPosition = scInt.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("> Invalid input. Please enter a valid number.");
                scInt.nextLine();
                continue;
            }
        }
        placePieces(gameBoard, symbolPosition, "player");
    }

    //Check winner
    private static String checkWinner(List<List> winning) {
        for(List l : winning) {
            if(playerPositions.containsAll(l)) {
                return "\n*** Congratulations! You won the game! ***";
            } else if (computerPositions.containsAll(l)) {
                return "\n### Sorry! You lost the game! ###";
            } else if (playerPositions.size() + computerPositions.size() == 9) {
                return "\n### Game draw! ###";
            }
        }
        return "";
    }


}