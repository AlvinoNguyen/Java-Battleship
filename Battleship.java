import java.util.Scanner;

public class Battleship {
    public static void main(String[] args) {
        Player player = new Player(false);
        Player computer = new Player(true);

        printIntroduction();
        placeShips(player);
        Print.clearScreen();

        displayGrid("Your current grid of ships.", player.getPlayersGrid(), Grid.PRINT_SHIPS);
        placeShips(computer);
        Print.readLine("Hit enter to start guessing.");

        askForGuess(computer, player);
        /* int turnCount = 1;
        while(false) {
            if(turnCount % 2 == 0) {
                // Computer's turn
            } else {
                // Player's turn
            }
            turnCount++;
        } */
    }

    private static void printIntroduction() {
        Print.clearScreen();
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
        if(player.isComputer()) {
            Print.readLine("Hit enter for the enemy to choose their ship locations.");
            for(int i = 0; i < 5; i++) {
                Ship cur = player.getShip(i);
                while(true) {
                    int row = (int)(Math.random() * 10);
                    int col = (int)(Math.random() * 10);
                    int direction = (int)(Math.random() * 2);
                    if(player.chooseShipLocation(cur, row, col, direction)) {
                        break;
                    }
                }
            }
            System.out.println("The enemy has placed their ships.");
        } else {
            for(int i = 0; i < 5; i++) {
                Print.readLine("Hit enter to place the next ship.");
                Print.clearScreen();
                displayGrid("Your current grid of ships.", player.getPlayersGrid(), Grid.PRINT_SHIPS);
                Ship cur = player.getShip(i);
                System.out.println("Now you need to place a ship of length " + cur.getLength());
                while(true) {

                    int row;
                    while(true) {
                        row = Print.readLine("Which row? (A-J)").toUpperCase().charAt(0) - 'A';
                        if(row < 0 || row > 9) {
                            System.out.println("Invalid row, please try again.");
                        } else {
                            break;
                        }
                    }

                    int col;
                    while(true) {
                        col = Integer.parseInt(Print.readLine("Which column? (1-10)")) - 1;
                        if(col < 0 || col > 9) {
                            System.out.println("Invalid column, please try again.");
                        } else {
                            break;
                        }
                    }

                    int direction;
                    while(true) {
                        String direction_str = Print.readLine("Horizontal or vertical?");
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

    public static boolean askForGuess(Player player, Player enemy) {
        if(player.isComputer()) {
            Print.readLine("Hit enter for the computer's turn.");
            Print.clearScreen();
            char row;
            char col;
            while(true) {
                row = (char)(Math.random() * 10);
                col = (char)(Math.random() * 10);
                if(!player.getOpponentsGrid().alreadyGuessed(row, col)) {
                    break;
                }
            }
            System.out.println("Computer player guesses row " + ((char)(row + 'A')) + " and column " + (col + 1));
            if(enemy.recordOpponentGuess(row, col)) {
                System.out.println("Computer hit!");
            } else {
                System.out.println("Computer missed.");
            }
            enemy.getPlayersGrid().printStatus();
        }
        return true;
    }
}