package othello.plateau;

import othello.outils.FabriquePlateau;

public class Plateau {
    private Case [][] plateauDeJeu;

    public Plateau() {
        plateauDeJeu = FabriquePlateau.getPlateauOthello();
    }
}
