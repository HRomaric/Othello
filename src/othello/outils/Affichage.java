package othello.outils;

import othello.plateau.Case;
import othello.plateau.Etat;
import java.util.Scanner;

public class Affichage {
    private static final Scanner demandeurCoup = new Scanner(System.in);


    /**
     * Procédure qui affiche un plateau
     * @param e - Etat
     */
    public static void afficher(Etat e) {
        StringBuilder sb = new StringBuilder();
        Case[][] p = e.getPlateau().getPlateauDeJeu();

        sb.append("#############################################\n");
        sb.append("######  1   2   3   4   5   6   7   8  ######\n");
        sb.append("#############################################\n");

        for(int i = 0; i < 8; i++) {
            if (i == 0) {
                sb.append("## 1 #");
            }

            for (int j = 0; j < 8; j++) {
                if (j == 0) {
                    sb.append(" ");
                }
                if (!p[i][j].isPionBlanc() && !p[i][j].isPionNoir()) {
                    sb.append(" -  ");
                } else if (p[i][j].isPionBlanc()) {
                    sb.append(" B  ");
                } else if (p[i][j].isPionNoir()) {
                    sb.append(" N  ");
                }
            }

            sb.append("# " + Integer.toString(i+1) + " ##\n");
            if (i < 7){
                sb.append("## " + Integer.toString(i+2) + " #");
            }

        }
        sb.append("#############################################\n");
        sb.append("######  1   2   3   4   5   6   7   8  ######\n");
        sb.append("#############################################\n");

        System.out.println(sb);
    }

    public static String demanderCoup(Etat etat) {
        StringBuilder nomJoueurCourant = new StringBuilder("Joueur  ");
        if (etat.joueurQuiJoue()){
            nomJoueurCourant.append(" Blanc ");
        } else {
            nomJoueurCourant.append(" Noir ");
        }
        nomJoueurCourant.append("quel coup souhaitez vous jouer ? ligne colonne ");
        System.out.println(nomJoueurCourant);
        return demandeurCoup.nextLine();
    }

    public static void fermerDemandeurCoup() {
        demandeurCoup.close();
    }





}
