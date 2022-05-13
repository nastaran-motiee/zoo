package mobility;

/**
 * The interface Ilocatable defines location functionality.
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 March 27,22
 */
public interface ILocatable {

    /**
     * getLocation.
     * @return the location
     */
    Point getLocation();

    /**
     * SetLocation.
     *
     * @param point the point
     * @return true if the location was set successfully.
     */
    boolean setLocation(Point point);
}
