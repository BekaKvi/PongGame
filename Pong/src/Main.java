import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Sets up the GUI and starts the game.
 * 
 * @author BekaKvi
 *
 */
public class Main{

	static final int pWidth = 900; 		// sets the constant value of the frame (x-axis)
	static final int pHeight = 900;  	// sets the constant value of the frame (y-axis) 
	
    public static void main(String[] args) {
    	
        JFrame frame = new JFrame("PONG");	// creates new Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Closes the application
        frame.setLayout(new BorderLayout());	// sets layout
        frame.setResizable(false);	// sets the frame not resizable 

        GamePanel gamePanel = new GamePanel();	// creates new game Panel
        frame.add(gamePanel, BorderLayout.CENTER);	// adds object to the frame

        frame.setSize(pWidth, pHeight);	// sets the size of the panel
        frame.setVisible(true);			// sets panel visible
        
        // the game starts when the gamePanel animation begins
        
        gamePanel.go();
        
        
    }
}