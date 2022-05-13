package plants;

import graphics.ZooPanel;

import java.util.logging.Level;


/**
 * @author baroh
 *
 */
public class Lettuce extends Plant {
	private static volatile Lettuce instance = null;

	/**
	 * TODO:CHANGED
	 * get instance of Lettuce
	 * @return instance of Lettuce
	 */
	public static Lettuce getInstance(){
		if(instance == null){
			synchronized (Lettuce.class){
				if(instance==null){
					instance = new Lettuce(ZooPanel.getInstance());
				}
			}
		}
		return instance;
	}

	private Lettuce(ZooPanel pan){
		super(pan);
	}


	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
