import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.util.Iterator;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.util.Set;
import java.util.HashSet;

public class GameMap {

	private List<GameObject> objects;
	private Mario mario;
	private boolean lostGame;

	private static final int NUM_SHAPES = 70;
	private static final String BACKGROUND_IMAGE_FILE_NAME = "/images/MarioBackground.png";
	private static final int NUM_OPPONENTS = 15;

	private Dimension panelDimension;
	private static Image backgroundImage;

	public GameMap(Dimension panelDimension) {
		this.panelDimension = panelDimension;	
		
		objects = new ArrayList<GameObject>();
		openBackgroundImage();
		mario = addMario();
		//loadOpponents();
	}

	public GameObject addGameObject(GameObject go) {
		objects.add(go);
		return go;
	}
	
	
//	public void loadOpponents() {
//		int currentOpponents = 0;
//
//		if (objects.size() > 1) {
//			for (int i = 0; i < objects.size(); i++) {
//				if ((objects.get(i) instanceof Mario) && ( ((Tank) objects.get(i)).isOpponent() )) {
//					currentOpponents++;
//				}
//			}
//		}
//
//		for (int i = currentOpponents; i < NUM_OPPONENTS; i++) {
//			addGameObject(new Tank(new Location((int) (Math.random() * panelDimension.width), (int) (Math.random() * panelDimension.height)), 30, 30, this, true));
//		}
//	}

	private Mario addMario() {
		return (Mario) addGameObject(new Mario());
	}
	
	public void openBackgroundImage() {
		try {		
			URL backgroundImageURL = getClass().getResource(BACKGROUND_IMAGE_FILE_NAME);
			if (backgroundImageURL != null) {
				backgroundImage = ImageIO.read(backgroundImageURL);
				// img = img.getScaledInstance(img.getWidth(null) , img.getHeight(null), Image.SCALE_DEFAULT);
			}
		} catch (IOException e) {
			System.err.println("Could not open image ( " + BACKGROUND_IMAGE_FILE_NAME + " )");
			e.printStackTrace();
		}
	}

	private void checkLostGame() {
		if (mario.health() <= 0) {
			objects.clear();	
			lostGame = true;
		}
	}

	public boolean lostGame() {
		return lostGame;
	}

	public void tick() {
	}

//	public void playerUpdateMotion(Set<Double> directions) {
//		playerTank.updateMotion(directions);
//	}

	public Dimension dimensions() {
		return panelDimension;
	}

//	public void moveOpponents() {
//		for (int i = 0; i < objects.size(); i++) {
//			GameObject go = objects.get(i);
//			if (go instanceof Tank && ((Tank)go).isOpponent()) {
//				Tank t = (Tank) go;
//				t.opponentMove();
//			}
//		}
//	}

	public void removeFromObjects() {
		int i = 0;

		while (i < objects.size()) {
			if (objects.get(i).shouldRemove()) {
				objects.remove(i);
			} else {
				i++;
			}
		}
	}

	public List<GameObject> objects() {
		return objects;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawImage(backgroundImage, 0, 0, panelDimension.width, panelDimension.height, null);
		
		if (lostGame == true) {
			g.drawString("Game Over, click to restart", (panelDimension.width / 2), panelDimension.height / 2);
		}

		for (GameObject go : objects) {
			go.draw(g);
		}

	}

}
