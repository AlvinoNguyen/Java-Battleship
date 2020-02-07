import java.util.Scanner;

public class Battleship {
    public static void main(String[] args) {
        Player player = new Player();
        Player computer = new Player();

        printIntroduction();
        placeShips(player);
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

    private static void placeShips(Player player) {
        for(int i = 0; i < 5; i++) {
            System.out.print("Hit enter to place the next ship.");
            readLine();
            clearScreen();
            System.out.println("Your current grid of ships.");
            player.getPlayersGrid().printShips();
            Ship cur = player.getShip(i);
            System.out.println("Now you need to place a ship of length " + cur.getLength());
            System.out.print("Which row? (A-J) ");
            String row = readLine();
            System.out.print("Which column? (1-10) ");
            String col = readLine();
            System.out.print("Horizontal or vertical? ");
            String direction = readLine();
        }
    }
}