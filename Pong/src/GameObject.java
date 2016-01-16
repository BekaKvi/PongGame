import java.awt.Point;
import java.util.Random;

public class GameObject {

	protected Point topLeft; 		// initial coordinates of top left corner of object
	protected Point bottomRight; 	// initial coordinates of bottom left corner of object

	protected int xSpeed;			// initial x-axis speed
	protected int ySpeed;			// initial y-axis speed
	
	
	protected boolean playingGame = true; 	// checks if the game is in progress
	protected boolean gameoOvar = false;	// checks if the game is over
	
	
	protected Integer playerOneScore = 0;	// calculates player1 score
	protected Integer playerTwoScore = 0;	// calculates player2 score

	Random randomGenerator = new Random();  //Generates random number
	
	static int appTopBarSize = 20;			// Size of a top panel of the application

	
	
	/**
	 * 
	 * Implements collision methods
	 * 
	 * @return The two objects have collided.
	 * 
	 */

	public void collidePlayerOne(Player player){ // checks if player1 is collided with the ball 
	
		if((this.topLeft.x  + xSpeed) < player.getBottomRight().getX()){	
			if((this.topLeft.y + ySpeed) > player.getBottomRight().getY() || (this.bottomRight.y + ySpeed) < player.getTopLeft().getY()){
				playerTwoScore++;			// increases the score
				delay(200);					// delays for 2 milliseconds 
				
				this.topLeft.x = 450;		// after player scores, ball is initialized in the middle of the screen
				this.topLeft.y = 450;
				this.bottomRight.x = 480; 
				this.bottomRight.y = 480;
			}
			else 
				xSpeed *= -1; 				// otherwise changes the direction of the ball 
		}
 
	}
	
	public void collidePlayerTwo(Player player){ // checks if player2 is collided with the ball
		if((this.bottomRight.x + xSpeed) > player.getTopLeft().getX()){
			if((this.topLeft.y + ySpeed) > player.getBottomRight().getY() || (this.bottomRight.y + ySpeed) < player.getTopLeft().getY()){
				playerOneScore++;
				delay(200);					
				
				this.topLeft.x = 450;
				this.topLeft.y = 450;
				this.bottomRight.x = 480; 
				this.bottomRight.y = 480;
			}
			else 
				xSpeed *= -1; 
			}	
	}
	
	
	/**
	 * Initialize object with top and bottom corners and initial x- and y-speed
	 * 
	 */

	public GameObject(Point topLeft, Point bottomRight, int xSpeed, int ySpeed) {
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;

		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	/**
	 * Initialize object with top corner, height, width, and initial x- and
	 * y-speed
	 * 
	 */

	public GameObject(int initX, int initY, int height, int width, int xSpeed, int ySpeed) {
		this(new Point(initX, initY), new Point(initX + width, initY + height), xSpeed, ySpeed);
	}

	/**
	 * Initialize object with top corner at (0,0), with given height and width,
	 * and initial speed 5 in the x-dimension
	 * 
	 */

	public GameObject(int height, int width) {
		this(0, 0, height, width, 5, 0);
	}
	
	/**
	 * Set the GameObject's speed in the x dimension
	 * 
	 * @param xSpeed
	 *            The desired x-speed.
	 */

	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	/**
	 * Set the GameObject's speed in the x dimension
	 * 
	 * @param ySpeed
	 *            The desired y-speed.
	 */
	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	/**
	 * Increase the GameObject's speed in the x dimension
	 * 
	 * @param x
	 *            The amount of increase.
	 */

	public void accelX(int x) {
		xSpeed += x;
	}

	/**
	 * Increase the GameObject's speed in the y dimension
	 * 
	 * @param y
	 *            The amount of increase.
	 */

	public void accelY(int y) {
		ySpeed += y;
	}

	/**
	 *
	 * @return A Point representing the top-left corner of the GameObject's
	 *         bounding box
	 */
	public Point getTopLeft() {
		return topLeft;
	}

	/**
	 *
	 * @return A Point representing the bottom-right corner of the GameObject's
	 *         bounding box
	 */

	public Point getBottomRight() {
		return bottomRight;
	}

	/**
	 * 
	 * @return The height (in pixels) of the GameObject
	 */
	public int getHeight() {
		return bottomRight.y - topLeft.y;
	}

	/**
	 * 
	 * @return The width (in pixels) of the GameObject
	 */

	public int getWidth() {
		return bottomRight.x - topLeft.x;

	}
	
	/**
	 * Changes the location of the object for the next "animation frame"
	 * Implements the bounce method.
	 */

	public void step() {
		topLeft.x += xSpeed;
		bottomRight.x += xSpeed;

		topLeft.y += ySpeed;
		bottomRight.y += ySpeed;
		
		int y = bottomRight.y + ySpeed; 
		
			if(y < appTopBarSize){
				ySpeed = 3;
			} else if(y > Main.pWidth - appTopBarSize){
				ySpeed = -3;
			}

	}
	
	/**
	 * implements delay function, that is used when the ball is crossing left y or right y axis
	 * Implements the bounce method.
	 */
	public void delay(int i){
		try {
            Thread.sleep(i);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
		
	}

}