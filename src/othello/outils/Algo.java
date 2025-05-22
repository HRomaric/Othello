package othello.outils;

import othello.plateau.Etat;

import java.util.ArrayList;

public class Algo {
    private Algo(){}

    private double evaluation(int c, Etat e){
        if (e.estEtatFinal()){


        }
        if(c ==0){

        }


        ArrayList<Etat> s = new ArrayList<>(e.nbSuccesseurPossibles());
       for (Etat successeurs : e){
           s.add(successeurs);
       }





        return 3.5;
    }


    public static Etat minimax (Etat e , int c ){

        return null;
    }









}
