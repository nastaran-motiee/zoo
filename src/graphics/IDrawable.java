package graphics;

import javax.swing.*;
import java.awt.*;

/**
 * The interface Drawable.
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 April 20,22
 */
public interface IDrawable {
    public final static String PICTURE_PATH = "src/assignment2_pictures/";
    public void loadImages(String nm);
    public void drawObject (Graphics g);
    public String getColor();
}
