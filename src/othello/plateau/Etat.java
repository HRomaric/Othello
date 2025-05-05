package othello.plateau;

public class Etat {
    private Plateau plateau; //Le plateau de jeu
    private boolean etatInitial; //dit si on est l'état initial
    private boolean etatFinal; //dit si on est un état final / échec
    private boolean joueurCourant; // false joueurNoir true joueurBlanc

    /**
     * Constructeur Etat initial
     */
    public Etat(){
        plateau = new Plateau();
        joueurCourant = false ;
        etatInitial = true;
        etatFinal = false;
    }

    /**
     * Etat courant
     * @param plateau
     */
    public Etat(Plateau plateau){
        this.plateau = plateau;
        etatInitial = false;
        etatFinal = false;
    }

    /**
     * Getteur du plateau de jeu
     * @return plateau - Plateau
     */
    public Etat successeur(){
        return new Etat(plateau);
    }

    public boolean coupPossible(int l, int c){
        if (!joueurCourant){ //Joueur Noir
            return plateau.coupPourNoir(l,c);
        } else { // Joueur Blanc
            return plateau.coupPourBlanc(l,c);
        }

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
    public boolean getEtatFinal() {
        return etatFinal;
    }
}