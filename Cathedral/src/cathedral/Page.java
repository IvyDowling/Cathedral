package cathedral;

import asciiPanel.Render;

public class Page {

    private PageInputAction pageInputAction;

    public Page(PageInputAction pa) {
        pageInputAction = pa;
    }

    public Render[] getDefaultRender() {
        return pageInputAction.getDefaultRender();
    }

    public Render getDefatultRenderIndex(int i) {
        return pageInputAction.getDefaultRender()[i];
    }

    public void setOnKeyPress(PageInputAction p) {
        pageInputAction = p;
    }

    public PageInputAction getPageInputAction() {
        return pageInputAction;
    }
}
