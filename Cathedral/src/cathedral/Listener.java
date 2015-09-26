package cathedral;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {

    private int[] keys = {37, 38, 39, 40};
    private char[] characters = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
        'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private boolean enabled;
    private static Screen screen;
    private boolean debugOutput = false;

    public Listener() {
        screen = Screen.getInstance();
        enabled = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (enabled) {
            char keyChar = e.getKeyChar();
            if (e.getKeyCode() == 37) { // up
                ;
            }
            if (debugOutput) {
                System.out.print(keyChar);
            }
        } else {
            for (int i = 0; i < alphabet.length; i++) {
                if (e.getKeyChar() == alphabet[i]) {
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void enable() {
        enabled = true;
    }

    public void debug() {
        debugOutput = true;
    }

}
