package othello.plateau;

public class Etat {
    private Plateau plateau;

    /**
     * Constructeur Etat initial
     */
    public Etat(){
    plateau = new Plateau();
    }

    /**
     * Etat courant
     * @param plateau
     */
    public Etat(Plateau plateau){
        this.plateau = plateau;
    }


    public Plateau getPlateau() {
        return plateau;
    }
}
