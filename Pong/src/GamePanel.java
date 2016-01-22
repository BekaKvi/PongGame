import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;
import java.util.Random;


/**
 * Animates a simple graphical game.
 *
 * Uses a Swing Timer to advance the animation; keeps track of, and renders, all GameObjects. Handles all relevant game events.
 *
 * @author BekaKvi
 *
 */



public class GamePanel extends JPanel implements ActionListener, KeyListener {

   	static final int FRAME_RATE = 200; // animation proceeds at 30 frames per second
	
   	protected boolean startGamePlayer = false; // Checks if an user chose to play against a player
	protected boolean startGameComputer = false; // Checks if an user chose to play against computer

	final int paddleWidth = 25; // sets the paddle width
	final int paddleHeight = 200; // sets the paddle height
	final int delay = 1000;
	
   	Timer t;	// animation timer
	Random randomGenerator = new Random(); // Generates random number

	GameObject ball; // bare-bones animation: just a simple object that slides across the panel

	Player player;
	Player player2;
	Player computer;

	
	/**
	 * Creates game Timer, creates initial GameObjects
	 * Makes panel to be focusable and adds key listener
	 */


	GamePanel () {
        t = new Timer(1000/FRAME_RATE, this);
		ball = new GameObject(450, 450, 30, 30, 3, 3);
		player = new Player(0, 0, 20, 200);
		player2 = new Player(880, 0, 20, 200);
		computer = new Player(ball, 880, 0, 20, 200);	 		
		
		this.addKeyListener(this);
		this.setFocusable(true);
		
	}

	/**
	 * How to render one "frame" of the animation
	 */

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(startGamePlayer != true && startGameComputer != true){
			setBackground(new Color(218, 71, 92));

			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
			g.setColor(Color.white);
			g.drawString("PONG", Main.pWidth / 2 - 150, Main.pHeight / 4);

			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
			g.setColor(Color.white);
			g.drawString("Play Against Computer - press 1", Main.pWidth / 2 - 250, Main.pHeight / 2);
			g.drawString("Play Against Player       - press 2", Main.pWidth / 2 - 250, Main.pHeight / 2 + 50);
		}
		
		if (startGamePlayer == true) {
			setBackground(new Color(218, 71, 92));

			g.setColor(Color.white);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
			g.drawString(ball.playerOneScore.toString(), Main.pWidth / 2 - 120, 100);
			g.drawString(":", Main.pWidth / 2 - 10, 100);
			g.drawString(ball.playerTwoScore.toString(), Main.pWidth / 2 + 50, 100);

			g.setColor(Color.white);
			g.fillOval(ball.topLeft.x, ball.topLeft.y, ball.getWidth(), ball.getHeight());
			g.fillRect(player.topLeft.x, player.topLeft.y, player.getPlayerWidth(), player.getPlayerHeight());
			g.fillRect(player2.topLeft.x, player2.topLeft.y, player2.getPlayerWidth(), player2.getPlayerHeight());
			
		} else if (startGameComputer == true) {
			setBackground(new Color(218, 71, 92));

			g.setColor(Color.white);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
			g.drawString(ball.playerOneScore.toString(), Main.pWidth / 2 - 120, 100);
			g.drawString(":", Main.pWidth / 2 - 10, 100);
			g.drawString(ball.playerTwoScore.toString(), Main.pWidth / 2 + 50, 100);

			g.setColor(Color.white);
			g.fillOval(ball.topLeft.x, ball.topLeft.y, ball.getWidth(), ball.getHeight());
			g.fillRect(player.topLeft.x, player.topLeft.y, player.getPlayerWidth(), player.getPlayerHeight());
			g.fillRect(computer.topLeft.x, computer.topLeft.y, computer.getPlayerWidth(), computer.getPlayerHeight());

		}
		
	}

	/**
	 * Responds to 'ticks' from the timer.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {

		// if this is an event from the Timer, call the method that advances the animation
		if (e.getSource() == t) {
			tick();
		}
	}
	
	/**
	 * Responds to the specific letters, when pushed on the keyboard
	 */
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_2)
			startGamePlayer = true;
		else if (e.getKeyCode() == KeyEvent.VK_1)
			startGameComputer = true;

		if (e.getKeyCode() == KeyEvent.VK_W)
			player.setPlayerSpeed(-5);
		else if (e.getKeyCode() == KeyEvent.VK_S)
			player.setPlayerSpeed(5);

		if (e.getKeyCode() == KeyEvent.VK_UP)
			player2.setPlayerSpeed(-5);
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			player2.setPlayerSpeed(5);
	}
	
	/**
	 * Responds to the specific letters, when released on the keyboard
	 */
	
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S)
			player.setPlayerSpeed(0);
		if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN)
			player2.setPlayerSpeed(0);
	}

	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Depending on the boolean variables, decides rather to start player
	 * against computer panel or player against player panel; Then adds all the
	 * steps and at the end invokes paintComponent
	 */
	
	private void tick() {

		//!! the game logic from paintComponent should go in this method.

		if (startGameComputer == true) {
			
			ball.step();
			ball.collidePlayerOne(player);
			ball.collidePlayerTwo(computer);
			player.updatePlayer();
			computer.updateComputer();
			
		} else if (startGamePlayer == true) {
			
			ball.step();
			ball.collidePlayerOne(player);
			ball.collidePlayerTwo(player2);
			player.updatePlayer();
			player2.updatePlayer();
		}

		if (ball.playerOneScore > 8 || ball.playerTwoScore > 8) {

			ball.playerOneScore = 0;
			ball.playerTwoScore = 0;
			ball.delay(delay);

			ball = new GameObject(250, 250, 30, 30, 3, -3);
		}
		
		repaint(); 

	}

	/**
	 * Start the Timer: this will cause events to be fired, and thus the animation to begin
	 */

	void go() {
		t.start();
	}

	public GameObject getBall() {
		return ball;
	}
	
}