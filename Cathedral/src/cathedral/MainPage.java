package cathedral;

import asciiPanel.Render;
import java.awt.Color;

public class MainPage extends Page {

    @Override
    public Render[] getDefaultRender() {
        Render[] temp = new Render[img.length];
        for (int i = 0; i < img.length; i++) {
            temp[i] = new Render(img[i], 0, i, Color.CYAN, Color.BLACK);
        }
        return temp;
    }

    int i = 0;

    @Override
    public Render[] getUpdateRender() {
        Color c = new Color(255, 0, i);
        i++;
        if (i == 255) {
            i = 0;
        }
        return new Render[]{
            new Render("beep", 36, 12, c, Color.RED)
        };
    }

    @Override
    public Command pageAction(int key) {
        return new Command() {
            @Override
            public void exe(Controller c) {
                c.printToConsole("does nothing");
            }
        };
    }

    private String[] img = new String[]{
        " . . . . . . .:r,. ..,.. ..:,:.,,iiLUZM@OL   . . . . . . ",
        ". . . . . . ii, ... . . . . :.:,:,::rvFX@M@v    . . . . .",
        " . . . . . 7:  ,.. . . . . . . . ,:i:rYXXOM@B:   . . . . ",
        ". . . . . 7.  :.. . . . . . . . . .,i:7ukNOZ@B. . . . . .",
        ".. . . . 7. .:,, . . . . . . . ....,.irL1MG8q@G  . . . . ",
        ". . . . :i .:::.. . . . . . . ..,.:,i:7vXM@kFjBY  . . . .",
        " . . . .r. ,ii:: . . . . . . . ,.::iir7S8@MPv7UM   . . . ",
        ". . . ..v .,i:;.. . . . . . . . ..::77PZBGMj7r7qL . . . .",
        " . . . ,; .:.ii: . . . . . ,.    ,,ivFPBZOZLju,JX  . . . ",
        ". . . ..7 rUU :,.       ..,.,.,,::7JXSPXOXZY@SiLM   . . .",
        " . . . .; iMB.   . :ir:::;:i:::i7qE8Pquui7JuB0rFZ  . . . ",
        ". . . . 7 iM@ .7MM@M@B@XS5jLFPXG@E@B@B@BML::@ZJqZ . . . .",
        " . . . .i::@i7B@M@B@M@M@B@18B@S@uEBBB@B@M@0;kB2BJ  . . . ",
        ". . . . i:rjr8MB@MOGMB@B8UrMXZX87B@B@8@M@BMqqOMB: . . . .",
        ".. . . ..i:2uGNBB@M@B@BP,.:r.k1ur@BO8@O@MF.OkMO0   . . . ",
        ". . . .  ::LrG@5MM@B@0i      .UL7k@M@B@O7 :BNO@i  . . . .",
        " . . . . .7,.iOq7Y;r    .  1PZ7uUJ;uNOX, ;O8E@M  . . . . ",
        ". . . .  .Mi:..:i,    uBr.@BEB@FMM1.. ..ruOBNO@   . . . .",
        " . . . . L:vqvr2u5UY7@BY @B@78M@SMBFLU7JJqMGPMB@ . . . . ",
        ". . . .  7Fi2U1POB@B@Bq rM@PrFZMMP@M@B@M@O0UN0@5  . . . .",
        ".. . . .  u@BOPjrYq@Gui:i@B@i5M@FXBMPP5NuJX@B@S  . . . . ",
        ". . . . .  .iJkM@B@Gjiii L@B.7@OY:@B8YvU8OBGL,  . . . . .",
        ".. . . . .    .rO@BZrr7r,    @X2u 8@urZ@MBv:   . . . . . ",
        ". . . . . . . i.UB@O:,u.:   75u:7 LBvr@BZN5   . . . . . .",
        " . . . . . .  ,i.,M@rr;.    .  .   M:5B1MS7  . . . . . . ",
        ". . . . . . . :i. iBM0O5Mquik7F11UFkGPZBMvL . . . . . . .",
        " . . . . . . .:: . uBB5UUYq:Oi7@:MLXFuG@FLv  . . . . . . ",
        ". . . . . . . ,i :  ruY0N:ZYZ7LrvrULS0@OLrL . . . . . . .",
        " . . . . . . . rJFr: iFUOLu2SuFJk1UEN@O2r5.  . . . . . . ",
        ". . . . . . . . 7B@8U:uqOEXOBMBZ88OOBOZY7   . . . . . . .",
        " . . . . . . .   .Y@Xv:iYOB@B@M@MMEGqBv.   . . . . . . . ",
        ". . . . . . . .    ,Mi   :u222GSXu1N@v  . . . . . . . . .",
        " . . . . . . . . .  .BJ.      .::jN@1  . . . . . . . . . ",
        ". . . . . . . . . .   2SkYuP@BGkOEO;  . . . . . . . . . ."
    };

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }
}
