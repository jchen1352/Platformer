import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class PlatformLauncher {

	public static void main(String[] args) {
		JFrame gameFrame = new JFrame();
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();      
		PlatformPanel pp = new PlatformPanel(d);
		gameFrame.add(pp);
		
		//Add a JLabel that holds the Mario gif
		JLabel label = null;
		try {
			label = new JLabel(Mario.getGif());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.err.println("Couldn't get Mario gif");
			e.printStackTrace();
		}
		if (label != null) {
			gameFrame.getContentPane().add(label);
		}
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
