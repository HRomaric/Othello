package othello.tests;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import othello.outils.Affichage;
import othello.plateau.Etat;
import othello.plateau.Plateau;

public class TestPlateau {

    @Test
    public void testConstructeurPlateau(){
        Plateau p = new Plateau();
        assertEquals(2, p.getNbPiontBlanc());
        assertEquals(2, p.getNbPiontNoir());
    }

    @Test
    public void testVerificationDirectionnelPourBlanc(){
        Plateau p = new Plateau();

        // horizontal vers la gauche
        boolean test = p.verificationDirectionnel(3,5,0,-1,"Blanc"  );
        assertTrue(test,"Erreur de verification directionnel vers la gauche car la case 3 5 à bien un voisin de même couleur plus loin vers la gauche  ");
        // horizontale vers la droite
        boolean test2 = p.verificationDirectionnel(3,5,0,1,"Blanc"  );
        assertFalse(test2, "Erreur de vérification directionnel vers la droite car la case 3 5 n'a pas de voisin de même couleur plus loin vers la droite ");

        // vertical vers le Bas

        boolean test3 = p.verificationDirectionnel(5,3,1,0,"Blanc"  );
        assertFalse(test3, "Erreur de vérification directionnel vers le bas , la case 5 3 n'a pas de voisin vertical plus bas de la même couleur");
        // vertical vers le haut

        boolean test4 = p.verificationDirectionnel(5,3,-1,0,"Blanc"  );
        assertTrue(test4, "Erreur de vérification directionnel vers le haut, la case 5 3 a bien un voisin de couleur blanche plus haut ");
    }


    @Test
    public void testVerificationDirectionnelPourNoir(){
        Plateau p = new Plateau();
        // horizontal vers la gauche
        boolean test = p.verificationDirectionnel(3,2,0,-1,"Noir"  );
        assertFalse(test, "Erreur de vérification directionnel vers la droite car la case 3 5 n'a pas de voisin de même couleur plus loin vers la gauche ");
        // horizontale vers la droite
        boolean test2 = p.verificationDirectionnel(3,2,0,1,"Noir"  );
        assertTrue(test2,"Erreur de verification directionnel vers la gauche car la case 3 2 à bien un voisin de même couleur plus loin vers la droite  ");

        // vertical vers le Bas
        boolean test3 = p.verificationDirectionnel(2,3,1,0,"Noir"  );
        assertTrue(test3, "Erreur de vérification directionnel vers le haut, la case 2 3 a bien un voisin de couleur blanche plus haut ");
        // vertical vers le haut

        boolean test4 = p.verificationDirectionnel(2,3,-1,0,"Noir"  );
        assertFalse(test4, "Erreur de vérification directionnel vers le bas , la case 2 3 n'a pas de voisin vertical plus bas de la même couleur");

    }


    @Test
    public void testJouerCoupPlateau(){
        // attention dans ce test, on ne vérifie pas si le coup est correcte on veut voir s'il modifie correctement le plateau
        Plateau p = new Plateau();
        p.jouerCoupPlateau(2,2, "Blanc");
        //Affichage.afficher(new Etat(p,true));
        assertTrue(p.recupererCase(2,2).isPionBlanc() && !p.recupererCase(2,2).isPionNoir()    );
    }
}
