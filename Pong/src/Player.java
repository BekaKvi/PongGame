import java.awt.Point; 

/**
 * Sets up the Player class.
 * 
 * @author BekaKvi
 *
 */



public class Player {

	protected GameObject comp;
	protected Point topLeft;		// initial coordinates of top left corner of object
	protected Point bottomRight; 	// initial coordinates of bottom left corner of object
	protected int playerSpeed;		// initial player speed
	protected int compSpeed = 0;
	
	/**
	 * Initialize object with top and bottom corners and initial x- and y-speed
	 * 
	 */
	
	public Player(Point topLeft, Point bottomRight ){
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}
	
	/**
	 * Initialize object with top corner, width, and height
	 * 
	 */

	public Player(int x, int y, int width, int height){
		this(new Point(x,y), new Point(x + width, y + height));
	}
	
	/**
	 * Initialize object with GameObject, top corner, width, and height
	 * 
	 */
	
	public Player(GameObject game, int x, int y, int width, int height){
		this(new Point(x,y), new Point(x + width, y + height));
		this.comp = game;
	}
	
	/**
	 * Updates the speed of the x and y axis
	 * 
	 */
	
	public void updatePlayer(){
		topLeft.y += playerSpeed;
		bottomRight.y += playerSpeed;
	}
	
	public void updateComputer(){
		if(comp.getTopLeft().getY() < this.topLeft.y)
			compSpeed *= -3;
		else if(comp.getTopLeft().getY() > this.topLeft.y)
			compSpeed = 3; 
		
		this.topLeft.y  += compSpeed;
		this.bottomRight.y += compSpeed;
	} 
	
	/**
	 *
	 * @return A Point representing the top-left corner of the GameObject's
	 *         bounding box
	 */
	
	public Point getTopLeft(){ 
		return topLeft; 
	}
	
	/**
	 *
	 * @return A Point representing the bottom-right corner of the GameObject's
	 *         bounding box
	 */
	
	public Point getBottomRight(){ 
		return bottomRight; 
	}

	/**
	 * sets the player speed
	 */
	
	public void setPlayerSpeed(int speed){ 
		playerSpeed = speed;
	}
	
	/**
	 * 
	 * @return The width (in pixels) of the player
	 */
	
	public int getPlayerWidth(){
		return bottomRight.x - topLeft.x;
	}
	/**
	 * 
	 * @return The height (in pixels) of the player
	 */
	public int getPlayerHeight(){
		return bottomRight.y - topLeft.y;
	}

	
}