import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;

public class Window{
	public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    JPanel panel = new JPanel();
    
    
    
    public static void main(String[] args) {
    	
    	JFrame f= new JFrame("Test");  
    	JTextField t = new JTextField("Enter your name");  
        t.setBounds(100,105, 200,30);  
        f.add(t);  
        f.setSize(400,300);  
        f.setResizable(false);
        f.setLayout(null);  
        f.setVisible(true);  	
	}
}
