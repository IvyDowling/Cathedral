package cathedral;

import asciiPanel.Render;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class VideoLib {

    private static String[] intro1 = new String[]{
        "."
    };

    private static String[] intro2 = new String[]{
        " ."
    };

    private static String[] intro3 = new String[]{
        "  ."
    };

    private static String[] intro4 = new String[]{
        "   ."
    };
    private static String[] intro5 = new String[]{
        "     ",
        "    ."
    };

    private static String[] intro6 = new String[]{
        "       ",
        "       ",
        "      ."
    };

    private static String[] intro7 = new String[]{
        "          ",
        "          ",
        "         ."
    };

    private static String[] intro8 = new String[]{
        "             ",
        "             ",
        "             ",
        "            ."
    };

    public static List<Render[]> getIntroCutscene() {
        List<Render[]> temp = new LinkedList<>();
        temp.add(toRenderArray(intro1, Color.WHITE, Color.BLACK));
        temp.add(toRenderArray(intro2, Color.WHITE, Color.BLACK));
        temp.add(toRenderArray(intro3, Color.WHITE, Color.BLACK));
        temp.add(toRenderArray(intro4, Color.WHITE, Color.BLACK));
        temp.add(toRenderArray(intro5, Color.WHITE, Color.BLACK));
        temp.add(toRenderArray(intro6, Color.WHITE, Color.BLACK));
        temp.add(toRenderArray(intro7, Color.WHITE, Color.BLACK));
        temp.add(toRenderArray(intro8, Color.WHITE, Color.BLACK));

        return temp;
    }

    public static Render[] getEmptyRender() {
        return new Render[]{
            new Render("", 0, 0, Color.BLACK, Color.BLACK)
        };
    }

    private static Render[] toRenderArray(String[] s, Color fg, Color bg) {
        Render[] temp = new Render[s.length];
        for (int i = 0; i < s.length; i++) {
            temp[i] = new Render(s[i], 0, i, fg, bg);
        }
        return temp;
    }
}
