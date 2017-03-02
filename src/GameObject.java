import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class GameObject {
	
	protected static final int GRAVITY = 10;
	protected double height, width, speed, direction;
	protected int health;
	protected Location location;
	protected boolean shouldRemove;
	protected static final String MARIO_GIF = "/images/Mario.gif";

	public GameObject(int health, double height, double width, Location location, 
			double speed, double direction, boolean shouldRemove){
		this.health = health;
		this.height = height;
		this.width = width;
		this.location = new Location(location);
		this.speed = speed;
		this.direction = direction;
	}
	
//	public void openGif(String s){
//		try{
//			URL url = new URL(s);
//			Icon icon = new ImageIcon(url);
//			JLabel label = new JLabel(icon);
//		}
//		catch (Exception e){
//			System.out.println("Problem opening image");
//		}
//		
//	}
	
//	public void openImage(String s) {
//		try {		
//			URL backgroundImageURL = getClass().getResource(s);
//			if (backgroundImageURL != null) {
//				backgroundImage = ImageIO.read(backgroundImageURL);
//				// img = img.getScaledInstance(img.getWidth(null) , img.getHeight(null), Image.SCALE_DEFAULT);
//			}
//		} catch (IOException e) {
//			System.err.println("Could not open image ( " + BACKGROUND_IMAGE_FILE_NAME + " )");
//			e.printStackTrace();
//		}
//	}
	
	public int health(){
		return health;
	}

	public void move() {
		location.addVector(speed, direction);
	}

//	private boolean willMoveOffscreen() {
//		Location loc = new Location(location);
//		loc.addVector(speed, direction);
//		return !loc.inMap(new Rectangle(map.dimensions()));
//	}

	public abstract void checkCollision();

	public abstract void checkOffScreen();

	public Rectangle getBoundingRect() {
		return new Rectangle((int) (location.getX()-width/2),
				(int) (location.getY()-height/2),
				(int) width, (int) height);
	}

	public abstract void draw(Graphics2D g);

	public void markRemove() {
		shouldRemove = true;
	}

	public boolean shouldRemove() {
		return shouldRemove;
	}
	
	public void checkHealth() {
		if (health <= 0) {
			markRemove();
		}
	}
}
