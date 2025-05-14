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
        // voisin droit et diagonale sens droit
        if (c<7){
            if (plateauDeJeu[l][c+1].isPionNoir() && verificationDirectionnel(l,c,0,1, "Blanc")){
                return true ;
            }
        }

        // voisin gauche et diagonale sens gauche
        if (c>0){
            if (plateauDeJeu[l][c-1].isPionNoir() && verificationDirectionnel(l,c,0,-1, "Blanc")){
                return true ;
            }
        }

        // voisin haut
        if (l>0){
            if (plateauDeJeu[l-1][c].isPionNoir() && verificationDirectionnel(l,c,-1,0, "Blanc")){
                return true ;
            }
        }

        // voisin bas
        if (l<7){
            if (plateauDeJeu[l+1][c].isPionNoir() && verificationDirectionnel(l,c,1,0, "Blanc")){
                return true ;
            }
        }

        return false;
    }



    public boolean coupPourNoir(int l, int c){
        return false;
    }

    public boolean verificationDirectionnel(int ligneJoue, int colonneJoue,int incrementationLigne, int incrementationColonne, String joueurCourant){
        int ligne = ligneJoue + incrementationLigne ;
        int colonne = colonneJoue +  incrementationColonne ;

        while (ligne < 8 && ligne>=0 && colonne < 8 && colonne>=0      ){
            if (plateauDeJeu[ligne][colonne].estVide()){
                return false;
            }
            if (plateauDeJeu[ligne][colonne].isPionNoir() && joueurCourant.equals("Noir") ){
                return true ;
            }
            if (plateauDeJeu[ligne][colonne].isPionBlanc() && joueurCourant.equals("Blanc") ){
                return true ;
            }
            colonne+=incrementationColonne;
            ligne+=incrementationLigne;
        }
        return false ;
    }









}