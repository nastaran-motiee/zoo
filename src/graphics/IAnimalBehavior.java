package graphics;

import java.beans.PropertyChangeSupport;
import java.util.Properties;

/**
 * /TODO: 2 new method in IAnimalBehavior
 * The interface Animal behavior.
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 2.0 May 12,22
 */
public interface IAnimalBehavior {


    public String getAnimalName();
    public int getSize();
    public void eatInc();
    public int getEatCount();
    public boolean getChanges ();
    public void setChanges (boolean state);
    public void setSuspended(); //TODO: animal wait now
    public void setResumed();//TODO: animal stops waiting
}
