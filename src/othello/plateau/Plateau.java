package othello.plateau;

import othello.outils.FabriquePlateau;

public class Plateau {
    private Case [][] plateauDeJeu;
    private int nbPiontBlanc;
    private int nbPiontNoir;



    public Plateau() {
        plateauDeJeu = FabriquePlateau.getPlateauOthello();
        majNbPiont();
    }



    public Case[][] getPlateauDeJeu() {
        return plateauDeJeu;
    }

    public int getNbPiontBlanc(){
        return nbPiontBlanc;
    }

    public int getNbPiontNoir(){
        return nbPiontNoir;
    }



    public void majNbPiont(){
        nbPiontBlanc = 0;
        nbPiontNoir = 0;

        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (plateauDeJeu[i][j].isPionBlanc()){
                    nbPiontBlanc++;
                }
                if (plateauDeJeu[i][j].isPionNoir()){
                    nbPiontNoir++;
                }
            }
        }
    }

    public boolean coupPourBlanc(int l, int c){
        // vérification horizontal
        if (c<8){
            if (plateauDeJeu[l][c+1].isPionNoir()){
                for (int cpt = c+2 ; cpt<8;cpt++){
                    if (plateauDeJeu[l][cpt].isPionBlanc()){
                        return true;
                    }
                    if (plateauDeJeu[l][cpt].estVide()){
                        break;
                    }
                }
            }
            if (plateauDeJeu[l][c-1].isPionNoir()){
                for (int cpt = c-2 ; cpt<8;cpt--){
                    if (plateauDeJeu[l][cpt].isPionBlanc()){
                        return true;
                    }
                    if (plateauDeJeu[l][cpt].estVide()){
                        break;
                    }
                }
            }
        }


        // vérification vertical



        // vérification diagonal


        return false;
    }



    public boolean coupPourNoir(int l, int c){
        return false;
    }











}
