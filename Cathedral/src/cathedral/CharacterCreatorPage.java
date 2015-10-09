package cathedral;

import asciiPanel.Render;
import java.awt.Color;

public class CharacterCreatorPage extends Page {

    private final int POINT_POOL_MAX = 12;
    private final double HEIGHT_MIN = 5;
    private final int STR_MIN = 4;
    private final int DEX_MIN = 4;
    private final int LIST_SIZE = 8;
    private final int alignX = 6;

    private int pointPool = POINT_POOL_MAX;
    private int locationRef = 0;//+1 for move right, minus 1 move left
    private double height = HEIGHT_MIN;
    private int str, dex = STR_MIN;
    private int cursorLoc = 8;

    @Override
    public Render[] getDefaultRender() {
        Color bg = Color.BLACK;
        Color fg = Color.WHITE;
        return new Render[]{
            new Render(" Stats:", 4, 6, fg, bg),
            new Render("Height -- 5 ft", alignX, 8, fg, bg),
            new Render("Str -- 4", alignX, 9, fg, bg),
            new Render("Dex -- 4", alignX, 10, fg, bg),
            new Render(" Weapons:", 4, 12, fg, bg),
            new Render("Dagger -- Length: 1 Weight: 2 Sharpness + 5", alignX, 14, fg, bg),
            new Render("Longsword -- Length: 4 Weight: 6 Sharpness + 3", alignX, 15, fg, bg),
            new Render("Axe -- Length: 3 Weight: 8 Sharpness + 2", alignX, 16, fg, bg),
            new Render("Hammer -- Length: 2 Weight: 10 Sharpness + 0", alignX, 17, fg, bg),
            new Render("Battle Axe (2H) -- Length: 4 Weight: 15 Sharpness + 2", alignX, 18, fg, bg)
        };
    }

    @Override
    public Command pageAction(int key) {
        switch (key) {
            case 37://left
                moveLeft();
                return new Command() {
                    @Override
                    public void exe(Controller c) {
//                        c.printToConsole("");
                        c.printToConsole("-1");
                    }
                };
            case 38://up
                moveUp();
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.addRender(new Render(">", alignX - 1, cursorLoc, Color.ORANGE, Color.BLACK));
                    }
                };
            case 39://right
                moveRight();
                return new Command() {
                    @Override
                    public void exe(Controller c) {
//                        c.printToConsole("");
                        c.printToConsole("+1");
                    }
                };
            case 40://down
                moveDown();
                return new Command() {
                    @Override
                    public void exe(Controller c) {
//                        c.printToConsole("");
                    }
                };

            default:
                return new Command() {
                    @Override
                    public void exe(Controller c) {
//                        c.printToConsole("");
                    }

                };
        }
    }

    private void moveRight() {
        if (pointPool != 0) {
            pointPool = pointPool - 1;
            switch (locationRef) {
                case 0:
                    height = height + 0.5;
                    break;
                case 1:
                    str = str + 2;
                    break;
                case 2:
                    dex = dex + 2;
                    break;
                case 3:
                    //add weapon
                    break;

            }
        }
    }

    private void moveLeft() {
        if (pointPool != 12) {
            pointPool = pointPool - 1;
            switch (locationRef) {
                case 0:
                    height = height + 0.5;
                    break;
                case 1:
                    str = str + 2;
                    break;
                case 2:
                    dex = dex + 2;
                    break;
                case 3:
                    //add weapon
                    break;

            }
        }
    }

    private boolean moveUp() {
        if (locationRef != 0) { //top
            locationRef = locationRef + 1;
            cursorLoc = cursorLoc - 1;
            return true;
        }
        return false;
    }

    private boolean moveDown() {
        if (locationRef < LIST_SIZE) { //bottom
            locationRef = locationRef - 1;
            cursorLoc = cursorLoc + 1;
            return true;
        }
        return false;
    }

}
