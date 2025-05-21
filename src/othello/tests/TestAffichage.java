package othello.tests;

import org.junit.jupiter.api.Test;
import othello.outils.Affichage;
import othello.plateau.Etat;
import othello.plateau.Plateau;

public class TestAffichage {
    @Test
    public void testAffichage() {
        Plateau plateau = new Plateau();
        Etat etat = new Etat(plateau, true);
        Affichage.afficher(etat);
    }
}
