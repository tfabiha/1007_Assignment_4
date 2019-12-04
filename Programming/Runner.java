import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * @author Tabassum Fabiha | tf2478
 * Stolen and modified from jrk and https://docs.oracle.com/javase/tutorial/uiswing/components/slider.html
 * 
 * The video to show testing is found in the same folder as this class and is named
 * "ass4video.mov"
 * 
 * This class creates a GUI to show 7 cars. 
 * 
 * There is a slider to choose the speed of the cars from -6 to 6, and speeds can only be 
 * set to integers in that interval. Initially the speed is set to 1. 
 * 
 * The cars are randomly assigned a y value and their x value is set relative to their y
 * value to show the cars in para-perspective. Each car also releases exhaust in the form
 * of faded gray circles.
 * 
 * A Timer is used to update the GUI every 100 ticks. 
 */
public class Runner {

	public static void main(String[] args) {
		JFrame myFrame = new JFrame();
		JSlider speed = new JSlider(JSlider.HORIZONTAL, -6, 6, 1);
		
		speed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				JSlider source = (JSlider)e.getSource();
			    if (!source.getValueIsAdjusting()) {
			    	xtrans = (int)source.getValue();
			    };
			};
		});
		
		speed.setMajorTickSpacing(2);
		speed.setMinorTickSpacing(1);
		speed.setSnapToTicks(true);
		speed.setPaintTicks(true);
		speed.setPaintLabels(true);
		
		final Peloton track = new Peloton(0, 0, ICON_H);
		
		for (int i = 0; i < 7; i++) {
			int y = (int) (Math.random() * (ICON_H - 50) );
			MovingObject myObj = new Car(track.getX(), track.getY() + y, SIZE);
			track.addMovingObject(myObj);
		}
		
		final MyIcon myIcon = new MyIcon(track, ICON_W, ICON_H);
		final JLabel myLabel = new JLabel(myIcon);
		
		myFrame.add(myLabel);
		myFrame.add(speed);
		
		myFrame.setLayout(new FlowLayout());
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.pack();
		myFrame.setVisible(true);
		
		final int DELAY = 100;
		// Milliseconds between timer ticks
		Timer myTimer = new Timer(DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				track.translate(xtrans, 0);
				myLabel.repaint();
			}
		});
		myTimer.start();

	}
	
	public static final int SIZE = 40;
	public static final int ICON_W = 600;
	public static final int ICON_H = 250;
	private static int xtrans = 1;
}
