import java.awt.*;

/**
 * @author jrk based on cay horstmann
 * 
 * This interface contains all actions moving objects should be able to do, draw themselves
 * onto the screen, translate themselves by a certain x and y, and return their lowest
 * y-coordinates
 */
public interface MovingObject {
	void draw(Graphics2D g2D);
	void translate(int xChange, int yChange);
	int getBottomY();
}
