package cathedral;

import asciiPanel.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

public class Screen extends JPanel {

    private static final int HEIGHT = 35, WIDTH = 101, SCALE = 32;
    private static final Dimension DIMENSION = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
    private static final int STARTING_HEIGHT = 75, STARTING_WIDTH = 80;
    private static Screen screen = new Screen();
    private static AsciiPanel asciiPanel;
    private List<Render> renderList;
    private List<Render> defaultRenderList;

    public Screen() {

        this.setSize(DIMENSION);
        this.setBounds(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        this.add(asciiPanel = new AsciiPanel(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);

        renderList = new LinkedList<Render>();
        defaultRenderList = new LinkedList<Render>();
    }

    public void render() {
        asciiPanel.repaint();
        for (int i = 0; i < renderList.size(); i++) {
            asciiPanel.write(renderList.remove(i));
        }
        this.updateGameUI();
    }

    public void addRender(Render r) {
        renderList.add(r);
    }

    private void updateGameUI() {
        for (Render r : defaultRenderList) {
            asciiPanel.write(r);
        }
    }

    public void setPage(Page p) {
        defaultRenderList.clear();
        defaultRenderList.addAll(Arrays.asList(p.getDefaultRender()));
        updateGameUI();
    }

    public static Screen getInstance() {
        if (screen == null) {
            Screen screen = new Screen();
        }
        return screen;
    }
}
