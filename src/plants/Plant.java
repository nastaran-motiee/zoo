package plants;

import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Random;

import static privateutil.MyStrings.CABBAGE;
import static privateutil.MyStrings.LETTUCE;

/**
 * @author baroh
 *
 */
public abstract class Plant implements IEdible, ILocatable, IDrawable {
	/**
	 * 
	 */
	private double height;
	/**
	 * 
	 */
	private Point location;
	/**
	 * 
	 */
	private double weight;
	private ZooPanel pan;
	private BufferedImage img;

	/**
	 * plant constructor
	 * @param pan
	 */
	public Plant(ZooPanel pan){
		this.pan = pan;
		this.location = new Point(pan.getSize().getWidth()/2 - getHeight()/2, pan.getSize().getHeight()/2-getHeight()/2);
		//screenSize.width / 2 - getSize().width / 2, screenSize.height / 2 - getSize().height / 2


	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see food.IFood#getFoodtype()
	 */
	@Override
	public EFoodType getFoodtype() {
		return EFoodType.VEGETABLE;
	}

	/**
	 * @return
	 */
	public double getHeight() {
		return this.height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mobility.ILocatable#getLocation()
	 */
	@Override
	public Point getLocation() {
		return this.location;
	}

	/**
	 * @return
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param height
	 * @return
	 */
	public boolean setHeight(double height) {

		boolean isSuccess = (height >= 0);
		if (isSuccess) {
			this.height = height;
		} else {
			this.height = 0;
		}
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mobility.ILocatable#setLocation(mobility.Point)
	 */
	@Override
	public boolean setLocation(Point newLocation) {
		boolean isSuccess = Point.checkBoundaries(newLocation);
		if (isSuccess) {
			this.location = newLocation;
		}
		return isSuccess;
	}

	/**
	 * @param weight
	 * @return
	 */
	public boolean setWeight(double weight) {
		boolean isSuccess = (weight >= 0);
		if (isSuccess) {
			this.weight = weight;
		} else {
			this.weight = 0;
		}

		return isSuccess;
	}


	/**
	 * load images for the plant
	 * @param nm name of the plant
	 */
	public void loadImages(String nm){
		switch (nm){
			case CABBAGE -> {
				try {
					this.img = ImageIO.read(new File(PICTURE_PATH+"cabbage.png"));
				} catch (IOException exception) {
					System.out.println("Image doesn't exist");
				}
			}

			case LETTUCE -> {
				try {
					this.img = ImageIO.read(new File(PICTURE_PATH+"lettuce.png"));
				} catch (IOException exception) {
					System.out.println("Image doesn't exist");
				}
			}
		}
	}

	public String getColor(){
		return "";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] ";
	}

	/**
	 * draw the plant
	 * @param g
	 */
	public void drawObject (Graphics g) {

		g.drawImage(img, (int)this.getLocation().getX(), (int)this.getLocation().getY(), 40,40, pan);

	}

}
