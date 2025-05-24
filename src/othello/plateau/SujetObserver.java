package othello.plateau;

import othello.fx.Observateur;

import java.util.ArrayList;

public class SujetObserver {
    private ArrayList<Observateur> observateurs;
    private ArrayList<Observateur> observateurs2;

    /**
     * Constructeur de SujetObserve
     */
    public SujetObserver(){
        observateurs = new ArrayList<>();
        observateurs2 = new ArrayList<>();
    }

    /**
     * Permet d'ajouter des observateurs dans la collection
     * @param v le nouvel observateur
     */
    public void ajouterObservateur(Observateur v) {
        observateurs.add(v);
    }

    public void ajouterObservateur2(Observateur v) {
        observateurs2.add(v);
    }

    /**
     * Permet d'informer les vues du changement du monde
     */
    public void notifierObservateur() {
        for (Observateur v : observateurs) {
            v.reagir();
        }
    }

    public void notifierObservateur2() {
        for (Observateur v : observateurs2) {
            v.reagir();
        }
    }
}
