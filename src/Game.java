package PACKAGE_NAME;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game {

	public static void main(String[] args) {
		
		Avatar avatar = new Avatar();
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		frame.setSize(1000, 750);
		frame.setTitle("LAST STAND");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
		
	}
	
}
