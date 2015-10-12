package cathedral;

import asciiPanel.Render;
import combatsystem.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    public Controller() {
        screen = Screen.getInstance();
        console = TextArea.getInstance();
        cs = new CombatSystem();
        console.write("Welcome to the Cathedral");
    }

    public void addRender(Render r) {
        screen.addRender(r);
    }

    public void updateDynamicPageContent() {
        if (lastUpdate != currentPage.getUpdateRender()) {
            lastUpdate = currentPage.getUpdateRender();
            for (Render r : lastUpdate) {
                screen.addRender(r);
            }
        }
    }

    public void clear(int x, int y, int w, int h) {
        screen.clear(x, y, w, h);
    }

    public void makeNewAction(BodyPart bp, ActionExecution ae) {
        cs.addAction(new Action(self, currentEnemy, bp, ae));
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
        if (!cs.isEmpty()) {
            cs.getNextAction();
            return true;
        }
        return false;
    }

    public void printToConsole(String s) {
        console.write(s);
    }

    public boolean saveGame() {
        try {
            File f = new File("sv.dat");
            f.delete();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sv.dat"));
            oos.writeUnshared(save);

        } catch (IOException io) {
            System.out.println("Failed to save File");
            return false;
        }
        return true;
    }

    public boolean loadSave() {
        try {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sv.dat"))) {
                save = (Save) ois.readObject();
            }
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
