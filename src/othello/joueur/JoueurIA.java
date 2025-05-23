package othello.joueur;

public class JoueurIA extends Joueur {
    private int strategie; //0 pour evalDiffPiont   1 pour evalPoidsPos    peut être d'autres éval plus tard

    public JoueurIA(boolean couleur, int strat){
        super(couleur);
        this.strategie = strat;
    }

    public boolean estHumain(){
        return false;
    }

    /**
     * Procédure qui fait jouer le joueurIA
     */
    @Override
    public void jouer() {

    }
}
