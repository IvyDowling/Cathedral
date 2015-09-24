package rlengine;

import asciiPanel.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class Screen extends JPanel {

    private static final int HEIGHT = 35, WIDTH = 101, SCALE = 32;
    private static final Dimension DIMENSION = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
    private static final int STARTING_HEIGHT = 75, STARTING_WIDTH = 80;
    private static Screen screen = new Screen();
    private static AsciiPanel asciiPanel;

    public Screen() {

        this.setSize(DIMENSION);
        this.setBounds(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        this.add(asciiPanel = new AsciiPanel(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
    }

    public void render() {
    }

    public static Screen getInstance() {
        if (screen == null) {
            Screen screen = new Screen();
        }
        return screen;
    }
}
