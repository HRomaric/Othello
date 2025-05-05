package othello.tests;

import org.junit.Test;
import othello.outils.Affichage;
import othello.plateau.Etat;
import othello.plateau.Plateau;

public class TestAffichage {
    @Test
    public void testAffichage() {
        Plateau plateau = new Plateau();
        Etat etat = new Etat(plateau);
        Affichage affichage = new Affichage();
        affichage.afficher(etat);
    }
}
