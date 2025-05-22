package othello.plateau;

import java.util.ArrayList;

public class Etat {
    private Plateau plateau; //Le plateau de jeu
    private boolean etatInitial; //dit si on est l'état initial
    private boolean etatFinal; //dit si on est un état final / échec
    private boolean joueurCourant; // false joueurNoir true joueurBlanc
    private ArrayList<Etat> successeursValide ;

    /**
     * Constructeur Etat initial
     */
    public Etat(){
        plateau = new Plateau();
        joueurCourant = false ;
        etatInitial = true;
        etatFinal = false;
        successeursValide = new ArrayList<Etat>();
    }

    /**
     * Etat courant
     * @param plateau - Plateau
     * @param joueurCourant - boolean
     */
    public Etat(Plateau plateau, boolean joueurCourant){
        this.plateau = plateau;
        etatInitial = false;
        etatFinal = false;
        this.joueurCourant = joueurCourant;
        successeursValide = new ArrayList<Etat>();
    }

    /**
     * Permet de récuperer le successeur d'un état après avoir jouer un coup
     * @return successeur - Etat
     */
    public Etat successeur(){
        return new Etat(plateau, !joueurCourant);
    }


    public void mettreAJourSuccesseurs(){
        Etat successeursPossible ;
        successeursValide.clear();
        for (int ligne = 0; ligne<8 ;ligne++ ){
            for (int colonne = 0; colonne<8; colonne++){
                if (coupPossible(ligne,colonne)){
                    successeursPossible = new Etat(this.plateau, this.joueurCourant);
                    successeursPossible.jouerCoup(ligne,colonne);
                    successeursValide.add(successeursPossible);
                }
            }
        }
    }




    public boolean coupPossible(int l, int c){
        if (l>7 || l<0 || c>7  || c<0 || !plateau.recupererCase(l,c).estVide() ){
            return false;
        }
        if (!joueurCourant){ //Joueur Noir
            return plateau.coupPourJoueurCourant(l,c,"Noir");
        } else { // Joueur Blanc
            return plateau.coupPourJoueurCourant(l,c,"Blanc");
        }
    }

    public boolean joueurQuiJoue(){
        return this.joueurCourant;
    }


    public String quiEstLeJoueurCourant(){
        if (joueurCourant){
            return "Blanc";
        }
        return "Noir";
    }


    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * Getteur du champ etatInitial
     * @return etatInitial - boolean
     */
    public boolean getEtatInitial() {
        return etatInitial;
    }

    /**
     * Getteur du champ etatFinal
     * @return etatFinal - boolean
     */
    public boolean estEtatFinal() {
        return etatFinal;
    }

    public String getJoueurGagnant() {
        if (getPlateau().getNbPiontBlanc()> getPlateau().getNbPiontNoir()){
            return "Blanc";
        } else {
            return "Noir";
        }
    }

    public void jouerCoup(int ligneJoue, int colonneJoue){
        plateau.jouerCoupPlateau(ligneJoue,colonneJoue, quiEstLeJoueurCourant());
        plateau.manger(quiEstLeJoueurCourant(),ligneJoue,colonneJoue);
        plateau.majNbPiont();
    }

    public void verificationEtatFinal(){
        boolean coupPossible = false;
        for (int ligne = 0; ligne < 8 && !coupPossible ; ligne++) {
            for (int colonne = 0; colonne < 8; colonne++) {
                if (coupPossible(ligne, colonne)){
                    coupPossible = true;
                    break;
                }
            }
        }
        if (!coupPossible){
            joueurCourant = !joueurCourant;
            for (int ligne = 0; ligne < 8 && !coupPossible ; ligne++) {
                for (int colonne = 0; colonne < 8; colonne++) {
                    if (coupPossible(ligne, colonne)){
                        coupPossible = true;
                        joueurCourant = !joueurCourant;
                        break;
                    }
                }
            }
        }
        if (!coupPossible){
            etatFinal = true;
        }
    }

    public int nbSuccesseurPossibles(){
        return this.successeursValide.size();
    }





}