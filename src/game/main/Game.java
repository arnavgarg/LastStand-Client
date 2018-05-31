package game.main;

import game.map.Location;
import game.state.MenuState;
import game.state.State;
import game.state.StateLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends Canvas{

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private boolean running;
    private static StateLoader loader;

    public static String ADDRESS = "";//"http://54.201.138.236:8080/";
    private BufferedReader f;
    private JOptionPane fileError, URLerror;
    private String line;
    private URL url;
    
    public Game(){
    	try {
			f = new BufferedReader(new FileReader("config.txt"));
			line = f.readLine();
		} catch (FileNotFoundException e) {
			fileError = new JOptionPane();
			fileError.showMessageDialog(null, "CONFIG FILE NOT FOUND");
			line = "http:localhost:8080";
		} catch (IOException e) {
			URLerror.showMessageDialog(null, "INVALID ADDRESS FOUND IN CONIFG");
			line = "http:localhost:8080";
		} catch (NullPointerException e){
			URLerror.showMessageDialog(null, "INVALID ADDRESS FOUND IN CONIFG");
			line = "http:localhost:8080";
		}  	
    	try {
			url = new URL(line);
		} catch (MalformedURLException e) {
			URLerror = new JOptionPane();
			URLerror.showMessageDialog(null, "INVALID ADDRESS FOUND IN CONIFG");
			line = "http:localhost:8080";
		}
    	
    	if(line.length() != -1){
    		ADDRESS = line;
    	}
    	
    }

    private void start(){
        loader = new StateLoader(new MenuState());
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {
                GameInfo.getInstance().setMouseP(new Location(e.getX(), e.getY()));
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loader.processMouseEvent(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                loader.processKeyEventPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                loader.processKeyEventRelease(e);
            }
        });
        running = true;
        run();
    }

    public static void loadState(State s){
        loader.load(s);
    }

    private void run(){
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int ticks = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                loader.tick();
                ticks++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("TICKS: " + ticks + ", FPS: " + frames);
                ticks = 0;
                frames = 0;
            }
        }
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.clearRect(0, 0, WIDTH, HEIGHT);
        loader.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args){
        System.setProperty("sun.java2d.opengl", "true");
        JFrame frame = new JFrame("Last Stand");
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        game.setBackground(new Color(117, 214, 57));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        game.start();
    }
}
