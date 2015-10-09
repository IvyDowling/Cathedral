package asciiPanel;

import java.awt.Color;

public class Render {

    public String data;
    public int x, y;
    public Color foreground, background;
    
    public Render(String s,int x, int y, Color fore, Color bk){
        data = s;
        this.x = x;
        this.y = y;
        foreground = fore;
        background = bk;
    }
    
}
