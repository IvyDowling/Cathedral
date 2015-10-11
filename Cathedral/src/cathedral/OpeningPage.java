package cathedral;

import asciiPanel.Render;
import java.awt.Color;
import java.io.File;
import java.net.URL;

public class OpeningPage extends Page {

    private boolean top = true; //cursor starts on the continue

    @Override
    public Command pageAction(int key) {
        switch (key) {
            case 38://up                
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        if (!top) {
                            c.clear(29, 10, 1, 2);
                            top = true;
                        }
                    }
                };
            case 40://down
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        if (top) {
                            c.clear(29, 10, 1, 2);
                            top = false;
                        }
                    }
                };
            case 32: // spaceBar
                if (top) { //CONTINUE -> LOAD SAVE
                    return new Command() {
                        @Override
                        public void exe(Controller c) {
                            c.loadSave();
                        }
                    };
                }
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.setPage(new CharacterCreatorPage());
                    }
                };
            default:
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                    }
                };
        }
    }

    @Override
    public Render[] getUpdateRender() {
        if (top) {
            return new Render[]{
                new Render(">", 29, 10, Color.ORANGE, Color.BLACK)
            };
        }
        return new Render[]{
            new Render(">", 29, 11, Color.ORANGE, Color.BLACK)
        };
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
        Color fg = Color.BLUE;
        Color bg = Color.LIGHT_GRAY;
        Color continueColor = Color.GRAY;

        if (isThereAFile()) {
            continueColor = Color.YELLOW;
        }

        return new Render[]{
            new Render("(c)ontinue", 30, 10, continueColor, Color.BLACK),
            new Render("(n)ew game", 30, 11, Color.CYAN, Color.GRAY),
            new Render(cathedral[0], 0, 0, fg, bg),
            new Render(cathedral[1], 0, 1, fg, bg),
            new Render(cathedral[2], 0, 2, fg, bg),
            new Render(cathedral[3], 0, 3, fg, bg),
            new Render(cathedral[4], 0, 4, fg, bg),
            new Render(cathedral[5], 0, 5, fg, bg)
        };
    }

    private boolean isThereAFile() {
        try {
            URL path = OpeningPage.class.getResource("sv.dat");
            File f = new File(path.getFile());
            return f.canRead();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public void playViewer() {
        //no cutscene
    }

}
