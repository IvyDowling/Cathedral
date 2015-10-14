package cathedral;

import asciiPanel.Render;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class VideoLib {

    private static String[][] enemySprites = new String[][]{
        new String[]{
            "                ;Li:                 ",
            "               i@MiSr                ",
            "               r5r.JG                ",
            "              :MGOXL@                ",
            "           .,:7GBM:7BLv:             ",
            "          ivrqX. .iLrr@L             ",
            "          B@1Z@U:.:;7ir@             ",
            "         .BS:@@;Y@jL77uL:            ",
            "         M@i @BX18r::YY.J            ",
            "        rBZ;7L0@@OMU5B@B@.           ",
            "       ,B@Bj  v@@@M8BB@@B@Y          ",
            "        UrL   @kEXvr7k8,MB@B0ui      ",
            "        iO7  :@S1GZ0PJB   .PB@2      ",
            "         .,  1@:iqj.B,L@J            ",
            "            .@@:riv.B1,B@U.,:        ",
            "            vB@v:BPJ@@FL@B8M7        ",
            "           7B@MB8@M@BBL.Z@MY         ",
            "          vB@kF0OUUB@PLiEEYi         ",
            "           ;E@M@UO0@@@Pi             ",
            "            :i :@:  @B@i.            ",
            "            L@UL:   BBBv.            ",
            "           :Brri    BBv              ",
            "           L@..     @Br              ",
            "           YX,i     LBv              ",
            "           PBr,     NBO              ",
            "           v@v      XBB,             ",
            "           JO:      r5B@Bj           ",
            "           U@r,                      ",
            "           :YJ.                      "
        },
        new String[]{
            "."
        }
    };
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

    public static Render[] getEnemySprite(int i, int x, int y, Color fg, Color bg) {
        return toRenderArray(enemySprites[i], x, y, fg, bg);
    }

    private static Render[] toRenderArray(String[] s, Color fg, Color bg) {
        Render[] temp = new Render[s.length];
        for (int i = 0; i < s.length; i++) {
            temp[i] = new Render(s[i], 0, i, fg, bg);
        }
        return temp;
    }

    private static Render[] toRenderArray(String[] s, int x, int y, Color fg, Color bg) {
        Render[] temp = new Render[s.length];
        for (int i = 0; i < s.length; i++) {
            temp[i] = new Render(s[i], x, y + i, fg, bg);
        }
        return temp;
    }
}
