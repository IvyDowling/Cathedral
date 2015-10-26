package cathedral;

import asciiPanel.Render;
import combatsystem.*;
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
    private int locationRef = 0;//+1 for move down, minus 1 move up
    private int cursorLocation = 8 + locationRef;

    private ListItem[] statsList;
    private WeaponItem[] weaponsList;
    private WeaponItem[] selectedWeapons;

    public CharacterCreatorPage() {
        this.statsList = new ListItem[3];
        this.weaponsList = new WeaponItem[5];
        this.selectedWeapons = new WeaponItem[2]; // you only get two weapons
        statsList[0] = new ListItem(1, "Height");
        statsList[1] = new ListItem(1, "STR");
        statsList[2] = new ListItem(1, "DEX");
        weaponsList[0] = new WeaponItem(1, "Dagger", 1, 2, 5);
        weaponsList[1] = new WeaponItem(4, "Longsword", 4, 6, 3);
        weaponsList[2] = new WeaponItem(3, "Axe", 3, 8, 2);
        weaponsList[3] = new WeaponItem(3, "Hammer", 2, 10, 0);
        weaponsList[4] = new WeaponItem(5, "BattleAxe", 4, 14, 2);
    }

    @Override
    public Render[] getDefaultRender() {
        Color bg = Color.BLACK;
        Color fg = Color.WHITE;
        return new Render[]{
            new Render("Length Weight Sharpness Cost", alignX + 15, 7, bg, fg),
            new Render(" Stats:", 1, 8, fg, bg),
            new Render("Height", alignX, 8, fg, bg),
            new Render("Str", alignX, 9, fg, bg),
            new Render("Dex", alignX, 10, fg, bg),
            new Render(" Wep:", 1, 11, fg, bg),
            new Render("Dagger          -1-------2------ + 5 ----1", alignX, 11, fg, bg),
            new Render("Longsword       -4-------6------ + 3 ----4", alignX, 12, fg, bg),
            new Render("Axe             -3-------8------ + 2 ----3", alignX, 13, fg, bg),
            new Render("Hammer          -2------10------ + 0 ----3", alignX, 14, fg, bg),
            new Render("Battle Axe      -4------14------ + 2 ----5", alignX, 15, fg, bg),};
    }

    @Override
    public Render[] getUpdateRender() {
        Color bg = Color.BLACK;
        Color fg = Color.YELLOW;
        Render[] temp = new Render[10];
        temp[0] = new Render(statsList[0].output() + " ft", rightAlign, 8, fg, bg);
        temp[1] = new Render(statsList[1].output() + " (lbs)", rightAlign, 9, fg, bg);
        temp[2] = new Render(statsList[2].output(), rightAlign, 10, fg, bg);
        //weps
        if (!this.canWieldWarning()) {
            fg = Color.RED;
        }
        temp[3] = new Render(weaponsList[0].output(), rightAlign, 11, fg, bg);
        temp[4] = new Render(weaponsList[1].output(), rightAlign, 12, fg, bg);
        temp[5] = new Render(weaponsList[2].output(), rightAlign, 13, fg, bg);
        temp[6] = new Render(weaponsList[3].output(), rightAlign, 14, fg, bg);
        temp[7] = new Render(weaponsList[4].output(), rightAlign, 15, fg, bg);
        temp[8] = new Render(">", alignX - 1, 8 + locationRef, Color.ORANGE, Color.BLACK);
        temp[9] = new Render(" Points: " + pointPool + " ", 65, 2, Color.YELLOW, Color.BLUE);

        return temp;
    }

    @Override
    public Command pageAction(int key) {
        switch (key) {
            case 37://left
                moveLeft();
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
                            c.clearRender(alignX - 1, 8, 1, 8);// cleanup old cursors
                        }
                    };
                }
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                    }
                };
            case 39://right
                moveRight();
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
                            c.clearRender(alignX - 1, 8, 1, 8); // cleanup old cursors
                        }
                    };
                }
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                    }
                };
            case 32: // spaceBar
                if (pointPool == 0) {
                    return new Command() {
                        @Override
                        public void exe(Controller c) {
                            c.makeEntity(exportEntity());
                            c.setPage(new MainPage());
                        }
                    };
                } else {
                    return new Command() {
                        @Override
                        public void exe(Controller c) {
                        }
                    };
                }
            case 27: // esc
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.setPage(new OpeningPage());
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

    private boolean moveRight() {
        if (locationRef > (statsList.length - 1)) { // WEAPONS
            int index = locationRef - statsList.length;
            if (pointPool >= weaponsList[index].cost) {
                //private getFreeWepSlot()
                if (getFreeWeaponSlots()) {
                    weaponsList[index].addPoint();
                    pointPool = pointPool - weaponsList[index].cost;
                    selectWeapon(weaponsList[index]);
                }
                return true;
            }
        } else { // STATS
            if (pointPool >= statsList[locationRef].cost) {
                statsList[locationRef].addPoint();
                pointPool = pointPool - statsList[locationRef].cost;
                return true;
            }
        }
        return false;
    }

    private boolean moveLeft() {
        if (pointPool != 12) {
            if (locationRef > (statsList.length - 1)) { // WEAPONS
                int index = locationRef - statsList.length;
                if (weaponsList[index].pointsAdded > 0) {
                    weaponsList[index].removePoint();
                    pointPool = pointPool + weaponsList[index].cost;
                    deselectWeapon(weaponsList[index]);
                    return true;
                }
            } else { //STATS
                if (statsList[locationRef].pointsAdded > 0) {
                    statsList[locationRef].removePoint();
                    pointPool = pointPool + statsList[locationRef].cost;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean getFreeWeaponSlots() {
        if (selectedWeapons[0] != null && selectedWeapons[1] != null) {
            return false;
        }
        return true;
    }

    private void selectWeapon(WeaponItem w) {
        if (selectedWeapons[0] == null) {
            selectedWeapons[0] = w;
        } else if (selectedWeapons[1] == null) {
            selectedWeapons[1] = w;
        }
    }

    private void deselectWeapon(WeaponItem w) {
        if (selectedWeapons[0] == w) {
            selectedWeapons[0] = null;
        } else if (selectedWeapons[1] == w) {
            selectedWeapons[1] = null;
        }
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

    private boolean canWieldWarning() {
        int wepWeight = 0;
        for (WeaponItem w : weaponsList) {
            // collect the ammount of weapons chosen
            wepWeight = wepWeight + ((w.pointsAdded / w.cost) * w.weight);
        }
        //is the weight greater than the str stat
        return wepWeight <= (getStr());

    }

    private double getHeight() {
        return HEIGHT_MIN + ((double) statsList[0].pointsAdded / 2);
    }

    private int getWeight() {
        int WEIGHT_MIN = 120;
        return WEIGHT_MIN + (10 * getStr()) + (10 * statsList[0].pointsAdded);
    }

    private int getStr() {
        return STR_MIN + statsList[1].pointsAdded * 2;
    }

    private int getDex() {
        return DEX_MIN + statsList[2].pointsAdded * 2;
    }

    public Entity exportEntity() {
        List<Weapon> wep = new LinkedList<>();
        for (WeaponItem w : selectedWeapons) {
            if (w != null) {
                if (w.sharpness > 0) {
                    wep.add(new Weapon(w.name, w.length, w.weight, w.sharpness));
                } else {
                    wep.add(new Weapon(w.name, w.length, w.weight));
                }
            }
        }
        return new Entity(getHeight(), getWeight(), getStr(), getDex(), wep);
    }

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public Color getForegroundColor() {
        return Color.WHITE;
    }

    @Override
    public void playViewer() {
        //no cutscene
//        viewer.play();
    }

    //
    //LIST ITEM CLASS
    //
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
            pointsAdded = pointsAdded - cost;
        }

        public String output() {
            switch (this.name.toLowerCase()) {
                case "height":
                    return "" + ((pointsAdded * 0.5) + HEIGHT_MIN);
                case "str":
                    return "" + ((pointsAdded * 2) + STR_MIN);
                case "dex":
                    return "" + ((pointsAdded * 2) + DEX_MIN);
                case "dagger":
                    if (pointsAdded > 0) {
                        return "" + pointsAdded / cost;
                    }
                    return "-";
                case "longsword":
                    if (pointsAdded > 0) {
                        return "" + pointsAdded / cost;
                    }
                    return "-";
                case "axe":
                    if (pointsAdded > 0) {
                        return "" + pointsAdded / cost;
                    }
                    return "-";
                case "hammer":
                    if (pointsAdded > 0) {
                        return "" + pointsAdded / cost;
                    }
                    return "-";
                case "battleaxe":
                    if (pointsAdded > 0) {
                        return "" + pointsAdded / cost;
                    }
                    return "-";
            }
            return "";
        }
    }

    private class WeaponItem extends ListItem {

        int length;
        int weight;
        int sharpness;

        public WeaponItem(int c, String n, int l, int w, int shp) {
            super(c, n);
            length = l;
            weight = w;
            sharpness = shp;
        }
    }
}
