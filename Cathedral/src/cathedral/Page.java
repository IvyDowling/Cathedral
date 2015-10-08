package cathedral;

import asciiPanel.Render;

public abstract class Page {
    
    public abstract Render[] getDefaultRender();
    
    public abstract Command pageAction(int key);

}
