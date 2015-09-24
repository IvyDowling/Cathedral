/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rlengine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 *
 * @author IV
 */
public class TextArea extends JTextArea {

    private static final int HEIGHT = 240, WIDTH = 360, SCALE = 2;
    //+5 just to tighten up the JFrame & JPanel
    private final Dimension TEXT_AREA = new Dimension(250, (HEIGHT * SCALE ));
    private static TextArea textArea = new TextArea();
    Font font = new Font("Monospaced", Font.PLAIN, 12);

    public TextArea() {
        this.setRows(22);
        this.setColumns(30);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setEditable(false);
        this.setFont(font);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.setHighlighter(null);
    }

    public static TextArea getInstance() {
        if (textArea == null) {
            TextArea textArea = new TextArea();
        }
        return textArea;
    }

}

