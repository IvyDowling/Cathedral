package cathedral;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Range;
import asciiPanel.Render;
import asciiPanel.TileTransformer;
import combatsystem.*;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Controller {

    private CombatSystem cs;
    private static Controller controller = new Controller();
    private static Screen screen;
    private static TextArea console;

    private static Page currentPage;
    private Render[] lastUpdate;

    private Entity self;
    private Entity currentEnemy;
    private Save save;
    private int enemyIndex = 0;

    public Controller() {
        screen = Screen.getInstance();
        console = TextArea.getInstance();
        cs = new CombatSystem();
        console.write("Welcome to the Cathedral");
        currentEnemy = EnemyLib.getEnemy(enemyIndex);
    }

    public Entity getCurrentEnemy() {
        return currentEnemy;
    }

    public void makeSelf(double h, int w, int s, int d, List<Weapon> wep) {
        self = new Entity(h, w, s, d, wep);
    }

    public void makeSelf(Entity e) {
        self = e;
        System.out.println(e.toString());
    }

    public void addRender(Render r) {
        screen.addRender(r);
    }

    public void addAnimation(Range[] r, TileTransformer t) {
        screen.addAnimation(r, t);
    }

    public void updateDynamicPageContent() {
        if (lastUpdate != currentPage.getUpdateRender()) {
            lastUpdate = currentPage.getUpdateRender();
            for (Render r : lastUpdate) {
                screen.addRender(r);
            }
        }
    }

    public void clearAnimation() {
        screen.clearAnimation();
    }

    public void clearRender() {
        screen.clear(0, 0, screen.getWidthInCharacters(), screen.getHeightInCharacters());
    }

    public void clearRender(int x, int y, int w, int h) {
        screen.clear(x, y, w, h);
    }

    public void attackPlayer(BodyComponent bc) {
        cs.addAction(new Action(self, currentEnemy, self.getBodyPart(bc)));
    }

    public void attackEnemy(BodyComponent bc) {
        cs.addAction(new Action(self, currentEnemy, currentEnemy.getBodyPart(bc)));
    }

    public void setPage(Page p) {
        if (p != null) {
            currentPage = p;
            screen.setPage(p);
            console.setText("");
            this.saveGame();
        }
    }

    public void takeInput(int keyCode) {
        //command pattern at work
        execute(currentPage.pageAction(keyCode));
    }

    private void execute(Command c) {
        c.exe(getInstance());
        updateDynamicPageContent();
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public boolean fireNextAction() {
        if (self != null) {
            if (currentEnemy.getBody().isImpaired()) {
                console.write("You defeated the enemy!");
                enemyIndex++;
                if (enemyIndex > EnemyLib.getLength()) {
                    setPage(new EndPage());
                    console.write("You have beaten the Cathedral!");
                } else {
                    currentEnemy = EnemyLib.getEnemy(enemyIndex);
                }
            }
            if (self.getBody().isImpaired()) {
                setPage(new EndPage());
                console.write("You have been overcome by your wounds. You are forced to submit.");
            }
            if (!cs.isEmpty()) {
                this.getEnemyAction();
                Action a = cs.getNextAction();
                cs.popAction();
                console.write(turnDesc(a));
                a = cs.getNextAction();
                cs.popAction();
                console.write(turnDesc(a));
                cs.clear();
                return true;
            }
        }
        return false;
    }

    private String turnDesc(Action a) {
        String out = "";
        if (a.getSpark().equals(self)) {
            if (a.getBodyPart().isIsImpaired()) {
                out += "Their " + a.getBodyPart().getComponent().toString() + " is impaired!";
            } else {
                out += "You deal " + self.getDamage() + " damage to their " + a.getBodyPart().getComponent().toString();
            }
        } else {
            out += "\nYou took " + currentEnemy.getDamage() + " damage to your " + a.getBodyPart();
        }
        return out;
    }

    private void getEnemyAction() {
        this.attackPlayer(BodyComponent.HEAD);
    }

    public void printToConsole(String s) {
        console.write(s);
    }

    public boolean saveGame() {
        ObjectOutputStream oos = null;
        try {
            File f = new File("sv.dat");
            f.delete();
            oos = new ObjectOutputStream(new FileOutputStream("sv.dat"));
            oos.writeUnshared(save);
            oos.close();
        } catch (IOException io) {
            System.out.println("Failed to save File");
            return false;
        } finally {
            try {
                oos.close();
            } catch (Exception ignore) {
            }
        }
        return true;
    }

    public boolean loadSave() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sv.dat"))) {
            save = (Save) ois.readObject();
            ois.close();
        } catch (IOException ex) {
            System.out.println("couldn't load file: io\n" + ex);
            return false;
        } catch (ClassNotFoundException ex) {
            System.out.println("couldn't load file class\n" + ex);
            return false;
        }
        //assign save data
        if (save != null) {
            self = save.getEntity();
            gotoEnemy(save.enemyNumber);
        } else {
            System.out.println("SAVE DATA NULL");
        }
        return true;
    }

    private void gotoEnemy(int enemyNum) {
        //non-impl code
        currentEnemy = self;
    }

    public static Controller getInstance() {
        if (controller == null) {
            Controller controller = new Controller();
        }
        return controller;
    }
}
