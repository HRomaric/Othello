package othello.joueur;

import othello.Jeu;
import othello.fx.Observateur;
import othello.outils.Algo;

public class JoueurIA extends Joueur implements Observateur {
    private int strategie; //0 pour evalDiffPiont   1 pour evalPoidsPos    peut être d'autres éval plus tard
    private Jeu jeu;

    public JoueurIA(boolean couleur, int strat){
        super(couleur);
        this.strategie = strat;
    }

    public void ajouterJeu(Jeu j){
        this.jeu = j;
        this.jeu.ajouterObservateur2(this);
        this.jeu.notifierObservateur2();
    }

    public boolean estHumain(){
        return false;
    }

    /**
     * Procédure qui fait jouer le joueurIA
     */
    @Override
    public void jouer() {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        jeu.jouerIA();
    }

    @Override
    public void reagir() {
        if (jeu.getJoueurQuiJoue().equals(getCouleur())){
            jouer();
        }
    }
}
