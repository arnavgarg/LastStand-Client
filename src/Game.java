import State.StateLoader;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel{

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private boolean running;
    private State.StateLoader loader;

    private void start(){
        loader = new State.StateLoader(new State.MainMenu());
        running = true;
        run();
    }

    public void run(){
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
            loader.render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("TICKS: " + ticks + ", FPS: " + frames);
                ticks = 0;
                frames = 0;
            }
        }
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Last Stand");
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        game.start();
    }
}
