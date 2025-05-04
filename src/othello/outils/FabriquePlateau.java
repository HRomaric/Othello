package othello.outils;

import othello.plateau.Case;

public class FabriquePlateau {
    private FabriquePlateau() {
    }

    public static Case[][] getPlateauVide(){
        return new Case[8][8] ;
    }

    public static Case[][] getPlateauOthello(){
        Case[][] cases = getPlateauVide() ;
        cases[3][3].setPionBlanc();
        cases[3][4].setPionNoir();
        cases[4][4].setPionBlanc();
        cases[3][4].setPionNoir();

        return cases ;
    }


}
