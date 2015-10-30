package cathedral;

import asciiPanel.Render;
import java.awt.Color;

public class EndPage extends Page {

    @Override
    public Color getBackgroundColor() {
        return Color.DARK_GRAY;
    }

    @Override
    public Color getForegroundColor() {
        return Color.BLUE;
    }

    @Override
    public Render[] getDefaultRender() {
        String[] cathedral = new String[]{
            "   _____           _______   _    _   ______   _____    _____              _      ",
            "  / ____|    /\\   |__   __| | |  | | |  ____| |  __ \\  |  __ \\      /\\    | |     ",
            " | |        /  \\     | |    | |__| | | |__    | |  | | | |__) |    /  \\   | |     ",
            " | |       / /\\ \\    | |    |  __  | |  __|   | |  | | |  _  /    / /\\ \\  | |     ",
            " | |____  / ____ \\   | |    | |  | | | |____  | |__| | | | \\ \\   / ____ \\ | |____ ",
            "  \\_____|/_/    \\_\\  |_|    |_|  |_| |______| |_____/  |_|  \\_\\ /_/    \\_\\|______|"
        };
        Color fg = this.getForegroundColor();
        Color bg = this.getBackgroundColor();

        return new Render[]{
            new Render("(r) restart", 30, 10, Color.YELLOW, Color.MAGENTA),
            new Render(cathedral[0], 0, 0, fg, bg),
            new Render(cathedral[1], 0, 1, fg, bg),
            new Render(cathedral[2], 0, 2, fg, bg),
            new Render(cathedral[3], 0, 3, fg, bg),
            new Render(cathedral[4], 0, 4, fg, bg),
            new Render(cathedral[5], 0, 5, fg, bg)
        };
    }

    @Override
    public Render[] getUpdateRender() {
        return new Render[]{};
    }

    @Override
    public Command pageAction(int key) {
        switch (key) {
            case 82://r                
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.setPage(new OpeningPage());
                    }
                };
        }
        return null;
    }

    @Override
    public void playViewer() {
        //no cutscene yet
    }

}
