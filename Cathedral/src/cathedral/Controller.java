package cathedral;

import asciiPanel.Render;
import combatsystem.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    private CombatSystem cs;
    private static Controller controller = new Controller();
    private static Screen screen;
    private static TextArea console;

    private static Page currentPage;

    private Entity self;
    private Entity currentEnemy;

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
        for (Render r : currentPage.getUpdateRender()) {
            screen.addRender(r);
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
            updateDynamicPageContent();
            console.setText("");
        }
    }

    public void takeInput(int keyCode) {
        //command pattern at work
        execute(currentPage.pageAction(keyCode));
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    private void execute(Command c) {
        c.exe(getInstance());
        updateDynamicPageContent();
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
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sv.dat"))) {
                oos.writeObject(this);
            }
        } catch (IOException ioex) {
            return false;
        }
        return true;
    }

    public boolean loadSave() {
        try {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sv.dat"))) {
                controller = (Controller) ois.readObject();
            }
        } catch (Exception ex) {
            return false;
        }
        return true;

    }

    public static Controller getInstance() {
        if (controller == null) {
            Controller controller = new Controller();
        }
        return controller;
    }
}
