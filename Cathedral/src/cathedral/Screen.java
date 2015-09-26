package cathedral;

import asciiPanel.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Screen extends JPanel {

    private static final int HEIGHT = 35, WIDTH = 101, SCALE = 32;
    private static final Dimension DIMENSION = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
    private static final int STARTING_HEIGHT = 75, STARTING_WIDTH = 80;
    private static Screen screen = new Screen();
    private static AsciiPanel asciiPanel;
    private List<Render> renderList = new LinkedList<Render>();

    private Image testImg;

    public Screen() {

        this.setSize(DIMENSION);
        this.setBounds(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        this.add(asciiPanel = new AsciiPanel(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);

        try {
            testImg = ImageIO.read(new File("res/bug1.png"));
        } catch (Exception ignore) {
            System.out.println("BOO");
        }
    }

    public void render() {
        asciiPanel.repaint();
        for (int i = 0; i < renderList.size(); i++) {
            asciiPanel.write(renderList.remove(i));
        }
        this.updateGameUI();
    }

    public static Screen getInstance() {
        if (screen == null) {
            Screen screen = new Screen();
        }
        return screen;
    }

    public void addRender(Render r) {
        renderList.add(r);
    }

    private void updateGameUI() {
        int i = 0;
        int h = asciiPanel.getHeightInCharacters();
        int w = asciiPanel.getWidthInCharacters();
        while (i < h) {
            asciiPanel.writeCenter("|", i);
            asciiPanel.write("|", w - 2, i);
            i++;
        }
    }

    public void gameIntro() {
        asciiPanel.write("The Cathedral in the center square,", 0, 0);
        asciiPanel.write("The Cathedral in the center square,", 0, 1);
        asciiPanel.write("The Cathedral in the center square,", 0, 2);
        asciiPanel.write("The Cathedral in the center square,", 0, 3);
    }
}
