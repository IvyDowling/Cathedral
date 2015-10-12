package cathedral;

import asciiPanel.Render;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class MainPage extends Page {

    private Viewer viewer;
    private Render[] currentRender;

    public MainPage() {
        viewer = new Viewer();
        viewer.addRenderArray(VideoLib.getIntroCutscene());
    }

    @Override
    public Render[] getDefaultRender() {
        List<Render> temp = new LinkedList<>();
        char bar = '\u00B3';
        for (int i = 0; i < 35; i++) {
            temp.add(new Render(bar, 0, i, Color.WHITE, Color.BLACK));
            temp.add(new Render(bar, 41, i, Color.WHITE, Color.BLACK));
            temp.add(new Render(bar, 80, i, Color.WHITE, Color.BLACK));

        }
        Render[] ret = new Render[temp.size()];
        for(int i = 0; i < temp.size(); i++){
            ret[i] = temp.get(i);
        }
        return ret;
    }

    @Override
    public Render[] getUpdateRender() {
        if (viewer.isPlaying()) {
            //viewer render
            return currentRender = viewer.getCurrentRender();
        } else {
            return getDefaultRender();
        }
    }

    @Override
    public Command pageAction(int key
    ) {
        switch (key) {
            case 32:
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.printToConsole("play!");
                        c.getCurrentPage().playViewer();
                    }
                };
        }
        return new Command() {
            @Override
            public void exe(Controller c) {
                c.printToConsole("does nothing");
            }
        };
    }

    private Render[] toRenderArray(String[] s, int x, int y, Color fg, Color bg) {
        Render[] temp = new Render[s.length];
        for (int i = 0; i < s.length; i++) {
            temp[i] = new Render(s[i], x, i + y, fg, bg);
        }
        return temp;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public void playViewer() {
        viewer.play();
    }
}
