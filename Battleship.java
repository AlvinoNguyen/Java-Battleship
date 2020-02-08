import java.util.Scanner;

public class Battleship {
    public static void main(String[] args) {
        Player player = new Player();
        Player computer = new Player();

        printIntroduction();
        placeShips(player);
        clearScreen();

        displayGrid("Your current grid of ships.", player.getPlayersGrid(), Grid.PRINT_SHIPS);
        System.out.print("Hit enter for the enemy to choose their ship locations. ");
        readLine();
        System.out.println("The enemy has placed their ships.");
        System.out.print("Hit enter to start guessing. ");
        readLine();
    }

    // Clears the current screen
    private static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    // Reads and returns the string read from the user
    private static String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void printIntroduction() {
        clearScreen();
        System.out.println("=======================");
        System.out.println("Welcome to Battle Ship");
        System.out.println("=======================");
        System.out.println("Time to place your ships.");
    }

    private static void displayGrid(String message, Grid grid, int printType) {
        System.out.println(message);
        if(printType == Grid.PRINT_STATUS) {
            grid.printStatus();
        } else {
            grid.printShips();
        }
    }

    private static void placeShips(Player player) {
        for(int i = 0; i < 5; i++) {
            System.out.print("Hit enter to place the next ship.");
            readLine();
            clearScreen();
            displayGrid("Your current grid of ships.", player.getPlayersGrid(), Grid.PRINT_SHIPS);
            Ship cur = player.getShip(i);
            System.out.println("Now you need to place a ship of length " + cur.getLength());
            while(true) {

                int row;
                while(true) {
                    System.out.print("Which row? (A-J) ");
                    row = readLine().toUpperCase().charAt(0) - 'A';
                    if(row < 0 || row > 9) {
                        System.out.println("Invalid row, please try again.");
                    } else {
                        break;
                    }
                }

                int col;
                while(true) {
                    System.out.print("Which column? (1-10) ");
                    col = Integer.parseInt(readLine()) - 1;
                    if(col < 0 || col > 9) {
                        System.out.println("Invalid column, please try again.");
                    } else {
                        break;
                    }
                }

                int direction;
                while(true) {
                    System.out.print("Horizontal or vertical? ");
                    String direction_str = readLine();
                    if(direction_str.toUpperCase().charAt(0) == 'H') {
                        direction = Ship.HORIZONTAL;
                        break;
                    } else if (direction_str.toUpperCase().charAt(0) == 'V') {
                        direction = Ship.VERTICAL;
                        break;
                    } else {
                        System.out.println("Invalid direction, please try again.");
                    }
                }

                if(player.chooseShipLocation(cur, row, col, direction)) {
                    break;
                } else {
                    System.out.println("Invalid ship placement. Please try again.");
                }
            }
        }
    }
}