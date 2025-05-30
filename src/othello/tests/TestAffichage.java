package othello.tests;

import org.junit.jupiter.api.Test;
import othello.Jeu;
import othello.joueur.JoueurHumain;
import othello.outils.Affichage;
import othello.plateau.Etat;
import othello.plateau.Plateau;

public class TestAffichage {
    @Test
    public void testAffichage() {
        Plateau plateau = new Plateau();
        Etat etat = new Etat(plateau, true,new JoueurHumain(false), new JoueurHumain(true),1,1 );
        Affichage.afficher(etat);
    }
}
