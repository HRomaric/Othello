package othello.outils;

import othello.plateau.Case;
import othello.plateau.Etat;
import othello.plateau.Plateau;

import java.util.ArrayList;

public class Algo {
    private Algo(){}

    private static int evaluation(int c, Etat e){
        ArrayList<Etat> S = e.successeurs();
       int score_max, score_min ;
       if (e.estEtatFinal()){
           if (e.getPlateau().getNbPiontBlanc() == e.getPlateau().getNbPiontNoir()){
               return 0;
           }
            if (!e.getJoueurGagnantJoueur().estHumain()){
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
       }
       if(c ==0){
            return eval0(e,c);
       }
       if (!e.getJoueurCourant().estHumain()){
           score_max = Integer.MIN_VALUE;
           for (Etat successeurs : S){
               score_max = Math.max(score_max, evaluation(c-1, successeurs));
           }
           return score_max;
       } else {
           score_min = Integer.MAX_VALUE;
           for (Etat successeurs : S){
               score_min = Math.min(score_min, evaluation(c-1, successeurs));
           }
           return score_min;

       }
    }


    public static Etat minimax (Etat e , int c ){
        ArrayList<Etat> S = e.successeurs();
        int score_max = Integer.MIN_VALUE ;
        int score;
        Etat e_sortie = null;
        for (Etat successeurs : S){
            score = evaluation(c, successeurs);
            if (score >= score_max){
                score_max = score;
                e_sortie = successeurs;
            }
        }
        return e_sortie;
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
