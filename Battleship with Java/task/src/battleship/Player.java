package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Player {

    private final String name;
    private final Field field;
    private final List<Ship> ships;
    private static final Scanner scanner = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
        this.field = new Field();
        this.ships = new ArrayList<>();
    }

    public void placeShips() {
        System.out.printf("%s, place your ships on the game field%n", name);
        field.printField(field.getGameField());

        for (String shipName : Main.SHIP_TYPES.keySet()) {
            int shipLength = Main.SHIP_TYPES.get(shipName);
            boolean placed = false;

            while (!placed) {
                System.out.printf("Enter the coordinates for %s (%d cells):%n", shipName, shipLength);
                String input = scanner.nextLine();
                String[] coordinates = input.split(" ");

                if (coordinates.length == 2) {
                    try {
                        Ship newShip = new Ship(shipName, shipLength, coordinates[0].toUpperCase(), coordinates[1].toUpperCase());
                        if (field.placeShip(newShip)) {
                            ships.add(newShip);
                            field.printField(field.getGameField());
                            placed = true;
                        } else {
                            System.out.printf("Error! Wrong placement of the %s! Try again.%n", shipName);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Error! Wrong ship location! Try again.");
                }
            }
        }
    }

    public boolean takeTurn(Player opponent) {
        System.out.printf("%s, it's your turn:%n", name);
        field.printField(opponent.field.getFogField());
        System.out.println("-".repeat(20));
        field.printField(field.getGameField());

        System.out.println("\nTake a shot:");
        while (true) {
            String input = scanner.nextLine();
            int[] shotCoords = Main.parseCoordinates(input.toUpperCase());

            if (shotCoords != null) {
                int row = shotCoords[0];
                int col = shotCoords[1];
                boolean hit = opponent.field.takeShot(row, col);

                field.printField(opponent.field.getFogField());
                if (hit) {
                    System.out.println("You hit a ship!");
                    if (checkIfSankShip(opponent, row, col)) {
                        System.out.println("You sank a ship!");
                    }
                    return opponent.allShipsSunk();
                } else {
                    System.out.println("You missed!");
                    return false;
                }
            } else {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }
        }
    }

    private boolean checkIfSankShip(Player opponent, int row, int col) {
        for (Ship ship : opponent.ships) {
            if (ship.coordinates.contains(row + "," + col) && ship.isSunk(opponent.field.getGameField())) {
                return true;
            }
        }
        return false;
    }

    private boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk(field.getGameField())) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }
}
