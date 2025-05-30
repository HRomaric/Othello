package othello.plateau;


import othello.joueur.Joueur;
import othello.outils.Affichage;

import java.util.ArrayList;
import java.util.Iterator;

public class Etat implements Iterable<Etat> {
    private Plateau plateau; //Le plateau de jeu
    private boolean etatInitial; //dit si on est l'état initial
    private boolean etatFinal; //dit si on est un état final / échec
    private boolean joueurCourant; // false joueurNoir true joueurBlanc
    private Joueur joueurNoir;
    private Joueur joueurBlanc;
    private ArrayList<Etat> successeursValide ;
    private int[] dernierCoupJoue ;

    /**
     * Constructeur Etat initial
     */
    public Etat(Joueur joueurNoir, Joueur joueurBlanc) {
        plateau = new Plateau();
        joueurCourant = false ;
        etatInitial = true;
        etatFinal = false;
        successeursValide = new ArrayList<Etat>();
        this.joueurNoir = joueurNoir;
        this.joueurBlanc = joueurBlanc;
        dernierCoupJoue = new int[2];
        dernierCoupJoue[0] = -1;
        dernierCoupJoue[1] = -1;
        mettreAJourSuccesseurs();
    }

    /**
     * Etat courant
     * @param plateau - Plateau
     * @param joueurCourant - boolean
     */
    public Etat(Plateau plateau, boolean joueurCourant, Joueur joueurNoir, Joueur joueurBlanc, int ligneDernierCoup, int colonneDernierCoup) {
        this.plateau = plateau;
        etatInitial = false;
        etatFinal = false;
        this.joueurCourant = joueurCourant;
        successeursValide = new ArrayList<Etat>();
        this.joueurNoir = joueurNoir;
        this.joueurBlanc = joueurBlanc;
        dernierCoupJoue = new int[2];
        this.dernierCoupJoue[0] = ligneDernierCoup;
        this.dernierCoupJoue[1] = colonneDernierCoup;
    }


    public boolean isJoueurCourant() {
        return joueurCourant;
    }

    public void mettreAJourSuccesseurs(){
        Etat successeursPossible ;
        successeursValide.clear();
        for (int ligne = 0; ligne<8 ;ligne++ ){
            for (int colonne = 0; colonne<8; colonne++){
                if (coupPossible(ligne,colonne)){
                    Plateau p = new Plateau (this.plateau);
                    System.out.println(">>> Création d’un successeur pour : " + (this.joueurCourant ? "Blanc" : "Noir"));
                    successeursPossible = new Etat(p, this.joueurCourant, joueurNoir, joueurBlanc, ligne,colonne);
                    successeursPossible.jouerCoup(ligne,colonne);
                    successeursValide.add(successeursPossible);
                }
            }
        }
    }




    public boolean coupPossible(int l, int c){
        if (l>7 || l<0 || c>7  || c<0 || !plateau.recupererCase(l,c).estVide()){
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

    public Case [][] getPlateauDeJeu(){
        return plateau.getPlateauDeJeu();
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

    public Joueur getJoueurGagnantJoueur() {
        if (getPlateau().getNbPiontBlanc()> getPlateau().getNbPiontNoir()){
            return joueurBlanc;
        } else {
            return joueurNoir;
        }
    }


    public void jouerCoup(int ligneJoue, int colonneJoue){
        boolean joueurQuiJoue = this.joueurCourant;

        System.out.println(">>> Coup joué par : " + (joueurQuiJoue ? "Blanc" : "Noir"));
        String joueur = joueurQuiJoue ? "Blanc" :"Noir" ;

        plateau.jouerCoupPlateau(ligneJoue,colonneJoue,joueur );
        plateau.manger(joueur,ligneJoue,colonneJoue);
        plateau.majNbPiont();
        this.joueurCourant = !this.joueurCourant;
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


    @Override
    public Iterator<Etat> iterator() {
        return this.successeursValide.iterator();
    }

    public Joueur getJoueurCourant() {
        if (joueurCourant){
            return joueurBlanc;
        }
        else{
            return joueurNoir;
        }
    }

    public ArrayList<Etat> successeurs() {
        mettreAJourSuccesseurs();
        return successeursValide;
    }

    public int getDerniereLigneDernierCoup() {
        return dernierCoupJoue[0];
    }

    public int getDerniereColonneDernierCoup() {
        return dernierCoupJoue[1];
    }

    public Etat passerTour() {
        System.out.println("Passer tour");
        return new Etat(new Plateau(this.plateau), !joueurCourant, joueurNoir, joueurBlanc, -1, -1);
    }

    public boolean verifCoupPossible(){
        boolean coupPossible = false;
        for (int ligne = 0 ; ligne<8 && !coupPossible ; ligne++){
            for (int colonne = 0 ; colonne<8 ; colonne++){
                if (this.coupPossible(ligne, colonne)){
                    coupPossible = true;
                    break;
                }
            }
        }
        return coupPossible;
    }

    public Etat successeur(int ligneDernierCoup, int colonneDernierCoup) {
        Plateau copie = new Plateau(this.plateau);
        Etat e = new Etat(copie, this.joueurCourant, joueurNoir, joueurBlanc, ligneDernierCoup, colonneDernierCoup);
        e.jouerCoup(ligneDernierCoup, colonneDernierCoup);
        return e;
    }



    public Joueur getJoueurNoir() {
        return joueurNoir;
    }

    public Joueur getJoueurBlanc() {
        return joueurBlanc;
    }

    public void setJoueurBlanc(Joueur joueurBlanc) {
        this.joueurBlanc = joueurBlanc;
    }
}