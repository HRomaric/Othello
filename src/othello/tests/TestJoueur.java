package othello.tests;

import org.junit.jupiter.api.Test;
import othello.Jeu;
import othello.joueur.Joueur;
import othello.joueur.JoueurHumain;
import static org.junit.jupiter.api.Assertions.*;

public class TestJoueur {
    @Test
    public void testConstructeur(){
        Joueur j1 = new JoueurHumain(true);
        Joueur j2 = new JoueurHumain(false);

        assertTrue(j1.estBlanc());
        assertFalse(j2.estBlanc());

        assertFalse(j1.estNoir());
        assertTrue(j2.estNoir());
    }
}
