package cathedral;

import asciiPanel.Render;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class MainPage extends Page {

    private Viewer viewer;
    private Render[] currentRender;

    public MainPage() {
        viewer = new Viewer();
        viewer.addRenderArray(toRenderArray(img1, Color.CYAN, Color.BLACK))
                .addRenderArray(toRenderArray(img2, Color.CYAN, Color.BLACK))
                .addRenderArray(toRenderArray(img3, Color.CYAN, Color.BLACK))
                .addRenderArray(toRenderArray(img4, Color.CYAN, Color.BLACK))
                .addRenderArray(toRenderArray(img5, Color.CYAN, Color.BLACK))
                .addRenderArray(toRenderArray(img6, Color.CYAN, Color.BLACK))
                .addRenderArray(toRenderArray(img7, Color.CYAN, Color.BLACK))
                .addRenderArray(toRenderArray(img8, Color.BLUE, Color.WHITE));
    }

    @Override
    public Render[] getDefaultRender() {
        return toRenderArray(new String[]{"ooo"}, 30, 30, Color.CYAN, Color.BLACK);
    }

    @Override
    public Render[] getUpdateRender() {
        if (viewer.isPlaying()) {
            //viewer render
            return currentRender = viewer.getCurrentRender();
        } else {
            return toRenderArray(new String[]{"o0o"}, 60, 30, Color.RED, Color.BLACK);
        }
    }

    @Override
    public Command pageAction(int key) {
        switch (key) {
            case 32:
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.printToConsole("play!");
                        c.getCurrentPage().playViewer();
                    }
                };
        }
        return new Command() {
            @Override
            public void exe(Controller c) {
                c.printToConsole("does nothing");
            }
        };
    }

    private String[] boop = new String[]{
        " . . . . . . .:r,. ..,.. ..:,:.,,iiLUZM@OL   . . . . . . ",
        ". . . . . . ii, ... . . . . :.:,:,::rvFX@M@v    . . . . .",
        " . . . . . 7:  ,.. . . . . . . . ,:i:rYXXOM@B:   . . . . ",
        ". . . . . 7.  :.. . . . . . . . . .,i:7ukNOZ@B. . . . . .                     lasdajwd",
        ".. . . . 7. .:,, . . . . . . . ....,.irL1MG8q@G  . . . . ",
        ". . . . :i .:::.. . . . . . . ..,.:,i:7vXM@kFjBY  . . . .",
        " . . . .r. ,ii:: . . . . . . . ,.::iir7S8@MPv7UM   . . . ",
        ". . . ..v .,i:;.. . . . . . . . ..::77PZBGMj7r7qL . . . .",
        " . . . ,; .:.ii: . .HELLOHELLOHELLOivFPBZOZLju,JX  . . . ",
        ". . . ..7 rUU :,.       ..,.,.,,::7JXSPXOXZY@SiLM   . . .",
        " . . . .; iMB.   . :ir:::;:i:::i7qE8Pquui7JuB0rFZ  . . . ",
        ". . . . 7 iM@ .7MM@M@B@XS5jLFPXG@E@B@B@BML::@ZJqZ . . . .",
        " . . . .i::@i7B@M@B@M@M@B@18B@S@uEBBB@B@M@0;kB2BJ  . . . ",
        ". . . . i:rjr8MB@MOGMB@B8UrMXZX87B@B@8@M@BMqqOMB: . . . .",};

    private String[] img1 = new String[]{
        "."
    };

    private String[] img2 = new String[]{
        " ."
    };

    private String[] img3 = new String[]{
        "  ."
    };

    private String[] img4 = new String[]{
        "   ."
    };
    private String[] img5 = new String[]{
        "     ."
    };

    private String[] img6 = new String[]{
        "         ."
    };

    private String[] img7 = new String[]{
        "              ."
    };

    private String[] img8 = new String[]{
        "                         ."
    };

    private Render[] toRenderArray(String[] s, Color fg, Color bg) {
        Render[] temp = new Render[s.length];
        for (int i = 0; i < s.length; i++) {
            temp[i] = new Render(s[i], 0, i, fg, bg);
        }
        return temp;
    }

    private Render[] toRenderArray(String[] s, int x, int y, Color fg, Color bg) {
        Render[] temp = new Render[s.length];
        for (int i = 0; i < s.length; i++) {
            temp[i] = new Render(s[i], x, i + y, fg, bg);
        }
        return temp;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public void playViewer() {
        viewer.play();
    }
}
