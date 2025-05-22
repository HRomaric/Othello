package othello;

import othello.exceptions.ExceptionCoup;
import othello.fx.Scene;
import othello.outils.Affichage;
import othello.plateau.Etat;
import othello.plateau.Plateau;
import othello.plateau.SujetObserver;

public class Jeu extends SujetObserver {
    private Etat etatCourant ;
    private Scene scene ;

    public Jeu(){
        etatCourant = new Etat(); // initialisation du Jeu
        partie(); //pour le jeu dans le terminal
    }

    public Jeu(Scene scene){
        etatCourant = new Etat();
        this.scene = scene;
        Affichage.afficher(etatCourant);
    }

    public void partie(){
        while (!etatCourant.estEtatFinal()){
            Affichage.afficher(etatCourant);
            this.jouer(); //Partie sur terminal
        }
        System.out.println("Félicitation joueurs"+ etatCourant.getJoueurGagnant()  + " vous avez gagné !");
        Affichage.fermerDemandeurCoup();
    }

    public void regarderFinPartie(){
        etatCourant.verificationEtatFinal();
        if (etatCourant.estEtatFinal()){
            setFinPartie();
        }
    }

    public void setFinPartie(){
        scene.afficherFinPartie();
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
            while (!validationFormatCoup(coup) || !coupPossibleJouer  ){
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


    public void jouerIG(int x, int y){
        boolean coupPossible = false;
        for (int ligne = 0 ; ligne<8 && !coupPossible ; ligne++){
            for (int colonne = 0 ; colonne<8 ; colonne++){
                if (etatCourant.coupPossible(ligne, colonne)){
                    coupPossible = true;
                    break;
                }
            }
        }
        if (coupPossible){
            boolean coupPossibleJouer = false;
            if (etatCourant.coupPossible(x, y)){
                coupPossibleJouer = true;
                etatCourant.jouerCoup(x, y);
                etatCourant = etatCourant.successeur();
                etatCourant.verificationEtatFinal();
                System.out.println("ça à marché " + x + y);
                this.notifierObservateur();
                Affichage.afficher(etatCourant);
                etatCourant.getPlateau().majNbPiont();
                notifierObservateur();
            }
            else {
                try {
                    throw new ExceptionCoup();
                } catch (ExceptionCoup e) {
                    e.afficherMsg();
                }
            }
        }
        else{
            System.out.println("Il faut passer un tour");
            scene.afficherPasseTour();
            etatCourant = etatCourant.successeur();
        }
        regarderFinPartie();
    }

    public Etat getEtatCourant(){
        return etatCourant;
    }

    public Plateau getPlateau(){
        return etatCourant.getPlateau();
    }

    public String getJoueurQuiJoue(){
        return etatCourant.quiEstLeJoueurCourant();
    }
}