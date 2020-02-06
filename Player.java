public class Player {
    
    // These are the lengths of all of the ships.
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};

    // Instance variables
    private Ship[] ships;
    private Grid playersGrid;
    private Grid opponentsGrid;

    // Player constructor
    public Player() {
        ships = new Ship[5];
        for(int i = 0; i < SHIP_LENGTHS.length; i++) {
            ships[i] = new Ship(SHIP_LENGTHS[i]);
        }
        playersGrid = new Grid();
        opponentsGrid = new Grid();
    }

    // Sets a ship’s row, column and direction
    // and adds it to the current player’s grid
    public void chooseShipLocation(Ship s, int row, int col, int direction) {
        s.setLocation(row, col);
        s.setDirection(direction);
        playersGrid.addShip(s);
    }

    // Takes in an opponent guess for a row, col location,
    // and records the guess, and returns a boolean indicating
    // whether the guess was a hit
    public boolean recordOpponentGuess(int row, int col) {
        if(playersGrid.hasShip(row, col)) {
            playersGrid.markHit(row, col);
            return true;
        } else {
            playersGrid.markMiss(row, col);
            return false;
        }
    }

    // Returns the ship indexed at x
    public Ship getShip(int x) {
        return ships[x];
    }

    // Returns the player's grid
    public Grid getPlayersGrid() {
        return playersGrid;
    }

    // Returns the opponent's grid
    public Grid getOpponentsGrid() {
        return opponentsGrid;
    }

}