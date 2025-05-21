package othello.fx;

import javafx.scene.control.Button;

public class Bouton extends Button {
    private int x;
    private int y;

    /**
     * constructeur de Bouton
     * @param x - int - position
     * @param y - int - position
     */
    public Bouton(int x, int y) {
        super();

        assert x >= 0 && y >= 0;

        this.x = x;
        this.y = y;
    }

    /**
     * constructeur de Bouton
     * @param text - nom du bouton - string
     * @param x - position - int
     * @param y - position - int
     */
    public Bouton(String text, int x, int y) {
        super(text);

        assert x >= 0 && y >= 0;

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
