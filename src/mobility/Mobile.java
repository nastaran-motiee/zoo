package mobility;


import java.awt.image.TileObserver;
import java.util.Observable;

/**
 * The abstract class Mobile defines movement in space.
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 March 27,22
 */

public abstract class Mobile implements ILocatable {
    private Point location; // Current location
    private double totalDistance;// Distance the object traveled, [>0]

    //Constructors-------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Mobile constructor.
     * @param location location
     */
    public Mobile(Point location){
        this.location = location.replicate();
        this.totalDistance = 0;
    }

    //Gettres------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @return current location
     */
    @Override
    synchronized public Point getLocation() {
        return this.location;
    }

    /**
     *
     * @return total distance
     */
    synchronized public double getTotalDistance() {
        return totalDistance;
    }


    //Setters-----------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * set Location
     * @param location location
     * @return true if the location is inside the boundaries and the setting was successful, else return false
     */
    @Override
    synchronized public boolean setLocation(Point location) {
        boolean is_successful = Point.checkBoundaries(location);
        if(is_successful){
            this.location = location.replicate();
        }else{
            System.out.println(location +" is out of the legal boundaries !");
        }

        return is_successful;
    }

    /**
     * sets total distance
     * @param totalDistance total distance from the first point.
     * @return true if the setter totalDistance >= = and the setting was successful, else return false
     */
    synchronized public boolean setTotalDistance(double totalDistance) {
        boolean is_successful = (totalDistance >=0);
        this.totalDistance = totalDistance;
        return is_successful;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Adds the last_movement to the total distance is the parameter is not negative
     * else prints a message "ERROR! Distance can't be negative !"
     * @param distance_from_last_point distance from last point.
     */
    synchronized public void addTotalDistance(double distance_from_last_point){
        if(distance_from_last_point >= 0){
            setTotalDistance(getTotalDistance() + distance_from_last_point);
        }else{
            System.err.println("ERROR! Distance can't be negative !");
        }

    }

    /**
     * Calculate distance.
     *
     * @param point  point
     * @return the distance
     */
     synchronized public double calcDistance(Point point){
        return Math.sqrt( Math.pow(this.getLocation().getX()-point.getX(),2) + Math.pow(this.getLocation().getY()-point.getY(),2));
    }

    /**
     * checks if the point is in the legal bounds
     * if yes, move
     * @param point the point
     * @return distance traveled
     */
    synchronized public double move(Point point){
        double distance_traveled = 0;

        if(Point.checkBoundaries(point)){
            distance_traveled = calcDistance(point);
            addTotalDistance(distance_traveled);
            setLocation(point);
        }

        return distance_traveled;
    }


}
