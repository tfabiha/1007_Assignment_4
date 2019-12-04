import java.awt.*;
import java.util.*;

/**
 * @author Tabassum Fabiha | tf2478
 * 
 * This class holds and draws MovingObjects in relation to itself. It is also able to 
 * translate and all the MovingObjects it holds are able to translate with the it. Upon
 * adding a new MovingObject to the database the peloton will sort it into it's collection
 * in accordance to the lowest y value of the object
 *
 */
public class Peloton implements MovingObject {
	/**
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param height height of the peloton
	 * Sets the variables appropriately and creates a new instance of an ArrayList that will
	 * hold MovingObjects.
	 */
	public Peloton(int x, int y, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		movingObjects = new ArrayList<MovingObject>();
	}
	
	/**
	 * @param obj MovingObject to be added to the peloton
	 * Adds obj into the database sorted by increasing bottom y-coordinates  
	 */
	public void addMovingObject(MovingObject obj) {
		int i = 0;
		while (i < movingObjects.size()) {
			Car currObj = (Car) movingObjects.get(i);
			if (currObj.getBottomY() > obj.getBottomY())
				break;
			i++;
		}
		
		movingObjects.add(i, obj);
	}
	
	/**
	 *	Returns the lowest y-coordinate of the peloton
	 */
	public int getBottomY() {
		return y + height;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	/**
	 * @param g2D
	 * Draws each MovingObject in the database onto the screen.
	 */
	public void draw(Graphics2D g2D) {
		for (int i = 0; i < movingObjects.size(); i++) {
			MovingObject obj = movingObjects.get(i);
			obj.draw(g2D);
		}
	}
	
	/**
	 * @param xChange translation in the x-coordinate
	 * @param yChange translation in the y-coordinate
	 * Translates the peloton and all the MovingObjects in the peloton appropriately.
	 */
	public void translate(int xChange, int yChange) {
		x += xChange;
		y += yChange;
		for (int i = 0; i < movingObjects.size(); i++) {
			MovingObject obj = movingObjects.get(i);
			obj.translate(xChange, yChange);
		}
	}
	
	private int x;
	private int y;
	private int height;
	private ArrayList<MovingObject> movingObjects;
}
