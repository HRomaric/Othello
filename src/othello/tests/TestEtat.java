package othello.tests;

import org.junit.jupiter.api.Test;
import othello.joueur.JoueurHumain;
import othello.outils.Affichage;
import othello.plateau.Etat;
import othello.plateau.Plateau;

import static org.junit.jupiter.api.Assertions.*;

public class TestEtat {
    @Test
    public void testConstructeur1() {
        Etat etat = new Etat(new JoueurHumain(false), new JoueurHumain(true));

        assertTrue(etat.getEtatInitial());
        assertFalse(etat.estEtatFinal());
        assertNotNull(etat.getPlateau());
    }

    @Test
    public void testConstructeur2() {
        Plateau plateau = new Plateau();
        Etat etat = new Etat(plateau, true, new JoueurHumain(true), new JoueurHumain(false),1,1);

        assertFalse(etat.getEtatInitial());
        assertFalse(etat.estEtatFinal());
        assertNotNull(etat.getPlateau());
    }

    @Test
    public void testMettreAJourSuccesseur() {
        Plateau plateau = new Plateau();
        Etat etat = new Etat(plateau, false, new JoueurHumain(false), new JoueurHumain(true),1,1);
        etat.mettreAJourSuccesseurs();
        assertEquals(4, etat.nbSuccesseurPossibles(), "Erreur dans la situation de départ il y'a successeurs attendu" );
    }



}
