package othello.outils;

import othello.plateau.Case;
import othello.plateau.Etat;
import othello.plateau.Plateau;

public class  Algo {
    private Algo(){}

    private double evaluation(int c, Etat e){
        if (e.estEtatFinal()){


        }

        return 3.5;
    }


    public static Etat minimax (Etat e , int c ){

        return null;
    }

    public static int eval0(Etat e, int i){
        switch (i){
            case 0:{
                return evalDiffPiont(e);
            }
            case 1: {
                return evalPoidsPos(e);
            }
        }
        return -1;
    }

    public static int evalDiffPiont(Etat e){
        Plateau p = e.getPlateau();
        if (e.joueurQuiJoue()){
            return p.getNbPiontBlanc() - p.getNbPiontNoir();
        }
        else {
            return p.getNbPiontNoir() - p.getNbPiontBlanc();
        }
    }

    public static int evalPoidsPos(Etat e){
        int [][] points = {
                {100, 20 , 50 , 50 , 50 , 50 , 20 , 100},
                {20 , 20 , 50 , 25 , 25 , 50 , 20 , 20 },
                {50 , 25 , 25 , 5  , 5  , 25 , 25 , 50 },
                {50 , 25 , 5  , 0  , 0  , 5  , 25 , 50 },
                {50 , 25 , 5  , 0  , 0  , 5  , 25 , 50 },
                {50 , 25 , 25 , 5  , 5  , 25 , 25 , 50 },
                {20 , 20 , 50 , 25 , 25 , 50 , 20 , 20 },
                {100, 20 , 50 , 50 , 50 , 50 , 20 , 100},
        };

        int scoreB = 0;
        int scoreN = 0;
        Case[][] p = e.getPlateau().getPlateauDeJeu();

        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (p[i][j].isPionBlanc()){
                    scoreB += points[i][j];
                }
                if (p[i][j].isPionNoir()){
                    scoreN += points[i][j];
                }
            }
        }

        if (e.joueurQuiJoue()){
            return scoreB;
        }
        else {
            return scoreN;
        }
    }




}