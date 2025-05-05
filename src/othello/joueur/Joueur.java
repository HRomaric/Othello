package othello.joueur;

public abstract class Joueur {
    private boolean couleur; // true si blanc, false si noir

    public Joueur(boolean couleur) {
        this.couleur = couleur;
    }

    public boolean estBlanc(){
        return couleur;
    }

    public boolean estNoir(){
        return !couleur;
    }

    public abstract void jouer();
}