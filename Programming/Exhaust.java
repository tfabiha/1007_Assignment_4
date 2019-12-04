import java.awt.*;
import java.awt.geom.*;

/**
 * @author Tabassum Fabiha | tf2478
 * 
 * This class draws a single exhaust puff at a fixed position initially given upon creation.
 * The exhaust drawn is a circle of a random size that is proportional to the size of the 
 * car which created it. Randomness of the size is done each time the exhaust is being drawn
 * for aesthetic purposes. It is also able to say when the puff has faded away so that the
 * Car object may stop drawing it onto the screen.
 */
public class Exhaust {
	/**
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param unit
	 * Assigns variables the appropriate values
	 */
	public Exhaust(int x, int y, int unit) {
		this.x = x;
		this.y = y;
		this.unit = unit;
		cycle = 0;
	}
	
	/**
	 * @param g2D
	 * Draws the exhaust onto the screen
	 */
	public void draw(Graphics2D g2D) {
		cycle += 1;
		
		int xUpLeft = x;
		int yUpLeft = y;
		int diameter = (int) (unit * Math.random() * (MAXDIAMETER - MINDIAMETER) + MINDIAMETER );
		
		Ellipse2D.Double head = new Ellipse2D.Double(xUpLeft, yUpLeft, diameter, diameter);
		
		g2D.setPaint(new Color(90, 90, 90, 50));
		g2D.fill(head);
	}
	
	/**
	 * @return if the max number of cycles have been passed
	 */
	public boolean isFaded() {
		return cycle >= MAXCYCLES;
	}
	
	private int x;
	private int y;
	private int unit;
	private int cycle;
	
	private final double MINDIAMETER = 0.2;
	private final double MAXDIAMETER = 0.7;
	private final int MAXCYCLES = 30;
}
