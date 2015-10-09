package cathedral;

import combatsystem.*;
import asciipanel.*;
import java.awt.Color;

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
//    
//    public void highlightArea(Color c, int x, int y, int endX, int endY){
////        screen.addAnimation(new TileTransformer());
//    }
    
    public void addRender(Render r){
        screen.addRender(r);
    }

    public void makeNewAction(BodyPart bp, ActionExecution ae) {
        cs.addAction(new Action(self, currentEnemy, bp, ae));
    }

    public void setPage(Page p) {
        if (p != null) {
            screen.setPage(p);
            currentPage = p;
        }
    }

    public void takeInput(int keyCode) {
        //command pattern at work
        execute(currentPage.pageAction(keyCode));
    }

    private void execute(Command c) {
        c.exe(getInstance());
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
