package othello.tests;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import othello.plateau.Plateau;

public class TestPlateau {

    @Test
    public void testConstructeurPlateau(){
        Plateau p = new Plateau();
        assertEquals(2, p.getNbPiontBlanc());
        assertEquals(2, p.getNbPiontNoir());
    }



}
