package othello.joueur;

import javafx.application.Platform;
import javafx.concurrent.Task;
import othello.Jeu;
import othello.fx.Observateur;
import othello.plateau.Etat;

import java.lang.reflect.InvocationTargetException;

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
        Thread taskThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (jeu.coupPossible()){
                Platform.runLater(() -> jeu.jouerIA());
            }
            else{
                System.out.println("Il faut passer un tour");
                jeu.getScene().afficherPasseTour();
                Etat etatCourant = jeu.getEtatCourant();
                jeu.setEtatCourant(etatCourant.successeur(etatCourant.getDerniereLigneDernierCoup(), etatCourant.getDerniereColonneDernierCoup()));
            }
        });
        taskThread.start();

    }

    @Override
    public void reagir() {
        if (jeu.getJoueurQuiJoue().equals(getCouleur())) {
            jouer();
        }
    }
}
