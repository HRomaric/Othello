package othello;

import othello.plateau.Etat;

public class Jeu {
    private Etat etatCourant ;

    public void partie(){
        etatCourant = new Etat(); // initialisation du Jeu
        while (!etatCourant.estEtatFinal()){
            this.jouer();
        }
        System.out.println("Félicitation joueurs"+ etatCourant.getJoueurGagnant()  + " vous avez gagné !");
    }

    public void jouer(){

    }




}