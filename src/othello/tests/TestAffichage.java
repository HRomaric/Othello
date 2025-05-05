package othello.tests;

import org.junit.Test;
import othello.outils.Affichage;
import othello.plateau.Plateau;

public class TestAffichage {
    @Test
    public void testAffichage() {
        Plateau plateau = new Plateau();
        Affichage affichage = new Affichage();
        affichage.afficher(plateau);
    }
}
