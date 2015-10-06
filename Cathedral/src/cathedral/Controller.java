package cathedral;

import combatsystem.*;

public class Controller {

    private CombatSystem cs;
    private static Controller controller = new Controller();

    private Entity self;
    private Entity currentEnemy;

    public Controller() {
        cs = new CombatSystem();
    }

    public void makeNewAction(BodyPart bp, ActionExecution ae) {
        cs.addAction(new Action(self, currentEnemy, bp, ae));
    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
}
