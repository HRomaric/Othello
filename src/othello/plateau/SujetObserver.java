package othello.plateau;

import othello.fx.Observateur;

import java.util.ArrayList;

public class SujetObserver {
    private ArrayList<Observateur> observateurs;

    /**
     * Constructeur de SujetObserve
     */
    public SujetObserver(){
        observateurs = new ArrayList<>();
    }

    /**
     * Permet d'ajouter des observateurs dans la collection
     * @param v le nouvel observateur
     */
    public void ajouterObservateur(Observateur v) {
        observateurs.add(v);
    }

    /**
     * Permet d'informer les vues du changement du monde
     */
    public void notifierObservateur() {
        for (Observateur v : observateurs) {
            v.reagir();
        }
    }
}
