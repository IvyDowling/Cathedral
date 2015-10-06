package cathedral;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {

    private boolean enabled;
    private static Controller controller;
    private boolean debugOutput = false;

    public Listener() {
        controller = Controller.getInstance();
        enabled = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("input " + e.toString());

        if (enabled) {
            char keyChar = e.getKeyChar();
            System.out.println("input " + keyChar);
            controller.takeInput(e.getKeyCode());
            if (debugOutput) {
                System.out.print(keyChar);
            }
        } else {
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public Listener enable() {
        enabled = true;
        return this;
    }

    public void debug() {
        debugOutput = true;
    }

}
