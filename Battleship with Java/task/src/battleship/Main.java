package battleship;

import java.util.*;

public class Main {

//    private static final int SIZE = 10;
//    private static final char[][] player1Field = new char[SIZE][SIZE];
//    private static final char[][] player1FogField = new char[SIZE][SIZE];
//    private static final char[][] player2Field = new char[SIZE][SIZE];
//    private static final char[][] player2FogField = new char[SIZE][SIZE];
    public static final Map<String, Integer> SHIP_TYPES = new LinkedHashMap<>();
    static {
        SHIP_TYPES.put("Aircraft Carrier", 5);
        SHIP_TYPES.put("Battleship", 4);
        SHIP_TYPES.put("Submarine", 3);
        SHIP_TYPES.put("Cruiser", 3);
        SHIP_TYPES.put("Destroyer", 2);
    }

    private static final Scanner scanner = new Scanner(System.in);

//    private static final List<Ship> player1Ships = new ArrayList<>();
//    private static final List<Ship> player2Ships = new ArrayList<>();

    public static void main(String[] args) {
//       initializeField(player1Field);
//        initializeField(player1FogField);
//        initializeField(player2Field);
//        initializeField(player2FogField);

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        player1.placeShips();
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();

        player2.placeShips();
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();

        boolean player1Turn = true;
        while (true) {
            Player currentPlayer = player1Turn ? player1 : player2;
            Player opponentPlayer = player1Turn ? player2 : player1;

            if (currentPlayer.takeTurn(opponentPlayer)) {
                System.out.printf("%s won. Congratulations!", currentPlayer.getName());
                break;
            }

            System.out.println("Press Enter and pass the move to another player");
            scanner.nextLine();
            player1Turn = !player1Turn;
        }
//        for (Map.Entry<String, Integer> shipEntry : SHIP_TYPES.entrySet()) {
//            String shipName = shipEntry.getKey();
//            int shipLength = shipEntry.getValue();
//            boolean placed = false;
//
//            while (!placed) {
//                System.out.printf("Enter the coordinates for %s (%d cells):%n ", shipName, shipLength);
//                String input = scanner.nextLine();
//                String[] coordinates = input.split(" ");
//
//                if (coordinates.length == 2) {
//                    String start = coordinates[0].toUpperCase();
//                    String end = coordinates[1].toUpperCase();
//
//                    if (isValidInput(start, end, shipLength)) {
//                        Ship newShip = new Ship(shipName, shipLength, start, end);
//                        ships.add(newShip);
//                        placeShip(start, end);
//                        printField(gameField);
//                        placed = true;
//                    } else {
//                        System.out.printf("Error! Wrong length of the %s! Try again: %n", shipName);
//                    }
//                } else {
//                    System.out.println("Error! Wrong shipEntry location! Try again:");
//                }
//            }
//        }
//
//        System.out.println("The game starts!\n");
//        printField(fogField);
//        while (true) {
//            takeShot();
//            if (checkGameState()) {
//                break;
//            }
//        }
//        System.out.println("You sank the last ship. You won. Congratulations!");
    }

//    private static boolean checkGameState() {
//        for (Ship ship : ships) {
//            if (!ship.isSunk(gameField)) {
//                return false;
//            }
//        }
//        return true;
//    }

//    private static void takeShot() {
//        System.out.println("\nTake a shot!\n");
//
//        while (true) {
//            String input = scanner.nextLine();
//            int[] shotCoords = parseCoordinates(input.toUpperCase());
//
//            if (shotCoords != null) {
//                int row = shotCoords[0];
//                int col = shotCoords[1];
//                String shotCoordinate = row + "," + col;
//
//                if (fogField[row][col] == 'X') {
//                    System.out.println("\nYou hit a ship");
//                    printField(fogField);
//                    continue;
//                }
//
//                if (gameField[row][col] == 'O') {
//                    gameField[row][col] = 'X';
//                    fogField[row][col] = 'X';
//
//                    boolean sunk = false;
//                    for (Ship ship : ships) {
//                        if (ship.coordinates.contains(shotCoordinate)) {
//                            sunk = ship.isSunk(gameField);
//                            break;
//                        }
//                    }
//
//                    System.out.println();
//                    printField(fogField);
//                    if (sunk) {
//                        System.out.println("\nYou sank a ship! Specify a new target:");
//                    } else {
//                        System.out.println("\nYou hit a ship!");
//                    }
//                    return;
//                } else {
//                    gameField[row][col] = 'M';
//                    fogField[row][col] = 'M';
//                    printField(fogField);
//                    System.out.println("You missed!");
//                    return;
//                }
//            } else {
//                System.out.println("Error! You entered the wrong coordinates! Try again:");
//            }
//        }
//    }
//
//    private static boolean checkSunkShips() {
//        boolean sunk = false;
//        for (Ship ship : ships) {
//            if (!ship.isSunk(gameField)) {
//                sunk = true;
//            }
//        }
//        return sunk;
//    }

//    private static boolean isValidInput(String start, String end, int expectedLength) {
//        int[] startCoords = parseCoordinates(start);
//        int[] endCoords = parseCoordinates(end);
//
//        if (startCoords == null || endCoords == null) {
//            return false;
//        }
//
//        boolean sameRow = startCoords[0] == endCoords[0];
//        boolean sameColumn = startCoords[1] == endCoords[1];
//
//        int length = Math.max(Math.abs(startCoords[0] - endCoords[0]), Math.abs(startCoords[1] - endCoords[1])) + 1;
//        return length == expectedLength && (sameRow || sameColumn) && !isAdjacent(startCoords, endCoords);
//    }
//
//    private static boolean isAdjacent(int[] startCoords, int[] endCoords) {
//        int row1 = startCoords[0], col1 = startCoords[1];
//        int row2 = endCoords[0], col2 = endCoords[1];
//
//        int minRow = Math.min(row1, row2);
//        int maxRow = Math.max(row1, row2);
//        int minCol = Math.min(col1, col2);
//        int maxCol = Math.max(col1, col2);
//
//        for (int i = Math.max(0, minRow -1); i <= Math.min(SIZE - 1, maxRow + 1); i++) {
//            for (int j = Math.max(0, minCol - 1); j <= Math.min(SIZE - 1, maxCol + 1); j++) {
//                if (gameField[i][j] == 'O') {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

//    private static void placeShip(String start, String end) {
//        int[] startCoords = parseCoordinates(start);
//        int[] endCoords = parseCoordinates(end);
//
//        int row1 = startCoords[0], col1 = startCoords[1];
//        int row2 = endCoords[0], col2 = endCoords[1];
//
//        if (row1 == row2) {
//            for (int i = Math.min(col1, col2); i <= Math.max(col1, col2); i++) {
//                gameField[row1][i] = 'O';
//            }
//        } else {
//            for (int i = Math.min(row1, row2); i <= Math.max(row1, row2); i++) {
//                gameField[i][col1] = 'O';
//            }
//        }
//    }

    static int[] parseCoordinates(String coordinate) {
        if (coordinate.length() < 2 || coordinate.length() > 3) return null;

        char rowChar = coordinate.charAt(0);
        int row = rowChar - 'A';
        int column;
        try {
            column = Integer.parseInt(coordinate.substring(1)) - 1;
        } catch (NumberFormatException e) {
            return null;
        }

        return (row >= 0 && row < 10 && column >= 0 && column < 10) ? new int[]{row, column} : null;
    }

//    public static void initializeField(char[][] field) {
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                field[i][j] = '~';
//            }
//        }
//    }

//    public static void printField(char[][] field) {
//        System.out.print("  ");
//        for (int i = 1; i <= SIZE; i++) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < SIZE; i++) {
//            System.out.print((char) ('A' + i) + " ");
//            for (int j = 0; j < SIZE; j++) {
//                System.out.print(field[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
}
