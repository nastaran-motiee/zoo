
package animals;

import controller.Controller;
import decoretor.IColorDecoretor;
import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import graphics.ZooPanel;
import memento.CareTaker;
import memento.Memento;
import mobility.ILocatable;
import mobility.Mobile;
import mobility.Point;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import static privateutil.MyStrings.*;

/**
 * The class Animal defines the characteristics common to all animals.
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 2.0 April 28,2022
 */
public  abstract class Animal extends Mobile implements Runnable, IEdible, IDrawable, IAnimalBehavior {
    protected boolean threadSuspended = false;
    protected Point location;

    private final int EAT_DISTANCE = 10;
    private int size;
    private String col;
    private int horSpeed;
    private int verSpeed;
    private boolean coordChanged;
    private int x_dir = 1;
    private int y_dir = 1;
    private int eatCount;
    private final ZooPanel pan;
    private BufferedImage img1, img2;
    private String name;
    private double weight;
    private final IDiet diet;//used for eating appropriate food.
    private boolean killed = false;
    private boolean isDead = false;
    protected static ArrayList<IEdible> availableFood ;

    protected static ArrayList<Animal> availableAnimals ;
    private Controller controller = Controller.getInstance();
    private CareTaker careTaker = new CareTaker();



    //Constructors----------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Animal constructor
     * @param size size of animal
     * @param horSpeed horizontal speed of animal
     * @param verSpeed vertical speed of animal
     * @param col color of animal
     * @param diet diet of animal
     */
    protected Animal(int size, int horSpeed, int verSpeed, String col, IDiet diet, Point location, ZooPanel pan){
        super(location);
        this.name = this.getClass().getSimpleName();
        this.size = size;
        this.horSpeed = horSpeed;
        this.verSpeed = verSpeed;
        this.col = col;
        this. diet =(IDiet)diet.replicate();
        this.pan = pan;
    }



