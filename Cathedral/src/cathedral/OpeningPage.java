package cathedral;

import asciiPanel.Render;
import java.awt.Color;

public class OpeningPage extends Page {

    private static Controller controller;
    
    public OpeningPage(){
        controller = Controller.getInstance();
    }

    @Override
    public Command pageAction(int key) {
        switch (key) {
            case 13://enter
                controller.printToConsole("Enter");
                return new Command() {
                    @Override
                    public void exe() {
                        System.out.println("well something happened");
                    }
                };
            default:
                return new Command() {
                    @Override
                    public void exe() {
                        System.out.println("default");
                    }
                };
        }
    }

    @Override
    public Render[] getDefaultRender() {
        return new Render[]{
            new Render(" Cathedral ", 5, 5, Color.PINK, Color.CYAN),
            new Render("(c)ontinue", 30, 10, Color.CYAN, Color.BLACK),
            new Render("(n)ew game", 30, 11, Color.CYAN, Color.GRAY)
        };
    }

}
