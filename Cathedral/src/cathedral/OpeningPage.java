package cathedral;

import asciiPanel.Render;
import java.awt.Color;

public class OpeningPage extends Page {
    
    @Override
    public Command pageAction(int key) {
        switch (key) {
            default:
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.setPage(new CharacterCreatorPage());
                    }
                };
        }
    }

    @Override
    public Render[] getDefaultRender() {
        return new Render[]{
            new Render(" Cathedral ", 5, 5, Color.PINK, Color.CYAN),
            new Render("(c)ontinue", 30, 10, Color.CYAN, Color.BLACK),
            new Render("(n)ew game", 30, 11, Color.CYAN, Color.GRAY)
        };
    }

}
