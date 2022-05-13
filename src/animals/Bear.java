package animals;
import graphics.ZooPanel;
import mobility.Point;
import diet.Omnivore;
import privateutil.RoarerAnimal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class Bear.
 * Bear is omnivore
 *initial location of a bear is Point(100, 5)
 * bear is omnivore
 * @see Omnivore
 * @see Animal
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 2.0 April  28,22
 */
public class Bear extends RoarerAnimal{



    private enum colors{BLACK, WHITE,GRAY};//Bear can be BLACK , WHITE OR GRAY
    private String furColor = colors.GRAY.toString();

    //Constructors----------------------------------------------------------------------------------------------------------------------------------------------------------


    /**
     * Bear constructor.
     * sets the weight of animal (weight = size * 1.5)
     * @param size size of animal
     * @param horSpeed horizontal speed of animal
     * @param verSpeed vertical speed of animal
     * @param col color of animal
     */
    public Bear(int size, int horSpeed, int verSpeed, String col, ZooPanel pan){
        super(size, horSpeed, verSpeed, col, new Omnivore(), new Point(100, 5), pan);
        this.setWeight(size * 1.5);
    }




    //Setters--------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * set the size of bear
     * every the size of animal changes, the weight of animal changes too (weight = size * 1.5)
     * @param size size of the animal
     * @return if size > 0 && weight > 0 return true, else return false
     */
    public boolean setSize(int size){
       return super.setSize(size) && this.setWeight(size * 1.5);
    }


    //Getters--------------------------------------------------------------------------------------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * roar.prints "Stands on its hind legs, roars and scratches its belly" when the bear roars.
     */
    public void roar(){
        System.out.println(getName()+": Stands on its hind legs, roars and scratches its belly");

    }

}
