package cathedral;

import asciiPanel.Render;
import java.util.LinkedList;
import java.util.List;

public class Viewer {

    private final List<Render[]> vid;
    private int index;
    private boolean isPlaying;
    private int timer;

    public Viewer() {
        vid = new LinkedList<>();
        index = timer = 0;
        isPlaying = false;
    }

    public Viewer(List<Render[]> v) {
        vid = v;
    }

    public Render[] getCurrentRender() {
        if (index == vid.size()) {
            isPlaying = false;
        }
        timer++;
        if (isPlaying && timer < 1000) {
            System.out.println(index);
            timer = 0;
            return vid.get(index++);
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
    
    public void play(){
        isPlaying = true;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
