public class Location {

    public static final int UNGUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;

    private int status;
    private boolean hasShip;    

    // Location constructor. 
    public Location() {
        this.status = UNGUESSED;
        this.hasShip = false;
    }

    // Was this Location a hit?
    public boolean checkHit() {
        return this.status == HIT;
    }

    // Was this location a miss?
    public boolean checkMiss() {
        return this.status == MISSED;
    }

    // Was this location unguessed?
    public boolean isUnguessed() {
        return this.status == UNGUESSED;
    }

    // Mark this location a hit.
    public void markHit() {
        this.status = HIT;
    }

    // Mark this location a miss.
    public void markMiss() {
        this.status = MISSED;
    }

    // Return whether or not this location has a ship.
    public boolean hasShip() {
        return this.hasShip;
    }

    // Set the value of whether this location has a ship.
    public void setShip(boolean val) {
        this.hasShip = val;
    }

    // Set the status of this Location.
    public void setStatus(int status) {
        this.status = status;
    }

    // Get the status of this Location.
    public int getStatus() {
        return this.status;
    }

}