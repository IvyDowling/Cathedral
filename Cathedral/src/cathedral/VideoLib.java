package cathedral;

import asciiPanel.Range;
import asciiPanel.Render;
import combatsystem.BodyComponent;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class VideoLib {

    private static final int PANEL_WIDTH = 99;
    private static final int PANEL_CENTER_WIDTH = 50;
    private static final int PANEL_HEIGHT = 34;
    private static final int PANEL_CENTER_HEIGHT = 17;

    private static Range[] getBodyComponent(int i, BodyComponent b) {
        switch (b) {
            case LARM:
                return new Range[]{
                    new Range(10 + PANEL_CENTER_WIDTH, 4, 3),
                    new Range(9 + PANEL_CENTER_WIDTH, 5, 4),
                    new Range(9 + PANEL_CENTER_WIDTH, 6, 4),
                    new Range(8 + PANEL_CENTER_WIDTH, 7, 5),
                    new Range(8 + PANEL_CENTER_WIDTH, 8, 5),
                    new Range(7 + PANEL_CENTER_WIDTH, 9, 6),
                    new Range(7 + PANEL_CENTER_WIDTH, 10, 4),
                    new Range(7 + PANEL_CENTER_WIDTH, 11, 3),
                    new Range(6 + PANEL_CENTER_WIDTH, 12, 4),
                    new Range(6 + PANEL_CENTER_WIDTH, 13, 4)
                };
            case RARM:
                return new Range[]{
                    new Range(22 + PANEL_CENTER_WIDTH, 4, 1),
                    new Range(22 + PANEL_CENTER_WIDTH, 5, 1),
                    new Range(22 + PANEL_CENTER_WIDTH, 6, 1),
                    new Range(22 + PANEL_CENTER_WIDTH, 7, 2),
                    new Range(22 + PANEL_CENTER_WIDTH, 8, 2),
                    new Range(22 + PANEL_CENTER_WIDTH, 9, 3),
                    new Range(22 + PANEL_CENTER_WIDTH, 10, 4),
                    new Range(22 + PANEL_CENTER_WIDTH, 11, 8),
                    new Range(25 + PANEL_CENTER_WIDTH, 12, 5)
                };
            case LLEG:
                return new Range[]{
                    new Range(11 + PANEL_CENTER_WIDTH, 19, 6),
                    new Range(11 + PANEL_CENTER_WIDTH, 20, 5),
                    new Range(10 + PANEL_CENTER_WIDTH, 21, 5),
                    new Range(10 + PANEL_CENTER_WIDTH, 22, 4),
                    new Range(10 + PANEL_CENTER_WIDTH, 23, 4), 
                    new Range(10 + PANEL_CENTER_WIDTH, 24, 4),
                    new Range(10 + PANEL_CENTER_WIDTH, 25, 3),
                    new Range(10 + PANEL_CENTER_WIDTH, 26, 3),
                    new Range(10 + PANEL_CENTER_WIDTH, 27, 4),
                    new Range(10 + PANEL_CENTER_WIDTH, 28, 4)
                };
            case RLEG:
                return new Range[]{
                    new Range(19 + PANEL_CENTER_WIDTH, 19, 5),
                    new Range(19 + PANEL_CENTER_WIDTH, 20, 5),
                    new Range(19 + PANEL_CENTER_WIDTH, 21, 3),
                    new Range(19 + PANEL_CENTER_WIDTH, 22, 3),
                    new Range(19 + PANEL_CENTER_WIDTH, 23, 3),
                    new Range(19 + PANEL_CENTER_WIDTH, 24, 3),
                    new Range(19 + PANEL_CENTER_WIDTH, 25, 4),
                    new Range(19 + PANEL_CENTER_WIDTH, 26, 6)
                };
            case TORSO:
                return new Range[]{
                    new Range(12 + PANEL_CENTER_WIDTH, 4, 10),
                    new Range(12 + PANEL_CENTER_WIDTH, 5, 10),
                    new Range(12 + PANEL_CENTER_WIDTH, 6, 10),
                    new Range(12 + PANEL_CENTER_WIDTH, 7, 10),
                    new Range(12 + PANEL_CENTER_WIDTH, 8, 10),
                    new Range(12 + PANEL_CENTER_WIDTH, 9, 10),
                    new Range(13 + PANEL_CENTER_WIDTH, 10, 9),
                    new Range(13 + PANEL_CENTER_WIDTH, 11, 9),
                    new Range(12 + PANEL_CENTER_WIDTH, 12, 10)
                };
            case HEAD:
                return new Range[]{
                    new Range(15 + PANEL_CENTER_WIDTH, 0, 4),
                    new Range(14 + PANEL_CENTER_WIDTH, 1, 6),
                    new Range(14 + PANEL_CENTER_WIDTH, 2, 6),
                    new Range(13 + PANEL_CENTER_WIDTH, 3, 8)
                };
        }
        return new Range[]{};
    }

    private static String[][] enemySprites = new String[][]{
        new String[]{
            //           1         2         3
            //0123456789012345678901234567890123456
            "                ;Li:                 ",//0
            "               i@MiSr                ",//1
            "               r5r.JG                ",//2
            "              :MGOXL@                ",//3
            "           .,:7GBM:7BLv:             ",//4
            "          ivrqX. .iLrr@L             ",//5
            "          B@1Z@U:.:;7ir@             ",//6
            "         .BS:@@;Y@jL77uL:            ",//7
            "         M@i @BX18r::YY.J            ",//8
            "        rBZ;7L0@@OMU5B@B@.           ",//9
            "        B@Bj  v@@@M8BB@@B@Y          ",//10
            "        UrL   @kEXvr7k8,MB@B0ui      ",//11
            "       .iO7  :@S1GZ0PJB   .PB@2      ",//12
            "       :1.,  1@:iqj.B,L@J            ",//13
            "            .@@:riv.B1,B@U.          ",//14
            "            vB@v:BPJ@@FL@B8          ",//15
            "           7B@MB8@M@BBL.Z@MY         ",//16
            "          vB@kF0OUUB@PLiEEYi         ",//17
            "           ;E@M@UO0@@@Pi             ",//18
            "            :i :@:  @B@i.            ",//19
            "            L@UL:   BBBv.            ",//20
            "           :Brri    BBv              ",//21
            "           L@..     @Br              ",//22
            "           YX,i     LBv              ",//23
            "           PBr,     NBO              ",//24
            "           v@v      XBB,             ",//25
            "           JO:      r5B@Bj           ",//26
            "           U@r,                      ",//27
            "           :YJ.                      " //28
        },
        new String[]{
            "."
        }
    };

    public static Range[] getBodyComponentRange(BodyComponent b) {
        return getBodyComponent(0, b);
    }

    public static Range[] getSceenRange() {
        return new Range[]{
            new Range(0, 0, 99), new Range(0, 1, 99),
            new Range(0, 2, 99), new Range(0, 3, 99),
            new Range(0, 4, 99), new Range(0, 5, 99),
            new Range(0, 6, 99), new Range(0, 7, 99),
            new Range(0, 8, 99), new Range(0, 9, 99),
            new Range(0, 10, 99), new Range(0, 11, 99),
            new Range(0, 12, 99), new Range(0, 13, 99),
            new Range(0, 14, 99), new Range(0, 15, 99),
            new Range(0, 16, 99), new Range(0, 17, 99),
            new Range(0, 18, 99), new Range(0, 19, 99),
            new Range(0, 20, 99), new Range(0, 21, 99),
            new Range(0, 22, 99), new Range(0, 23, 99),
            new Range(0, 24, 99), new Range(0, 25, 99),
            new Range(0, 26, 99), new Range(0, 27, 99),
            new Range(0, 28, 99), new Range(0, 29, 99),
            new Range(0, 30, 99), new Range(0, 31, 99),
            new Range(0, 32, 99), new Range(0, 33, 99),
            new Range(0, 34, 99)
        };
    }

    public static List<Render[]> getIntroCutscene() {
        List<Render[]> temp = new LinkedList<>();
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
