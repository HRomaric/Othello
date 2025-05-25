package othello.outils;

import othello.plateau.Case;

public class FabriquePlateau {
    private FabriquePlateau() {
    }

    public static Case[][] getPlateauVide(){
        Case[][] plateau = new Case[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                plateau[i][j] = new Case();
            }
        }
        return plateau;
    }

    public static Case[][] getPlateauOthello(){
        Case[][] cases = getPlateauVide() ;
        cases[3][3].setPionBlanc();
        cases[3][4].setPionNoir();
        cases[4][4].setPionBlanc();
        cases[4][3].setPionNoir();

        return cases ;
    }

    public static Case [][] getPlateauPlein(){
        Case[][] cases = getPlateauVide() ;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cases[i][j].setPionBlanc();
            }
        }
        return cases ;
    }


    public static Case[][] getPlateauQuasiPlein(){
        Case[][] cases = getPlateauVide();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 7 && j == 7) continue; // leave one empty
                if ((i + j) % 2 == 0) {
                    cases[i][j].setPionBlanc();
                } else {
                    cases[i][j].setPionNoir();
                }
            }
        }
        return cases;
    }

}