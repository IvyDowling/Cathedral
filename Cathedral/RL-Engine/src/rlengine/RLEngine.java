package rlengine;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class RLEngine extends Canvas implements Runnable {

    private boolean running;
    private static final int HEIGHT = 19, WIDTH = 29, SCALE = 32;
    private static final String NAME = "RL";

    public int tickCount = 0;

    private JFrame frame;
    private Screen screen;
    private Listener listener;
    private final Dimension DIMENSION = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);

    public RLEngine() {

        frame = new JFrame(NAME);
        screen = Screen.getInstance();
        listener = new Listener();

        //sets personal bug
        frame.setIconImage(new ImageIcon("res/bug.png").getImage());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(screen, null);
        screen.addKeyListener(listener);
        frame.pack();
        frame.setPreferredSize(DIMENSION);
        frame.setResizable(true);
        //A nice visible top left corner point (5,5)
        frame.setLocation(0, 0);
        frame.setVisible(true);
        frame.setSize(DIMENSION);

    }

    public void init() {

    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        running = false;
    }

    public static void main(String[] args) {
        new RLEngine().start();
    }

    public void run() {

        init();

        long lastTime = System.nanoTime();
        final double nsPerTick = 1000000000 / (double) 60;

        int ticks = 0;
        int frames = 0;
        int c = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        while (running) {
            screen = Screen.getInstance();

            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = false;
            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                screen.render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println(frames + " " + ticks);
                frames = 0;
                ticks = 0;
            }


        }

    }

    public void tick() {
        tickCount++;
    }

}
