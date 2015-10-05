package cathedral;

import asciiPanel.Render;
import java.awt.Color;

public class PageLib {

    public Page getFakePage() {
        Render[] output = new Render[]{
            new Render(":::", 0, 0, Color.RED, Color.PINK),
            new Render("===", 2, 2, Color.YELLOW, Color.GREEN),
            new Render("|||", 1, 1, Color.BLUE, Color.CYAN)
        };
        return new Page(output, new PageInputAction() {
            @Override
            public void pageAction(int keyCode) {
                if (keyCode == 34) {
                    
                }
            }
        }
        );
    }
}
