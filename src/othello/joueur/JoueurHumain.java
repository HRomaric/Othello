package othello.joueur;

public class JoueurHumain extends Joueur {
    /**
     * Constructeur du joueurHumain
     * @param couleur - boolean - joueurNoir(false) ou blanc(true)
     */
    public JoueurHumain(boolean couleur) {
        super(couleur);
    }

    @Override
    public void jouer() {}

    public boolean estHumain(){
        return true;
    }
}
