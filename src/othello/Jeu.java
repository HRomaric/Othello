package othello;

import othello.outils.Affichage;
import othello.plateau.Etat;

public class Jeu {
    private Etat etatCourant ;

    public void partie(){
        etatCourant = new Etat(); // initialisation du Jeu
        while (!etatCourant.estEtatFinal()){
            Affichage.afficher(etatCourant);
            this.jouer();

        }
        System.out.println("Félicitation joueurs"+ etatCourant.getJoueurGagnant()  + " vous avez gagné !");
        Affichage.fermerDemandeurCoup();
    }

    public void jouer(){
        boolean coupPossible = false;
        for (int ligne = 0 ; ligne<8 && !coupPossible ; ligne++){
            for (int colonne = 0 ; colonne<8 ; colonne++){
                if (etatCourant.coupPossible(ligne, colonne)){
                    coupPossible = true;
                    break;
                }

            }
        }
        if  (coupPossible){
            String coup = "1111" ;
            int colonneAJouer = 0;
            int ligneAJouer = 0;
            boolean coupPossibleJouer = false;
            while ( !validationFormatCoup(coup) || !coupPossibleJouer  ){
                coup = Affichage.demanderCoup(etatCourant);
                if (coup.length() ==2){
                    ligneAJouer =  Character.getNumericValue(coup.charAt(0))-1 ;
                    colonneAJouer  = Character.getNumericValue(coup.charAt(1))-1;
                    if (etatCourant.coupPossible(ligneAJouer, colonneAJouer)){
                        coupPossibleJouer = true;
                    }
                }
            }
            etatCourant.jouerCoup(ligneAJouer, colonneAJouer);
            etatCourant = etatCourant.successeur();
            etatCourant.verificationEtatFinal();
            System.out.println("ça à marché " + coup);
        }
        else{
            System.out.println("Il faut passer un tour");
            etatCourant = etatCourant.successeur();
        }
    }

    public boolean validationFormatCoup(String coup ){
        return coup.matches("\\d\\d") && coup.length()==2    ;
    }

}