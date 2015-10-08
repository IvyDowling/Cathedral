package cathedral;

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

    }

    public void makeNewAction(BodyPart bp, ActionExecution ae) {
        cs.addAction(new Action(self, currentEnemy, bp, ae));
    }

    public void setPage(Page p) {
        System.out.println("new page");
        if (p != null) {
            screen.setPage(p);
            currentPage = p;
        }
    }

    public void takeInput(int keyCode) {
        console.write("input " + keyCode);
        execute(currentPage.pageAction(keyCode));
    }

    private void execute(Command c) {
        c.exe();
    }

    public boolean startNextAction() {
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
