package cathedral;

import combatsystem.*;

public class Controller {

    private CombatSystem cs;
    private static Controller controller = new Controller();
    private static Screen screen;

    private Page currentPage;

    private Entity self;
    private Entity currentEnemy;

    public Controller() {
        screen = Screen.getInstance();
        cs = new CombatSystem();
        currentPage = new Page(new PageLib.OpeningPage());
        screen.setPage(currentPage);
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
        System.out.println("took input " + keyCode);
        currentPage.getPageInputAction().pageAction(keyCode);
    }

    public static Controller getInstance() {
        if (controller == null) {
            Controller controller = new Controller();
        }
        return controller;
    }
}