    @Override
    public void run() {
        double new_x = 0;
        double new_y = 0;

        while(!threadSuspended ) {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                System.out.println(Arrays.toString(e.getStackTrace()));
            }

            new_x = this.getLocation().getX() + this.getHorSpeed() * getX_direction();
            new_y = this.getLocation().getY() + this.getVerSpeed() * getY_direction();
            this.move(new Point(new_x, new_y));

            if(availableFood != null){
                synchronized (availableFood){
                    Iterator<IEdible> foodIterator = availableFood.iterator();
                    while (foodIterator.hasNext()){
                        IEdible food = foodIterator.next();
                        if(this.getDiet().canEat(food.getFoodtype())){
                            this.move(goToCenter());
                            if(this.eat(food)){
                                controller.foodWasEaten(food);
                                foodIterator.remove();

                            }
                        }

                    }
                }
            }

            if(availableAnimals != null){
               synchronized (availableAnimals){
                   Iterator<Animal> animalIterator = availableAnimals.iterator();
                    while (animalIterator.hasNext()){
                        Animal animal = animalIterator.next();
                        if(animal != this){
                            if(this.eat(animal)){
                                animal.killAnimal();
                                while(!animal.isDead());
                                animalIterator.remove();


                            }
                        }

                    }
               }
            }

            if (threadSuspended){
                try {
                    synchronized (this){
                        this.wait();
                    }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(killed){
                this.isDead();
                System.out.println("I am dead !");
                return;
            }
        }


    }

    /**
     *
     * @return new Memento(color of animal)
     */
    public Memento createMemento(){
        return new Memento(this.getColor());
    }

    public void setMemento(Memento memento){
        setColor(memento.getState());

    }

   public void saveColorHistory(){
        Memento memento = this.createMemento();
        careTaker.addMemento(memento);

   }

   public void backToPreviousColor(){
        Memento memento = careTaker.getMemento();
        if(memento == null){
            JOptionPane.showMessageDialog(pan, "No previous Color");
        }
        if(memento != null){
            setMemento(memento);
            System.out.println(getColor());
            this.loadImages(this.getClass().getSimpleName());
        }


   }
    //Setters----------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * set weight, if weight >= 0
     * return true if weight was set, else return false
     * @param weight weight
     */
     public boolean setWeight(double weight) {
        boolean is_successful = (weight >= 0);
        if(is_successful){
            this.weight = weight;
        }

        return is_successful;
    }

    /**
     * set color
     * @param col color
     * @return true
     */
    public boolean setColor(String col){
         this.col = col;
         return true;

    }

    /**
     * sets the size of the animal if the parameter size > 0
     * @param size size of the animal
     * @return true if size>0 else return false
     */
     public boolean setSize(int size){
        boolean is_successful = (size > 0);
        if(is_successful){
            this.size = size;
        }
        return is_successful;

    }

    /**
     * set x direction
     * @param x_dir x direction (1 for moving right , -1 for moving left)
     * @return true if was successfully set, else return false
     */
    public boolean setX_direction(int x_dir){
        boolean is_successful = (x_dir == -1 || x_dir == 1);
        if(is_successful) {
            this.x_dir = x_dir;
        }
        return is_successful;
    }

    /**
     * set y direction
     * @param y_dir y direction (1 for moving up, -1 for moving down)
     * @return if was successfully set, else return false
     */
    public boolean setY_direction(int y_dir){
        boolean is_successful = (y_dir == -1 || y_dir == 1);
        if(is_successful){
            this.y_dir = y_dir;
        }
        return is_successful;
    }

    /**
     * set horizontal speed
     * @param horSpeed
     */
    public void setHorSpeed(int horSpeed) {
        this.horSpeed = horSpeed;
    }

    /**
     * set vertical speed
     * @param verSpeed
     */
    public void setVerSpeed(int verSpeed){
        this.verSpeed = verSpeed;
    }

    public void goEatFood(ArrayList<IEdible> availableFood){
        Animal.availableFood = availableFood;
    }

    public void goEatOtherAnimals(ArrayList<Animal> availableAnimals){
        Animal.availableAnimals = availableAnimals;
    }
    //Getters---------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * get x direction
     * @return x direction
     */
     public int getX_direction(){
        return this.x_dir;
    }

    /**
     * get y direction
     * @return y direction
     */
    public int getY_direction(){
        return this.y_dir;
    }
    /**
     * @return the distance that animal has to be from the food, to eat it
     * (food is not another animal)
     */
    public int getEatDistance(){
        return this.EAT_DISTANCE;
    }
    /**
     * @return horizontal speed of animal
     */
    public int getHorSpeed(){
        return this.horSpeed;
    }

    /**
     * @return vertical speed of animal
     */
     public int getVerSpeed(){
        return this.verSpeed;
    }

    /**
     * get name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * get weight
     * @return weight
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     *
     * @return diet
     */
     public IDiet getDiet() {
        return (IDiet) diet.replicate();
    }

    /**
     * tells which type of food is this animal; every animal's food type is MEAT except lions.
     * @return food type
     */
     public EFoodType getFoodtype() {
         if(this instanceof Lion){
             return EFoodType.NOTFOOD;
         }
        return EFoodType.MEAT;

    }

    /**
     * getSize method
     * @return the size of the animal
     */
    public int getSize(){
        return this.size;
    }

    /**
     * @return color of animal
     */
     public String getColor(){
        return this.col;

    }

    /**
     * @return how much the animal ate
     */
    public int getEatCount(){
        return this.eatCount;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------

    synchronized public void killAnimal(){
        killed = true;

    }

    synchronized public boolean isDead(){
        isDead = true;
        return isDead;
    }
    /**
     * Make sound.
     * this method activates the method roar for animals that can roar
     * and activate the method chew for animals that can chew
     */
    public abstract void makeSound();

    /**
     * check if there is a need to change the direction .
     * if there is, there change the direction
     */
    public void checkDirectionChange(Point point){

        if(getX_direction() == 1 && point.getX() + getSize() >= Point.getMax_x()){
            setX_direction(-1);
            System.out.println("X Direction %d"+getX_direction());
        }else if(getX_direction() == -1 && point.getX()  <= Point.getMin_x()){
            setX_direction(1);
            System.out.println("X Direction %d"+getX_direction());
        }else if (getY_direction() == 1 && point.getY() + getSize() >= Point.getMax_y()) {
            setY_direction(-1);
            System.out.println("Y Direction %d"+getY_direction());
        }else if(getY_direction() == -1 && point.getY() <= Point.getMin_y()){
            setY_direction(1);
            System.out.println("Y Direction %d"+getY_direction());
        }

    }


    /**
     *
     * @return the next point on the way to the center
     */
    public Point goToCenter(){
        double x_center =  pan.getWidth()/2.0;
        double y_center = pan.getHeight()/2.0;
        double last_x = this.getLocation().getX();
        double last_y = this.getLocation().getY();
        if(last_x < x_center - getSize()){
            setX_direction(1);
        }else if(last_x > x_center + getSize()){
            setX_direction(-1);
        }
        if(last_y <= y_center){
            setY_direction(1);
        }else {
            setY_direction(-1);
        }
        double new_x = last_x+(((last_x + x_center) /2.0) - ((last_x + x_center)/2.0 -1)) * getX_direction();
        double new_y = last_y+(((last_y + y_center)/2.0) - ((last_y + y_center)/2.0 -1)) * getY_direction();


        return new Point(new_x,new_y);
    }

    /**
     *
     * @param point the point
     * @return distance traveled
     */
    @Override
    public double  move(Point point){
            double distance_traveled = super.move(point);
            checkDirectionChange(point);
            setWeight(getWeight() - (distance_traveled * getWeight() * 0.00025));
            this.setChanges(true);
            pan.repaint();
            return distance_traveled;

    }



    /**
     * Eat boolean.
     * checks if the animal can eat the food , if yes , eat it
     * after eating the animal makes a sound
     * @param edible some edible object
     * @return true if the animal could eat, else return false
     */
    public boolean  eat(IEdible edible){
        boolean is_successful;

        if(edible instanceof Animal){
            is_successful = ((this.calcDistance(((ILocatable) edible).getLocation()) < ((Animal) edible).getSize())&& (this.getWeight() >= ((Animal) edible).getWeight() * 2)
            && this.getDiet().canEat(edible.getFoodtype()));

        }else{
            is_successful= (this.getDiet().canEat(edible.getFoodtype()) && this.calcDistance(((ILocatable) edible).getLocation()) < this.getEatDistance());


        }
        if(is_successful){
            synchronized (diet){
                diet.eat(this, edible);
                this.eatInc();
                this.makeSound();
            }
        }
        return is_successful;
    }


    /**
     * Increase the eatCount field of the animal by one
     */
    synchronized public void eatInc(){
        this.eatCount += 1;

    }


    /**
     * load Images of th animal
     * @param nm animal name(Bear, Lion,etc)
     */
    public void loadImages(String nm){
        switch(nm){
            case BEAR-> {
                switch (this.getColor()){
                    case BLUE -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"bea_b_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"bea_b_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case NATURAL -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"bea_n_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"bea_n_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case RED -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"bea_r_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"bea_r_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }
                }
            }

            case ELEPHANT-> {
                switch (this.getColor()){
                    case BLUE -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"elf_b_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"elf_b_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case NATURAL -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"elf_n_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"elf_n_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case RED -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"elf_r_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"elf_r_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }
                }
            }

            case GIRAFFE-> {
                switch (this.getColor()){
                    case BLUE -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"grf_b_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"grf_b_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case NATURAL -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"grf_n_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"grf_n_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case RED -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"grf_r_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"grf_r_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }
                }

            }

            case LION-> {
                switch (this.getColor()){
                    case BLUE -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"lio_b_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"lio_b_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case NATURAL -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"lio_n_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"lio_n_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case RED -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"lio_r_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"lio_r_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }
                }
            }

