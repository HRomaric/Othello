package othello.tests;


import org.junit.jupiter.api.Test;
import othello.plateau.Case;
import static org.junit.jupiter.api.Assertions.*;

public class TestCase {

    @Test
    public void testConstructeur(){
        Case c1  = new Case();
        assertFalse(c1.isPionBlanc(),"Erreur par défaut une case n'est pas blanche");
        assertFalse(c1.isPionNoir(),"Erreur par défaut une case n'est pas noire");
    }
}
