package cathedral;

import asciiPanel.Render;
import java.awt.Color;

public class Page {

    private Render[] defaultRender;
    private PageInputAction pageInputAction;

    public Page(Render[] c, PageInputAction pa) {
        if (!(c == null)) {
            defaultRender = c;
        }
        pageInputAction = pa;
    }

    public Page(String[] s, PageInputAction pa) {
        defaultRender = new Render[s.length];
        for (int i = 0; i < s.length; i++) {
            defaultRender[i] = new Render(s[i], 0, 0, Color.BLACK, Color.WHITE);
        }
        pageInputAction = pa;
    }

    public Render[] getDefaultRender() {
        return defaultRender;
    }

    public Render getDefatultRenderIndex(int i) {
        return defaultRender[i];
    }

    public void onKeyPress(int keyCode) {
        //OVERRIDE ME PLEASE
        pageInputAction = new PageInputAction(){
            @Override
            public void pageAction(int keyCode) {
                
            }
        };
    }

    public void setOnKeyPress(PageInputAction p) {
        pageInputAction = p;
    }

    public PageInputAction getPageInputAction() {
        return pageInputAction;
    }
}
