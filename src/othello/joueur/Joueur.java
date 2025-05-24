package othello.joueur;

public abstract class Joueur {
    private boolean couleur; // true si blanc, false si noir


    /**
     * Constructeur de Joueur
     * @param couleur - boolean - dit si le joueur est blanc ou noir
     */
    public Joueur(boolean couleur) {
        this.couleur = couleur;
    }

    /**
     * Getteur 1 de la couleur - dit s'il est blanc ou pas
     * @return couleur - boolean
     */
    public boolean estBlanc(){
        return couleur;
    }

    /**
     * Getteur 2 de la couleur - dit s'il est noir ou pas
     * @return couleur - boolean
     */
    public boolean estNoir(){
        return !couleur;
    }

    public abstract boolean estHumain();


    /**
     * Procédure qui fait jouer le joueur
     */
    public abstract void jouer();

    public String getCouleur() {
        if (couleur){
            return "Blanc";
        }
        else{
            return "Noir";
        }
    }
}