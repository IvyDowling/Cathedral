package cathedral;

import asciiPanel.Render;
import java.awt.Color;

public final class PageLib {

    private static final Controller controller = Controller.getInstance();

    private final int[] keys = {37, 38, 39, 40};
    private final char[] characters = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
        'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static class OpeningPage implements PageInputAction {

        @Override
        public void pageAction(int keyCode) {
            switch (keyCode) {
                case 13:
                    System.out.println("opnpg");
                    controller.setPage(new Page(new PageLib.CharacterCreatorPage())); //enter key
                    break;
            }
        }

        @Override
        public Render[] getDefaultRender() {
            return new Render[]{
                new Render("Opening Page", 7, 10, Color.BLUE, Color.GRAY),
                new Render("Cathedral", 4, 3, Color.BLUE, Color.BLACK)

            };
        }
    }

    public static class CharacterCreatorPage implements PageInputAction {

        @Override
        public void pageAction(int keyCode) {
            switch (keyCode) {
//                case 34: controller.makeNewAction();
            }
        }

        @Override
        public Render[] getDefaultRender() {
            return new Render[]{
                new Render("Character Creation", 23, 3, Color.BLUE, Color.GRAY),
                new Render("Cathedral", 4, 3, Color.BLUE, Color.BLACK)

            };
        }
    }

    public static class MainPage implements PageInputAction {

        @Override
        public void pageAction(int keyCode) {
            switch (keyCode) {
//                case 34: controller.makeNewAction();
            }
        }

        @Override
        public Render[] getDefaultRender() {
            return new Render[]{
                new Render("Main Game Page", 13, 13, Color.BLUE, Color.GRAY),
                new Render("Cathedral", 4, 3, Color.BLUE, Color.BLACK)

            };
        }
    }
}
