package asciiPanel;

import java.awt.Color;

public class Render {

    String data[];
    int x, y;
    Color foreground, background;

    public Render(String[] s, int x, int y, Color fore, Color bk) {
        data = s;
        this.x = x;
        this.y = y;
        foreground = fore;
        background = bk;
    }
    
    public Render(String s,int x, int y, Color fore, Color bk){
        data = new String[1];
        data[0] = s;
        this.x = x;
        this.y = y;
        foreground = fore;
        background = bk;
    }
    
}
