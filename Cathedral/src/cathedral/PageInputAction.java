package cathedral;

import asciiPanel.Render;

public interface PageInputAction {

    public void pageAction(int keyCode);

    public Render[] getDefaultRender();
    
    public PageInputAction getInstance();
}
