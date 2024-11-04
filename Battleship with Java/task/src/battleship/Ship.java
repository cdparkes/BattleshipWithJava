package battleship;

import java.util.HashSet;
import java.util.Set;

class Ship {
    private final String name;
    private final int length;
    final Set<String> coordinates;

    public Ship(String name, int length, String start, String end) {
        this.name = name;
        this.length = length;
        this.coordinates = new HashSet<>();

        // Parse coordinates
        int[] startCoords = Main.parseCoordinates(start);
        int[] endCoords = Main.parseCoordinates(end);

        // Check alignment: same row for horizontal or same column for vertical
        boolean sameRow = startCoords[0] == endCoords[0];
        boolean sameColumn = startCoords[1] == endCoords[1];

        if (!(sameRow || sameColumn)) {
            throw new IllegalArgumentException(("Error! Wrong ship location! Try again:"));
        }

        // Initialize and validate coordinates based on the alignment
        initializeCoordinates(startCoords, endCoords);

        // Validate the length of the ship based on coordinates
        if (coordinates.size() != length) {
            throw new IllegalArgumentException("Error! Wrong length of the " + name + "! Try again:");
        }
    }

    private void initializeCoordinates(int[] startCoords, int[] endCoords) {
        int row1 = startCoords[0], col1 = startCoords[1];
        int row2 = endCoords[0], col2 = endCoords[1];

        if (row1 == row2) { // Horizontal placement
            for (int col = Math.min(col1, col2); col <= Math.max(col1, col2); col++) {
                coordinates.add(row1 + "," + col);
            }
        } else { // Vertical placement
            for (int row = Math.min(row1, row2); row <= Math.max(row1, row2); row++) {
                coordinates.add(row + "," + col1);
            }
        }
    }

    public String getName() {
        return name;
    }

    public boolean isSunk(char[][] gameField) {
        for (String coord : coordinates) {
            String[] parts = coord.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            if (gameField[row][col] == 'O') {
                return false;
            }
        }
        return true;
    }
}

