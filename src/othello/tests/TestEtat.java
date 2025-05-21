package othello.tests;

import org.junit.jupiter.api.Test;
import othello.plateau.Etat;
import othello.plateau.Plateau;

import static org.junit.jupiter.api.Assertions.*;

public class TestEtat {
    @Test
    public void testConstructeur1() {
        Etat etat = new Etat();

        assertTrue(etat.getEtatInitial());
        assertFalse(etat.estEtatFinal());
        assertNotNull(etat.getPlateau());
    }

    @Test
    public void testConstructeur2() {
        Plateau plateau = new Plateau();
        Etat etat = new Etat(plateau, true);

        assertFalse(etat.getEtatInitial());
        assertFalse(etat.estEtatFinal());
        assertNotNull(etat.getPlateau());
    }

    @Test
    public void testMettreAJourSuccesseur() {
        Plateau plateau = new Plateau();
        Etat etat = new Etat(plateau, false);
        etat.mettreAJourSuccesseurs();
        System.out.println(etat.nbSuccesseurPossibles());
    }



}
