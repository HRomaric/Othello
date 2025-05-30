package othello;

import othello.exceptions.ExceptionCoup;
import othello.fx.Scene;
import othello.joueur.*;
import othello.outils.Affichage;
import othello.outils.Algo;
import othello.outils.ThreadsManager;
import othello.plateau.Etat;
import othello.plateau.Plateau;
import othello.plateau.SujetObserver;

public class Jeu extends SujetObserver{
    private Etat etatCourant ;
    private Scene scene ;

    public Jeu(){
        etatCourant = new Etat(new JoueurHumain(false), new JoueurHumain(false)); // initialisation du Jeu
        partie(); //pour le jeu dans le terminal
    }

    public Jeu(Scene scene, Joueur j1, Joueur j2){
        etatCourant = new Etat(j1, j2);
        this.scene = scene;
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nNouvelle Partie\n");
        Affichage.afficher(etatCourant);

        j1.ajouterJeu(this);
        j2.ajouterJeu(this);

        j1.verifTour();
    }

    public void partie(){
        while (!etatCourant.estEtatFinal()){
            Affichage.afficher(etatCourant);
            //this.jouer(); //Partie sur terminal
        }
        System.out.println("Félicitation joueurs"+ etatCourant.getJoueurGagnant()  + " vous avez gagné !");
        Affichage.fermerDemandeurCoup();
    }

    public boolean regarderFinPartie(){
        etatCourant.verificationEtatFinal();
        if (etatCourant.estEtatFinal()){
            return true;
        }
        else{
            return false;
        }
    }

    public void setFinPartie(){
        scene.afficherFinPartie();
        ThreadsManager.getInstance().detruireTout();
    }


    public boolean coupPossible(){
        boolean coupPossible = false;
        for (int ligne = 0 ; ligne<8 && !coupPossible ; ligne++){
            for (int colonne = 0 ; colonne<8 ; colonne++){
                if (etatCourant.coupPossible(ligne, colonne)){
                    coupPossible = true;
                    break;
                }
            }
        }
        return coupPossible;
    }

/*
    public void jouer(){
        if  (coupPossible()){
            String coup = "1111" ;
            int colonneAJouer = 0;
            int ligneAJouer = 0;
            boolean coupPossibleJouer = false;
            while (!validationFormatCoup(coup) || !coupPossibleJouer  ){
                coup = Affichage.demanderCoup(etatCourant);
                if (coup.length() ==2){
                    ligneAJouer = Character.getNumericValue(coup.charAt(0))-1 ;
                    colonneAJouer = Character.getNumericValue(coup.charAt(1))-1;
                    if (etatCourant.coupPossible(ligneAJouer, colonneAJouer)){
                        coupPossibleJouer = true;
                    }
                }
            }
            etatCourant.jouerCoup(ligneAJouer, colonneAJouer);
            etatCourant = etatCourant.successeur(ligneAJouer, colonneAJouer);
            etatCourant.verificationEtatFinal();
            System.out.println("ça à marché " + coup);
        }
        else{
            System.out.println("Il faut passer un tour");
            etatCourant = etatCourant.successeur(etatCourant.getDerniereLigneDernierCoup(), etatCourant.getDerniereColonneDernierCoup());
        }
    }

    public boolean validationFormatCoup(String coup ){
        return coup.matches("\\d\\d") && coup.length()==2    ;
    }
*/

    public void jouerIG(int x, int y){
        Plateau copiePlateau = new Plateau(etatCourant.getPlateau());

        Etat nouvelEtat = new Etat(copiePlateau, etatCourant.isJoueurCourant(), etatCourant.getJoueurNoir(), etatCourant.getJoueurBlanc(), x, y);

        nouvelEtat.jouerCoup(x, y);

        // 4. Remplacer l’état courant par le nouveau
        etatCourant = nouvelEtat;
        if (regarderFinPartie()){
            setFinPartie();
        }
        else {
            System.out.println("ça à marché " + x + y);
            Affichage.afficher(etatCourant);
            notifierObservateur();
            getJoueurCourant().verifTour();
        }
    }


    public Scene getScene() {
        return scene;
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

    public Joueur getJoueurCourant(){
        return etatCourant.getJoueurCourant();
    }

    public void passerTour(){
        scene.afficherPasseTour();
        etatCourant = etatCourant.passerTour();
        notifierObservateur();
        if (!etatCourant.getJoueurCourant().estHumain()){
            etatCourant.getJoueurCourant().verifTour();
        }
    }

    public void setEtatCourant(Etat etatCourant) {
        this.etatCourant = etatCourant;
    }

    public void verifierCoupExistant(){
        if (!coupPossible()){
            passerTour();
        }

    }
}