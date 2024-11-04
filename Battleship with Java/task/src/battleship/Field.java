package battleship;

public class Field {

    private static final int SIZE = 10;
    private final char[][] gameField = new char[SIZE][SIZE];
    private final char[][] fogField = new char[SIZE][SIZE];

    public Field() {
        initializeField(gameField);
        initializeField(fogField);
    }

    public boolean isValidPlacement(Ship ship) {
        for (String coord : ship.coordinates) {
            String[] parts = coord.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            // Check if the cell is already occupied
            if (gameField[row][col] == 'O') {
                return false;
            }

            // Check adjacent cells to prevent ships from being placed too close
            for (int i = Math.max(0, row - 1); i <= Math.min(SIZE - 1, row + 1); i++) {
                for (int j = Math.max(0, col - 1); j <= Math.min(SIZE - 1, col + 1); j++) {
                    if (gameField[i][j] == 'O') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void initializeField(char[][] field) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = '~';
            }
        }
    }

    public boolean placeShip(Ship ship) {
        if (!isValidPlacement(ship)) {
            return false; // Invalid placement
        }
        for (String coord : ship.coordinates) {
            String[] parts = coord.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            gameField[row][col] = 'O';
        }
        return true;
    }

    public char[][] getGameField() {
        return gameField;
    }

    public char[][] getFogField() {
        return fogField;
    }

    public boolean takeShot(int row, int col) {
        if (gameField[row][col] == 'O') {
            gameField[row][col] = 'X';
            fogField[row][col] = 'X';
            return true; // Hit
        } else {
            gameField[row][col] = 'M';
            fogField[row][col] = 'M';
            return false; // Miss
        }
    }

    public void printField(char[][] field) {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }
}
