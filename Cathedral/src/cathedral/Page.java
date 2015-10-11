package cathedral;

import asciiPanel.Render;
import java.awt.Color;

public abstract class Page {

    public abstract Color getBackgroundColor();

    public abstract Render[] getDefaultRender();

    public abstract Render[] getUpdateRender();

    public abstract Command pageAction(int key);
    
    public abstract void playViewer();

}
