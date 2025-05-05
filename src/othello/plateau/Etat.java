package othello.plateau;

public class Etat {
    private Plateau plateau;
    private boolean joueurCourant; // false joueurNoir true joueurBlanc

    /**
     * Constructeur Etat initial
     */
    public Etat(){
        plateau = new Plateau();
        joueurCourant = false ;
    }

    /**
     * Etat courant
     * @param plateau
     */
    public Etat(Plateau plateau){
        this.plateau = plateau;
    }

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
}
