package robot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * Simple GUI program to show an animation of robots. Class AnimationViewer is
 * a special kind of GUI component (JPanel), and as such an instance of 
 * AnimationViewer can be added to a JFrame object. A JFrame object is a 
 * window that can be closed, minimized, and maximized. The state of an
 * AnimationViewer object comprises a list of Robots and a Timer object. An
 * AnimationViewer instance subscribes to events that are published by a Timer.
 * In response to receiving an event from the Timer, the AnimationViewer iterates 
 * through a list of Robots requesting that each Robot paints and moves itself.
 * 
 * @author Craig Sutherland
 * 
 */
@SuppressWarnings("serial")
public class AnimationViewer extends JPanel implements ActionListener {
	// Frequency in milliseconds for the Timer to generate events.
	private static final int DELAY = 20;

	// Collection of Robots to animate.
	private List<Robot> _robots;

	private Timer _timer = new Timer(DELAY, this);

	/**
	 * Creates an AnimationViewer instance with a list of Robot objects and 
	 * starts the animation.
	 */
	public AnimationViewer() {
		_robots = new ArrayList<Robot>();
	
		// Populate the list of Robots.
		_robots.add(new WheeledRobot(0, 0, 5, 8));
		//_robots.add(new FlyingRobot(100,100,5,8,40,50));
		//_robots.add(new TrackedRobot(12,120,5,8,80,80));
        //_robots.add(new TrackedRobot(12,120,5,8,30,30));
        //_robots.add(new DynamicWheeledRobot());
        //_robots.add(new DynamicWheeledRobot(250, 250, 5, 8, 30, 30, Color.red));
		DynamicWheeledRobot test= new DynamicWheeledRobot(250, 250, 5, 8, 30, 30, Color.red);
		//_robots.add(new DynamicCarRobot(10,10,5,8,100,100));
        CarrierRobot ben= new CarrierRobot(0,0,5,8,200,200);
        CarrierRobot jake= new CarrierRobot(5,8,5,8,100,100);
        CarrierRobot willy= new CarrierRobot(0,0,5,8,50,50);
		_robots.add(ben);
		ben.add(test);
        ben.add(jake);
        ben.add(new FlyingRobot(10,100,5,8));
		jake.add(new FlyingRobot());
		jake.add(willy);
		willy.add(new DynamicCarRobot());
		ben.setInnerText("BABOONS");

		Robot sam= new DynamicWheeledRobot(0,0,3,2,Color.BLACK);
	

		//ben.add(new DynamicCarRobot(10,10,5,8,50,50));

       /*TrackedRobot bob= new TrackedRobot(0,0,3,2,200,200);
       _robots.add(bob);
       bob.setInnerText("BABOONS"); */


		
		// Start the animation.
		_timer.start();
	}

	/**
	 * Called by the Swing framework whenever this AnimationViewer object
	 * should be repainted. This can happen, for example, after an explicit 
	 * repaint() call or after the window that contains this AnimationViewer 
	 * object has been opened, exposed or moved.
	 * 
	 */
	public void paintComponent(Graphics g) {
		// Call inherited implementation to handle background painting.
		super.paintComponent(g);
		
		// Calculate bounds of animation screen area.
		int width = getSize().width;
		int height = getSize().height;
		
		// Create a GraphicsPainter that Robot objects will use for drawing.
		// The GraphicsPainter delegates painting to a basic Graphics object.
		Painter painter = new GraphicsPainter(g);

		// Progress the animation.
		for(Robot robot : _robots) {
			robot.paint(painter);
			robot.move(width, height);
		}
	}

	/**
	 * Notifies this AnimationViewer object of an ActionEvent. ActionEvents are
	 * received by the Timer.
	 */
	public void actionPerformed(ActionEvent e) {
		// Request that the AnimationViewer repaints itself. The call to 
		// repaint() will cause the AnimationViewer's paintComponent() method 
		// to be called.
		repaint();
	}
	
	
	/**
	 * Main program method to create an AnimationViewer object and display this
	 * within a JFrame window.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Animation viewer");
				frame.add(new AnimationViewer());
		
				// Set window properties.
				frame.setSize(500, 500);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}
