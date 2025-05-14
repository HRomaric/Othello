package othello.plateau;

import othello.outils.FabriquePlateau;

public class Plateau {
    private Case [][] plateauDeJeu; // Tableau de case -> représente le plateau de jeu
    private int nbPiontBlanc; // Nombre de pion blanc
    private int nbPiontNoir; //Nombre de pion noir


    /**
     * Constructeur du plateau
     */
    public Plateau() {
        plateauDeJeu = FabriquePlateau.getPlateauOthello();
        majNbPiont();
    }



    /**
     * Getteur du tableau de case
     * @return plateauDeJeu - Case[][]
     */
    public Case[][] getPlateauDeJeu() {
        return plateauDeJeu;
    }

    /**
     * Getteur du nombre de pion blanc
     * @return nbPiontBlanc - int
     */
    public int getNbPiontBlanc(){
        return nbPiontBlanc;
    }

    /**
     * Getteur du nombre de pion noir
     * @return nbPiontNoir - int
     */
    public int getNbPiontNoir(){
        return nbPiontNoir;
    }


    /**
     * Fonction qui recompte le nombre de pions blanc et noir
     */
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
        // voisin droit
        if (plateauDeJeu[l][c+1].isPionNoir()){
            if (verificationDirectionnel(l,c,0,1, "Blanc")){

            }
        }


        // voisin gauche

        // vérification vertical



        // vérification diagonal




        //

        return false;
    }



    public boolean coupPourNoir(int l, int c){
        return false;
    }

    public boolean verificationDirectionnel(int ligneJoue, int colonneJoue,int incrementationligne, int incrementationColonne, String joueurCourant){
        int departLigne = ligneJoue + incrementationligne ;
        int departColonne = colonneJoue +  incrementationColonne ;
        for (int i = departLigne ; i < 8 && i>0 ; i+=incrementationligne     ){
            for(int j = departColonne ; j<8 && j>0 ; j+=incrementationColonne){
                if (plateauDeJeu[i][j].estVide()){
                    return false;
                }
                if (plateauDeJeu[i][j].isPionNoir() && joueurCourant.equals("Noir") ){
                    return true ;
                }
                if (plateauDeJeu[i][j].isPionBlanc() && joueurCourant.equals("Blanc") ){
                    return true ;
                }
            }
        }
        return false ;
    }









}