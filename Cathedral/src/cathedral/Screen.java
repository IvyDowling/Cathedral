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
    private static Screen screen = new Screen();
    private static AsciiPanel asciiPanel;
    private List<Render> renderList;
    private List<Animation> animationList;
    private List<Render> defaultRenderList;

    public Screen() {
        this.setSize(DIMENSION);
        this.setBounds(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        this.add(asciiPanel = new AsciiPanel(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);

        renderList = new LinkedList<>();
        animationList = new LinkedList<>();
        defaultRenderList = new LinkedList<>();
    }

    public void render() {
        //write default renders
        this.updateGameUI();
        //write added renders
        for (Render r : renderList) {
            asciiPanel.write(r);
        }
        if(!animationList.isEmpty()){
            clearAnimation();
        }
        for (Animation a : animationList) {
            asciiPanel.withEachTile(a.x, a.y, a.width, 1, a.transform);
        }
        renderList.clear();
//        animationList.clear(); // we don't clear unless specifically told to for animation
        asciiPanel.repaint();
    }

    public void clearAnimation() {
        asciiPanel.withEachTile(0, 0, asciiPanel.getWidthInCharacters(),
                asciiPanel.getHeightInCharacters(), new TileTransformer() {
                    @Override
                    public void transformTile(int x, int y, AsciiCharacterData data) {
                        data.backgroundColor = asciiPanel.getDefaultBackgroundColor();
                        data.foregroundColor = asciiPanel.getDefaultForegroundColor();
                    }
                });
    }

    public void addRender(Render r) {
        renderList.add(r);
    }

    public void addRender(Render[] rnd) {
        renderList.addAll(Arrays.asList(rnd));
    }

    public void addAnimation(Range[] r, TileTransformer transformer) {
        for (int i = 0; i < r.length; i++) {
            Range range = r[i];
            animationList.add(new Animation(range.x, range.y, range.width, transformer));
        }
    }

    public void clear(int x, int y, int w, int h) {
        asciiPanel.clear(' ', x, y, w, h);
    }

    private void updateGameUI() {
        for (Render r : defaultRenderList) {
            asciiPanel.write(r);
        }
    }

    public void setPage(Page p) {
        asciiPanel.clear();
        defaultRenderList.clear();
        asciiPanel.setDefaultBackgroundColor(p.getBackgroundColor());
        asciiPanel.setDefaultForegroundColor(p.getForegroundColor());
        defaultRenderList.addAll(Arrays.asList(p.getDefaultRender()));
    }

    public int getHeightInCharacters() {
        return asciiPanel.getHeightInCharacters();
    }

    public int getWidthInCharacters() {
        return asciiPanel.getWidthInCharacters();
    }

    public static Screen getInstance() {
        if (screen == null) {
            Screen screen = new Screen();
        }
        return screen;
    }

    private class Animation {

        public TileTransformer transform;
        public int x, y, width;

        public Animation(int x, int y, int w, TileTransformer t) {
            this.x = x;
            this.y = y;
            width = w;
            transform = t;
        }
    }
}
