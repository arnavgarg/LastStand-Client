package game.state;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameState extends State {

    Image playerBody;
    Image playerHand;
    private int bodyX = 350;
    private int bodyY = 250;
    private int rightX = 430;
    private int rightY = 245;
    private int leftX = 345;
    private int leftY = 245;
	
    public GameState() {
		
	try {
		playerBody = ImageIO.read(getClass().getResource("/res/redcircle.png"));
		playerHand = ImageIO.read(getClass().getResource("/res/redcircle.png"));
	} catch (IOException e) {
		e.printStackTrace();
	}
		
		playerBody = playerBody.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		playerHand = playerHand.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		
	}
    
    public void render(Graphics g){
		
        // TODO - render game map
        g.drawImage(playerBody, bodyX, bodyY, null);
        g.drawImage(playerHand, rightX, rightY, null);
        g.drawImage(playerHand, leftX, leftY, null);
        
    }

    public void tick(){
        //TODO - update the information of mouse in the class
    }

    public void processMouseEvent(MouseEvent me) {

    }

    public void processKeyEvent(KeyEvent ke) {

    }
	
}
