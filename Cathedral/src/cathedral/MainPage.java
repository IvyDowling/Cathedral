package cathedral;

import asciiPanel.Render;
import java.awt.Color;

public class MainPage extends Page {

    @Override
    public Render[] getDefaultRender() {
        return new Render[]{
            new Render("peas", 0, 0, Color.MAGENTA, Color.CYAN)
        };
    }

    @Override
    public Command pageAction(int key) {
        return new Command() {
            @Override
            public void exe(Controller c) {
                c.printToConsole("does nothing");
            }
        };
    }

}
