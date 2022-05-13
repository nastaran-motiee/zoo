package mobility;

import privateutil.Replicable;

/**
 * The class Point defines position on a 2D axis
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 March 27,22
 */
public class Point implements Replicable {

    private double x;
    private double y;
    private static final double min_x = 0;
    private static final double min_y = 0;
    private static final double max_x = 800;
    private static final double max_y = 600;

    //Constructors-------------------------------------------------------------------------------------------------------------------------------------------------

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    //Setters---------------------------------------------------------------------------------------------------------------------------------------------------------

    public boolean setX(double x){
        boolean is_success = (x >= getMin_x() && x <= getMax_x());
        if(is_success){
            this.x = x;
        }
        return is_success;
    }

    public boolean setY(double y){
        boolean is_success = (y >= getMin_y() && y <= getMax_y());
        if(is_success){
            this.y = y;
        }
        return is_success;
    }
    //Getters---------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * getX
     * @return x
     */
    public double getX(){
        return this.x;
    }

    /**
     * getY
     * @return y
     */
    public double getY() {
        return this.y;
    }

    /**
     *
     * @return minimum value of x
     */
    public static double getMin_x(){
        return min_x;
    }

    /**
     *
     * @return minimum value of y
     */
    public static double getMin_y(){
        return min_y;
    }

    /**
     *
     * @return maximum value of x
     */
    public static double getMax_x(){
        return max_x;
    }

    /**
     *
     * @return maximum value of y
     */
    public static double getMax_y(){
        return max_y;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * checks if the point is in the legal bounds of Point class
     * @param point_to_check point to check
     * @return true if the point is in the legal bounds, else return false
     */
    public static boolean checkBoundaries(Point point_to_check){
        return point_to_check.getX() >= getMin_x() && point_to_check.getX() <= getMax_x() &&
                point_to_check.getY() >= getMin_y() && point_to_check.getY() <= getMax_y();
    }
    /**
     * make a copy of the object
     * @return a copy of point
     */

    @Override
    public Point replicate() {
        return new Point(this.getX(),this.getY());
    }

    /**
     * toString
     * @return Point(this.x,this.y)
     */
    public String toString(){
        return "Point(" + getX() + "," + getY() + ")";
    }
}
