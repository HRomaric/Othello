package othello.joueur;

import javafx.application.Platform;
import othello.Jeu;
import othello.fx.Observateur;
import othello.outils.Affichage;
import othello.outils.Algo;
import othello.outils.ThreadsManager;
import othello.plateau.Etat;

public class JoueurIA extends Joueur{
    private int strategie; //0 pour evalDiffPiont   1 pour evalPoidsPos    peut être d'autres éval plus tard

    public JoueurIA(boolean couleur, int strat){
        super(couleur);
        this.strategie = strat;
    }

    public void setJeu(Jeu j){
        this.ajouterJeu(j);
    }


    @Override
    public void jouerIA() {
        Thread taskThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(() -> {
                Etat etatCourant = this.getJeu().getEtatCourant();
                etatCourant.mettreAJourSuccesseurs();
                // log de debug
                /*
                System.out.println("###################### Affichage sucesseurs ###################### ");
                for (Etat successeurs : etatCourant){
                    Affichage.afficher(successeurs);
                }
                System.out.println("##################################################################");
                */
                //System.out.println(">>> Minimax lancé → joueur courant = " + etatCourant.quiEstLeJoueurCourant());

                Etat etatPropose = Algo.minimaxAlphaBeta(this.getJeu().getEtatCourant(), 5, strategie);

                int x = etatPropose.getDerniereLigneDernierCoup();
                int y = etatPropose.getDerniereColonneDernierCoup();

                if (x == -1 && y == -1){
                    System.out.println("Erreur IA");
                    return;
                }

                this.getJeu().jouerIG(x, y);
            } ) ;
        });
        ThreadsManager.getInstance().lancer(taskThread);
    }

    public boolean estHumain(){
        return false;
    }

    @Override
    public void jouerHumain(int x, int y) {}
}
