package cathedral;

import asciiPanel.Render;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Timer;

public class Viewer {

    private final List<Render[]> vid;
    private int index;
    private boolean isPlaying;
    private final int DELAY = 1000; //milliseconds

    public Viewer() {
        vid = new LinkedList<>();
        index = 0;
        isPlaying = false;
    }

    public Viewer(List<Render[]> v) {
        vid = v;
    }

    public Render[] getCurrentRender() {
        if (index == vid.size()) {
            isPlaying = false;
        }
        if (isPlaying) {
            return vid.get(index);
        }
        return null;
    }

    public Viewer addRenderArray(Render[] r) {
        vid.add(r);
        return this;
    }

    public Viewer addRenderArray(List<Render[]> r) {
        vid.addAll(r);
        return this;
    }

    public void play() {
        isPlaying = true;
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                nextFrame();
                System.out.println(index);
            }
        };
        new Timer(DELAY, taskPerformer).start();
    }

    public void stop() {
        isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void nextFrame() {
        if (index < vid.size()) {
            index++;
        } else {
            this.stop();
        }
    }
}
