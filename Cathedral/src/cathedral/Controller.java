package cathedral;

import asciiPanel.Render;
import combatsystem.*;

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
            screen.setPage(p);
            currentPage = p;
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

    public static Controller getInstance() {
        if (controller == null) {
            Controller controller = new Controller();
        }
        return controller;
    }
}
