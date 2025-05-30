package othello.joueur;

import othello.Jeu;

public abstract class Joueur {
    private boolean couleur; // true si blanc, false si noir
    private Jeu jeu;

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
     * Procédure qui fait jouer le joueur humain
     */
    public abstract void jouerHumain(int x, int y);

    /**
     * Procédure qui fait jouer le joueur IA
     */
    public abstract void jouerIA();


    public void verifTour(){
        jeu.verifierCoupExistant();
        if (!estHumain()){
            jouerIA();
        }
    }

    public String getCouleur() {
        if (couleur){
            return "Blanc";
        }
        else{
            return "Noir";
        }
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void ajouterJeu(Jeu jeu) {
        this.jeu = jeu;
    }
}
