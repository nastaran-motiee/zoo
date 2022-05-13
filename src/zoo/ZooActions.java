package zoo;
import animals.*;
import food.IEdible;
import graphics.ZooFrame;
import graphics.ZooPanel;
import mobility.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static privateutil.MyStrings.MOVE;

/**
 * the class ZooActions
 * this class includes the main method
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 2.0 April 30,22
 */
public class ZooActions {
    private static  List<Animal> animals = new ArrayList<>();
    private static int array_size = 0;




 //move + moveSimulation-----------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * this method can get every kind of animal
     * and checks which class the animal belongs to
     * the method checks if the animal move (if the point is inside the legal bounds);
     * if yes, performs a simulation of movement of the animals
     * @see Animal
     * @see mobility.Mobile
     * @param animal animal
     * @param point point
     * @return true if animal could move to point, else return false
     */
    private static boolean move(Object animal, Point point){
        double distance_traveled = 0;
        // actually there is no need to do the conversion here ; but it is required in the assignment
        if(Point.checkBoundaries(point)){
            if(animal instanceof Bear){
                distance_traveled = ((Bear) animal).move(point);
            }else if(animal instanceof Elephant){
                distance_traveled = ((Elephant) animal).move(point);
            }else if(animal instanceof  Giraffe){
                distance_traveled = ((Giraffe)animal).move(point);
            }else if(animal instanceof  Lion){
                distance_traveled = ((Lion) animal).move(point);
            }else if(animal instanceof  Turtle){
                distance_traveled = ((Turtle) animal).move(point);
            }

       }


        return distance_traveled != 0;
    }

    /**
     * This method performs a simulation of movement of the animals
     * uses random points
     * @param animals animals array list
     */
    private static void moveSimulation(List<Animal> animals){
        System.out.println("🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭");
        System.out.println("                     Movement Simulation                    ");
        System.out.println("🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭🧭");
        Random random_number = new Random();
        int x = random_number.nextInt(800), y = random_number.nextInt(800);
        for (Animal animal : animals) {
            if (animal != null) {
                move(animal, new Point(x, y));
                x = random_number.nextInt(1000);
                y = random_number.nextInt(1000);
            }

        }
    }


//eat + eatSimulation-------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * this method can get every kind of animal
     * and checks which class the animal belongs to
     * the method checks if the animal can eat the food; if yes, eat it
     * @see Animal#eat(IEdible)
     * @param animal animal
     * @param food food
     * @return true if animal could eat the food, else return false
     */
    private static boolean eat(Object animal, IEdible food){
        boolean ate = false;
        // actually there is no need to do the conversion here ; but it is required in the assignment
        if(animal instanceof Bear){
            ate = ((Bear) animal).eat(food);
        }else if(animal instanceof Elephant){
            ate = ((Elephant) animal).eat(food);
        }else if(animal instanceof  Giraffe){
            ate = ((Giraffe)animal).eat(food);
        }else if(animal instanceof  Lion){
            ate = ((Lion) animal).eat(food);
        }else if(animal instanceof  Turtle){
            ate = ((Turtle) animal).eat(food);
        }
        // if(animal instanceof Animal){
        //    ate = ((Animal) animal).eat(food);
        // }
        return ate;
    }

    /**
     * This method performs a simulation of eating
     * each time will pick two random animals and one will eat the other if it can
     * @param animals animals list
     */
    private static void eatSimulation(List<Animal> animals){
        System.out.println("🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲");
        System.out.println("                  Random Eating Simulation                 ");
        System.out.println("🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲🍲");
        int num_of_loops = animals.size() / 2;

        Random random = new Random();
        int eater_index = 0, food_index = 0;
        for(int i = 0; i < num_of_loops; i++){
            eater_index = random.nextInt(animals.size());
            food_index = random.nextInt(animals.size());
            while (eater_index == food_index){
                eater_index = random.nextInt(animals.size());
                food_index = random.nextInt(animals.size());
            }
            if(eat(animals.get(eater_index), animals.get(food_index))){
                animals.remove(food_index);
            }
        }

    }
//---------------------------------------------------------------------------------------------------------------------------------------------------------

}
