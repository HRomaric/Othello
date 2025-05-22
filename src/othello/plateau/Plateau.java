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

    public Plateau(Plateau p){
        this.plateauDeJeu = FabriquePlateau.getPlateauVide();
        this.nbPiontNoir = p.getNbPiontNoir();
        this.nbPiontBlanc = p.getNbPiontBlanc();

        for (int ligne = 0 ; ligne < 8 ; ligne++){
            for (int colonne = 0; colonne <8; colonne++){
                this.plateauDeJeu[ligne][colonne] = new Case(p.recupererCase(ligne,colonne));
            }
        }
    }

    /**
     * Getteur du tableau de case
     * @return plateauDeJeu - Case[][]
     */
    public Case[][] getPlateauDeJeu() {
        return plateauDeJeu;
    }

    /**
     * Getteur d'une case du plateau
     * @param ligne - int
     * @param colonne - int
     * @return case - Case
     */
    public Case recupererCase(int ligne, int colonne) {
        return plateauDeJeu[ligne][colonne];
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


    public boolean coupPourJoueurCourant(int l, int c, String joueurCourant){
        // voisin droit et diagonale sens droit
        if (c<7){
            if (plateauDeJeu[l][c+1].isPionAdversaireCourant(joueurCourant) && verificationDirectionnel(l,c,0,1, joueurCourant)){
                return true ;
            }
        }

        // voisin gauche et diagonale sens gauche
        if (c>0){
            if (plateauDeJeu[l][c-1].isPionAdversaireCourant(joueurCourant) && verificationDirectionnel(l,c,0,-1, joueurCourant)){
                return true ;
            }
        }

        // voisin haut
        if (l>0){
            if (plateauDeJeu[l-1][c].isPionAdversaireCourant(joueurCourant) && verificationDirectionnel(l,c,-1,0, joueurCourant)){
                return true ;
            }
        }

        // voisin bas
        if (l<7){
            if (plateauDeJeu[l+1][c].isPionAdversaireCourant(joueurCourant) && verificationDirectionnel(l,c,1,0, joueurCourant)){
                return true ;
            }
        }

        // voisin diagonal bas-droite
        if (l < 7 && c < 7) {
            if (plateauDeJeu[l + 1][c + 1].isPionAdversaireCourant(joueurCourant) && verificationDirectionnel(l, c, 1, 1, joueurCourant)) {
                return true;
            }
        }
        // voisin diagonal bas-gauche
        if (l < 7 && c > 0) {
            if (plateauDeJeu[l + 1][c - 1].isPionAdversaireCourant(joueurCourant) && verificationDirectionnel(l, c, 1, -1, joueurCourant)) {
                return true;
            }
        }
        // voisin diagonal haut-droite
        if (l > 0 && c < 7) {
            if (plateauDeJeu[l - 1][c + 1].isPionAdversaireCourant(joueurCourant) && verificationDirectionnel(l, c, -1, 1, joueurCourant)) {
                return true;
            }
        }
        // voisin diagonal haut-gauche
        if (l > 0 && c > 0) {
            return plateauDeJeu[l - 1][c - 1].isPionAdversaireCourant(joueurCourant) && verificationDirectionnel(l, c, -1, -1, joueurCourant);
        }

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

    public void mangerDirectionnel(int ligneJoue, int colonneJoue,int incrementationLigne, int incrementationColonne, String joueurCourant){
        int ligne = ligneJoue + incrementationLigne ;
        int colonne = colonneJoue +  incrementationColonne ;

        while (ligne < 8 && ligne>=0 && colonne < 8 && colonne>=0      ){
            if (plateauDeJeu[ligne][colonne].estVide() || plateauDeJeu[ligne][colonne].isPionNoir() && joueurCourant.equals("Noir") || plateauDeJeu[ligne][colonne].isPionBlanc() && joueurCourant.equals("Blanc")    ){
                break;
            }
            if (plateauDeJeu[ligne][colonne].isPionNoir() && joueurCourant.equals("Blanc") ){
                plateauDeJeu[ligne][colonne].setPionBlanc();
            }
            if (plateauDeJeu[ligne][colonne].isPionBlanc() && joueurCourant.equals("Noir") ){
                plateauDeJeu[ligne][colonne].setPionNoir();
            }
            colonne+=incrementationColonne;
            ligne+=incrementationLigne;
        }
    }


    public void jouerCoupPlateau(int ligneJoue, int colonneJoue, String joueurCourant){
        switch (joueurCourant){
            case "Blanc":
                this.plateauDeJeu[ligneJoue][colonneJoue].setPionBlanc();
                break;
            case "Noir":
                this.plateauDeJeu[ligneJoue][colonneJoue].setPionNoir();
                break;
            default:
        }
    }

    public void manger(String joueurCourant, int l, int c){
        if (joueurCourant.equals("Blanc")){
            // voisin droit
            if (c<7){
                if (plateauDeJeu[l][c+1].isPionNoir() && verificationDirectionnel(l,c,0,1, "Blanc")){
                    mangerDirectionnel(l,c,0,1, joueurCourant);
                }
            }
            // voisin gauche
            if (c>0){
                if (plateauDeJeu[l][c-1].isPionNoir() && verificationDirectionnel(l,c,0,-1, "Blanc")){
                    mangerDirectionnel(l,c,0,-1, joueurCourant);
                }
            }
            // voisin haut
            if (l>0){
                if (plateauDeJeu[l-1][c].isPionNoir() && verificationDirectionnel(l,c,-1,0, "Blanc")){
                    mangerDirectionnel(l,c,-1,0, joueurCourant);
                }
            }
            // voisin bas
            if (l<7){
                if (plateauDeJeu[l+1][c].isPionNoir() && verificationDirectionnel(l,c,1,0, "Blanc")){
                    mangerDirectionnel(l,c,1,0, joueurCourant);
                }
            }
            mangerDiagonalBlanche(l, c);
        }
        else if (joueurCourant.equals("Noir")){
            // voisin droit
            if (c<7){
                if (plateauDeJeu[l][c+1].isPionBlanc() && verificationDirectionnel(l,c,0,1, "Noir")){
                    mangerDirectionnel(l,c,0,1, joueurCourant);
                }
            }
            // voisin gauche
            if (c>0){
                if (plateauDeJeu[l][c-1].isPionBlanc() && verificationDirectionnel(l,c,0,-1, "Noir")){
                    mangerDirectionnel(l,c,0,-1, joueurCourant);
                }
            }
            // voisin haut
            if (l>0){
                if (plateauDeJeu[l-1][c].isPionBlanc() && verificationDirectionnel(l,c,-1,0, "Noir")){
                    mangerDirectionnel(l,c,-1,0, joueurCourant);
                }
            }
            // voisin bas
            if (l<7){
                if (plateauDeJeu[l+1][c].isPionBlanc() && verificationDirectionnel(l,c,1,0, "Noir")){
                    mangerDirectionnel(l,c,1,0, joueurCourant);
                }
            }
            mangerDiagonalNoir(l, c);
        }
    }

    public void mangerDiagonalBlanche(int l, int c){
        // diagonale bas-droite
        if (l < 7 && c < 7) {
            if (plateauDeJeu[l + 1][c + 1].isPionNoir() && verificationDirectionnel(l, c, 1, 1, "Blanc")) {
                mangerDirectionnel(l, c, 1, 1, "Blanc");
            }
        }
        // diagonale bas-gauche
        if (l < 7 && c > 0) {
            if (plateauDeJeu[l + 1][c - 1].isPionNoir() && verificationDirectionnel(l, c, 1, -1, "Blanc")) {
                mangerDirectionnel(l, c, 1, -1, "Blanc");
            }
        }
        // diagonale haut-droite
        if (l > 0 && c < 7) {
            if (plateauDeJeu[l - 1][c + 1].isPionNoir() && verificationDirectionnel(l, c, -1, 1, "Blanc")) {
                mangerDirectionnel(l, c, -1, 1, "Blanc");
            }
        }
        // diagonale haut-gauche
        if (l > 0 && c > 0) {
            if (plateauDeJeu[l - 1][c - 1].isPionNoir() && verificationDirectionnel(l, c, -1, -1, "Blanc")) {
                mangerDirectionnel(l, c, -1, -1, "Blanc");
            }
        }
    }

    public void mangerDiagonalNoir(int l, int c){
        // diagonale bas-droite
        if (l < 7 && c < 7) {
            if (plateauDeJeu[l + 1][c + 1].isPionBlanc() && verificationDirectionnel(l, c, 1, 1, "Noir")) {
                mangerDirectionnel(l, c, 1, 1, "Noir");
            }
        }
        // diagonale bas-gauche
        if (l < 7 && c > 0) {
            if (plateauDeJeu[l + 1][c - 1].isPionBlanc() && verificationDirectionnel(l, c, 1, -1, "Noir")) {
                mangerDirectionnel(l, c, 1, -1, "Noir");
            }
        }
        // diagonale haut-droite
        if (l > 0 && c < 7) {
            if (plateauDeJeu[l - 1][c + 1].isPionBlanc() && verificationDirectionnel(l, c, -1, 1, "Noir")) {
                mangerDirectionnel(l, c, -1, 1, "Noir");
            }
        }
        // diagonale haut-gauche
        if (l > 0 && c > 0) {
            if (plateauDeJeu[l - 1][c - 1].isPionBlanc() && verificationDirectionnel(l, c, -1, -1, "Noir")) {
                mangerDirectionnel(l, c, -1, -1, "Noir");
            }
        }
    }











}