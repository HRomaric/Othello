package othello.tests;

import org.junit.jupiter.api.Test;
import othello.plateau.Etat;
import othello.plateau.Plateau;

import static org.junit.Assert.*;

public class TestEtat {
    @Test
    public void testConstructeur1() {
        Etat etat = new Etat();

        assertTrue(etat.getEtatInitial());
        assertFalse(etat.getEtatFinal());
        assertNotNull(etat.getPlateau());
    }

    @Test
    public void testConstructeur2() {
        Plateau plateau = new Plateau();
        Etat etat = new Etat(plateau);

        assertFalse(etat.getEtatInitial());
        assertFalse(etat.getEtatFinal());
        assertNotNull(etat.getPlateau());
    }
}
