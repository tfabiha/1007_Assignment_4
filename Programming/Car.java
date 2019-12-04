import java.awt.*;
import java.awt.geom.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Tabassum Fabiha | tf2478
 * Modified after being stolen from jrk
 * 
 * This class draws all the parts of a car, the car itself and the exhaust being created.
 * The exhaust is created in an interval written in EXHAUSTCREATIONRATE and is removed
 * when it can say that it has faded. All the parts of the car are in relation to the upper
 * left corner of the car's body.
 */
public class Car implements MovingObject {
	/**
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param unit
	 * Assigns variables the appropriate values. The unit and x-coordinate is adjusted to
	 * relay to para-perspective. Also instantiates a new ArrayList to store the 
	 * exhaust created by the car.
	 */
	public Car(int x, int y, int unit) {
		this.y = y;
		this.unit = (int) ( (y * 2.0 / 250 + 0.5) * unit );
		this.x = x + (int) (unit - this.unit);
		color = new int[3];
		cycle = 0;
		exhaust = new LinkedList<Exhaust>();
		
		for (int i = 0; i < color.length; i++) {
			color[i] = (int) (Math.random() * 200);
		}
	}
	
	/**
	 *	Returns the lowest y-coordinate of the car
	 */
	public int getBottomY() {
		return (int) (y + (WHEELDIAMETER * BODYHEIGHT) * unit);
	}
	
	/**
	 * @param xChange translation in the x-coordinate
	 * @param yChange translation in the y-coordinate
	 * Translates the car appropriately
	 */
	public void translate(int xChange, int yChange) {
		x += xChange;
		y += yChange;
	}
	
	/**
	 * @param g2D
	 * Draws all aspects of a car, the car itself and the exhaust it creates
	 */
	public void draw(Graphics2D g2D) {
		drawCar(g2D);
		drawExhaust(g2D);
		cycle += 1;
	}
	
	/**
	 * @param g2D
	 * Draws the car itself, the body, wheels, driver, and windshield
	 */
	private void drawCar(Graphics2D g2D) {
		// body
		int bodyXUpLeft = x;
		int bodyYUpLeft = y;
		int bodySizeHorizontal = (int) (unit * BODYLENGTH);
		int bodySizeVertical = (int) (unit * BODYHEIGHT);
		
		Rectangle2D.Double body = new Rectangle2D.Double(bodyXUpLeft, bodyYUpLeft, bodySizeHorizontal, bodySizeVertical);
	
		// left wheel
		int leftWXUpLeft = bodyXUpLeft + bodySizeHorizontal / 5;
		
		// right wheel
		int rightWXUpLeft = bodyXUpLeft + 3 * bodySizeHorizontal / 5;
		
		// common wheel traits
		int WYUpLeft = bodyYUpLeft + bodySizeVertical;
		int WDiameter = (int) (unit * WHEELDIAMETER);
		
		Ellipse2D.Double leftW = new Ellipse2D.Double(leftWXUpLeft, WYUpLeft, WDiameter, WDiameter);
		Ellipse2D.Double rightW = new Ellipse2D.Double(rightWXUpLeft, WYUpLeft, WDiameter, WDiameter);
		
		// driver head
		int headXUpLeft = bodyXUpLeft + bodySizeHorizontal / 2;
		int headYUpLeft = bodyYUpLeft - (int) (unit * DRIVERDIAMETER);
		int headDiameter = (int) (unit * DRIVERDIAMETER);
		
		Ellipse2D.Double head = new Ellipse2D.Double(headXUpLeft, headYUpLeft, headDiameter, headDiameter);
		
		// windshield
		int windshieldXTop = rightWXUpLeft;
		int windshieldXBottom = windshieldXTop + (int) (unit * WINDSHEILDLENGTH * Math.sin(Math.PI / 4));
		int windshieldYBottom = bodyYUpLeft;
		int windshieldYTop = windshieldYBottom - (int) (unit * WINDSHEILDLENGTH * Math.sin(Math.PI / 4));
		
		Line2D.Double windshield = new Line2D.Double(windshieldXTop, windshieldYTop, windshieldXBottom, windshieldYBottom);
		
		// general path
		GeneralPath car = new GeneralPath();
		car.append(body, false);
		car.append(leftW, false);
		car.append(rightW, false);
		car.append(head, false);
		car.append(windshield, false);
		
		// draw
		g2D.setPaint(new Color(color[0], color[1], color[2]));
		g2D.fill(car);
		g2D.draw(windshield);
	}
	
	/**
	 * @param g2D
	 * First updates the exhaust database and then draws all the exhaust onto the screen.
	 */
	private void drawExhaust (Graphics2D g2D) {
		updateExhaust();
		
		Iterator<Exhaust> iter = exhaust.iterator();
		while (iter.hasNext()) {
			Exhaust puff = iter.next();
			puff.draw(g2D);
		}
	}
	
	/**
	 * Updates the exhaust database by creating new Exhaust if needed and removing old 
	 * Exhaust after they've faded.
	 */
	private void updateExhaust() {
		if (cycle % EXHAUSTCREATIONRATE == 0) {
			int bodySizeHorizontal = (int) (unit * BODYLENGTH);
			int bodySizeVertical = (int) (unit * BODYHEIGHT);
			
			int puffX = x - bodySizeHorizontal / 4;
			int puffY = y + bodySizeVertical / 4;
			
			Exhaust puff = new Exhaust(puffX, puffY, unit);
			exhaust.add(puff);
		}
		
		if (exhaust.peek() != null) {
			Exhaust puff = exhaust.peek();
			if (puff.isFaded())
				exhaust.poll();
		}
	}
	
	private int x;
	private int y;
	private int unit;
	private int[] color;
	private int cycle;
	private LinkedList<Exhaust> exhaust;
	
	private final int EXHAUSTCREATIONRATE = 10;
	private final double BODYLENGTH = 2.0;
	private final double BODYHEIGHT = 0.5;
	private final double WHEELDIAMETER = 0.4;
	private final double DRIVERDIAMETER = 0.25;
	private final double WINDSHEILDLENGTH = 0.5;
}
