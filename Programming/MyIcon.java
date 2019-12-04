import java.awt.*;
import javax.swing.*;

/**
 * @author jrk based on cay horstmann
 * 
 * This class wraps around a MovingObject to draw it to the screen.
 */
public class MyIcon implements Icon {
	/**
	 * @param obj Moving object to draw to the screen
	 * @param w width of the icon
	 * @param h height of the icon
	 */
	public MyIcon(MovingObject obj, int w, int h) {
		this.obj = obj;
		this.w = w;
		this.h = h;
	}
	
	public int getIconWidth() {
		return w;
	}
	
	public int getIconHeight() {
		return h;
	}
	
	/**
	 * Paints the icon onto the screen.
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2D = (Graphics2D) g;
		obj.draw(g2D);
	}
	
	private int w;
	private int h;
	private MovingObject obj;
}
