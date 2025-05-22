package othello.fx;

import javafx.scene.control.Label;
import othello.Jeu;
import othello.plateau.SujetObserver;

public class Texte extends Label implements Observateur{
    private String txt;
    private int j;
    private SujetObserver jeu;

    public Texte(String texte, int i, SujetObserver sujetObserver) {
        super();
        if (i == 2){
           this.setText(texte + "Noir");
        }
        else{
            this.setText(texte + "0");
        }
        this.txt = texte;
        j = i;
        jeu = sujetObserver;
        jeu.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        int nbPiont;
        if (j == 0){
            nbPiont = ((Jeu) jeu).getPlateau().getNbPiontBlanc();
            this.setText(txt + nbPiont);
        }
        else if (j == 1){
            nbPiont = ((Jeu) jeu).getPlateau().getNbPiontNoir();
            this.setText(txt + nbPiont);
        }
        else if (j == 2){
            String j = ((Jeu) jeu).getEtatCourant().quiEstLeJoueurCourant();
            this.setText(txt + j);
        }
    }
}