            case TURTLE-> {
                switch (this.getColor()){
                    case BLUE -> {
                        try {
                            this.img1 = ImageIO.read(new File("src/assignment2_pictures/trt_b_1.png"));
                            this.img2 = ImageIO.read(new File("src/assignment2_pictures/trt_b_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case NATURAL -> {
                        try {
                            this.img1 = ImageIO.read(new File("src/assignment2_pictures/trt_n_1.png"));
                            this.img2 = ImageIO.read(new File("src/assignment2_pictures/trt_n_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case RED -> {
                        try {
                            this.img1 = ImageIO.read(new File("src/assignment2_pictures/trt_r_1.png"));
                            this.img2 = ImageIO.read(new File("src/assignment2_pictures/trt_r_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }
                }
            }
        }


    }


    /**
     *
     * @return which kind of animal is this
     */
    public String getAnimalName(){
        return this.getClass().getSimpleName();
    }


    /**
     *
     * @return true if coordinates of animal changed else return false
     */
    public boolean getChanges (){
        return this.coordChanged;

    }

    /**
     * @param state set to true if coordinates of animal changes, else set to false
     */
    synchronized public void setChanges (boolean state){
        this.coordChanged = state;

    }

    synchronized public void setSuspended(){ //TODO: animal wait now
            threadSuspended = true;
    }

    synchronized public void setResumed(){
            threadSuspended = false;
            this.notify();

    }

    /**
     * TODO: get it back to assignment1 , and use instead it getAnimalName
     * toString
     * @return name of class
     */
    public String toString(){
        return this.getClass().getSimpleName();
    }


    public void drawObject (Graphics g) {
        if(x_dir == 1 ) {
            g.drawImage(img1, (int)getLocation().getX(), (int)getLocation().getY(), getSize(), getSize(), pan);
        }

          else if(x_dir == -1) {
            g.drawImage(img2, (int) getLocation().getX(), (int) getLocation().getY(), getSize(), getSize(), pan);
        }

    }


}






