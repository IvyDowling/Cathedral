package cathedral;

public abstract class Page {

    private Chunk defaultRender;

    public Page(Chunk c) {
        if (!(c == null)) {
            defaultRender = c;
        }

    }

    public Page(String[] s) {
        defaultRender = new Chunk(s, 0, 0);
    }

    public Chunk getDefaultRender() {
        return defaultRender;
    }
}
