import java.awt.Graphics2D;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;


public class Mario extends GameObject {

	public Mario(){
		super(3, 5, 5, new Location(10,10), 0, 0, false);
	}
	
	public static Icon getGif() throws MalformedURLException {
		URL url = GameObject.class.getResource(GameObject.MARIO_GIF);
		return new ImageIcon(url);
	}

	@Override
	public void move() {
		super.move();
	}

	@Override
	public void checkCollision() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkOffScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		
	}
}
