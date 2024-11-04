package battleship;

import java.util.*;

public class Main {

    public static final Map<String, Integer> SHIP_TYPES = new LinkedHashMap<>();
    static {
        SHIP_TYPES.put("Aircraft Carrier", 5);
        SHIP_TYPES.put("Battleship", 4);
        SHIP_TYPES.put("Submarine", 3);
        SHIP_TYPES.put("Cruiser", 3);
        SHIP_TYPES.put("Destroyer", 2);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

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
    }

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
}
