package cathedral;

import asciiPanel.Render;
import java.awt.Color;

public abstract class Page {

    private Render[] defaultRender;

    public Page(Render[] c) {
        if (!(c == null)) {
            defaultRender = c;
        }
    }

    public Page(String[] s) {
        defaultRender = new Render[s.length];
        for (int i = 0; i < s.length; i++) {
            defaultRender[i] = new Render(s[i], 0, 0, Color.BLACK, Color.WHITE);
        }
    }

    public Render[] getDefaultRender() {
        return defaultRender;
    }

    public Render getDefatultRenderIndex(int i) {
        return defaultRender[i];
    }
}
