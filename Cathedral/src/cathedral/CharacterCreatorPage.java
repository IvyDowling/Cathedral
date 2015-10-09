package cathedral;

import asciiPanel.Render;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class CharacterCreatorPage extends Page {

    private final int POINT_POOL_MAX = 12;
    private final double HEIGHT_MIN = 5;
    private final int STR_MIN = 4;
    private final int DEX_MIN = 4;
    private final int LIST_SIZE = 7;//start at 0
    private final int alignX = 10;
    private final int rightAlign = 70;

    private int pointPool = POINT_POOL_MAX;
    private double height;
    private int str, dex;
    private int locationRef = 0;//+1 for move down, minus 1 move up
    private int[] weaponCount = new int[5];

    private ListItem[] items;

    public CharacterCreatorPage() {
        this.items = new ListItem[8];
        height = HEIGHT_MIN;
        str = STR_MIN;
        dex = DEX_MIN;
        items[0] = new ListItem(1, "Height");
        items[1] = new ListItem(1, "STR");
        items[2] = new ListItem(1, "DEX");
        items[3] = new ListItem(1, "Dagger");
        items[4] = new ListItem(4, "Longsword");
        items[5] = new ListItem(3, "Axe");
        items[6] = new ListItem(3, "Hammer");
        items[7] = new ListItem(5, "BattleAxe");
    }

    @Override
    public Render[] getDefaultRender() {
        Color bg = Color.BLACK;
        Color fg = Color.WHITE;
        return new Render[]{
            new Render("Length Weight Sharpness Cost", alignX + 15, 7, bg, fg),
            new Render(" Stats:", 1, 8, fg, bg),
            new Render("Height", alignX, 8, fg, bg),
            new Render(height + " ft", rightAlign, 8, Color.YELLOW, bg),// right
            new Render("Str", alignX, 9, fg, bg),
            new Render(str + " (lbs)", rightAlign, 9, Color.YELLOW, bg),// right
            new Render("Dex", alignX, 10, fg, bg),
            new Render("" + dex, rightAlign, 10, Color.YELLOW, bg),// right
            new Render(" Wep:", 1, 11, fg, bg),
            new Render("Dagger          -1-------2------ + 5 ----1", alignX, 11, fg, bg),
            new Render("Longsword       -4-------6------ + 3 ----4", alignX, 12, fg, bg),
            new Render("Axe             -3-------8------ + 2 ----3", alignX, 13, fg, bg),
            new Render("Hammer          -2------10------ + 0 ----3", alignX, 14, fg, bg),
            new Render("Battle Axe (2H) -4------15------ + 2 ----5", alignX, 15, fg, bg),
            new Render(">", alignX - 1, 8 + locationRef, Color.ORANGE, Color.BLACK)
        };
    }

    @Override
    public Command pageAction(int key) {
        switch (key) {
            case 37://left
                if (moveLeft()) {
                    return new Command() {
                        @Override
                        public void exe(Controller c) {
                            c.addRender(new Render(" Points: " + pointPool + " ", 65, 2, Color.YELLOW, Color.BLUE));
                        }
                    };
                }
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                    }
                };
            case 38://up
                if (moveUp()) {
                    return new Command() {
                        @Override
                        public void exe(Controller c) {
                            c.addRender(new Render(">", alignX - 1, 8 + locationRef, Color.ORANGE, Color.BLACK));
                        }
                    };
                }
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                    }
                };
            case 39://right
                if (moveRight()) {
                    return new Command() {
                        @Override
                        public void exe(Controller c) {
                            c.addRender(new Render(" Points: " + pointPool + " ", 65, 2, Color.YELLOW, Color.BLUE));
                        }
                    };
                }
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                    }
                };
            case 40://down
                if (moveDown()) {
                    return new Command() {
                        @Override
                        public void exe(Controller c) {
                            c.addRender(new Render(">", alignX - 1, 8 + locationRef, Color.ORANGE, Color.BLACK));
                        }
                    };
                }
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                    }
                };
            case 13: //enter
                if (pointPool == 0) {
                    return new Command() {
                        @Override
                        public void exe(Controller c) {
                            c.setPage(new MainPage());
                        }
                    };
                }
            default:
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                    }
                };
        }
    }

    private boolean moveRight() {
        if (pointPool >= items[locationRef].cost) {
            items[locationRef].addPoint();
            pointPool = pointPool - items[locationRef].cost;
            return true;
        }
        return false;
    }

    private boolean moveLeft() {
        if (pointPool != 12) {
            if (items[locationRef].pointsAdded > 0) {
                items[locationRef].removePoint();
                pointPool = pointPool + items[locationRef].cost;
                return true;
            }
        }
        return false;
    }

    private boolean moveUp() {
        if (locationRef != 0) { //top
            locationRef = locationRef - 1;
            return true;
        }
        return false;
    }

    private boolean moveDown() {
        if (locationRef < LIST_SIZE) { //bottom
            locationRef = locationRef + 1;
            return true;
        }
        return false;
    }

    public int[] getWeapons() {
        return weaponCount;
    }

    private class ListItem {

        int cost;
        String name;
        int pointsAdded;

        public ListItem(int c, String s) {
            cost = c;
            name = s;
        }

        public void addPoint() {
            pointsAdded = pointsAdded + cost;
        }

        public void removePoint() {
            pointsAdded = pointsAdded + cost;
        }
    }
}
