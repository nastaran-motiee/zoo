package plants;

import graphics.ZooFrame;
import graphics.ZooPanel;


/**
 * @author baroh
 *
 */
public class Cabbage extends Plant {
	private static volatile Cabbage instance = null;

	/**
	 * TODO:CHANGED
	 * get instance of Cabbage
	 * @return instance of Cabbage
	 */
	public static Cabbage getInstance(){
		if(instance == null){
			synchronized (Cabbage.class){
				if(instance==null){
					instance = new Cabbage(ZooPanel.getInstance());
				}
			}
		}
		return instance;
	}

	private Cabbage(ZooPanel pan){
		super(pan);
	}


	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
