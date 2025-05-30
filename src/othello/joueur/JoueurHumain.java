package othello.joueur;

import othello.Jeu;
import othello.exceptions.ExceptionCoup;
import othello.outils.Affichage;
import othello.plateau.Etat;

public class JoueurHumain extends Joueur {
    /**
     * Constructeur du joueurHumain
     * @param couleur - boolean - joueurNoir(false) ou blanc(true)
     */
    public JoueurHumain(boolean couleur) {
        super(couleur);
    }

    public void setJeu(Jeu j){
        this.ajouterJeu(j);
    }

    public void jouerTerminal() {
    }

    @Override
    public void jouerHumain(int x, int y){
        Jeu j = this.getJeu();
        Etat e = j.getEtatCourant();
        if (e.coupPossible(x, y)){
            j.jouerIG(x, y);
        }
        else {
            try {
                throw new ExceptionCoup();
            } catch (ExceptionCoup ex) {
                ex.afficherMsg();
            }
        }
    }

    @Override
    public boolean estHumain(){
        return true;
    }

    @Override
    public void jouerIA() {}
}
