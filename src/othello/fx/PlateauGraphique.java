package othello.fx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import othello.Jeu;
import othello.plateau.Case;
import othello.plateau.Plateau;
import othello.plateau.SujetObserver;

public class PlateauGraphique extends GridPane  implements Observateur{
    private Plateau plateau;
    private Bouton[][] cases;
    private int dim = 8;
    private int k = 0;
    private SujetObserver sujetObserver;
    private Image image1 = new Image(getClass().getResourceAsStream("/images/pion_noir.png"), 40,40, true, true);
    private Image image2 = new Image(getClass().getResourceAsStream("/images/pion_blanc.png"), 40,40, true, true);

    public PlateauGraphique(SujetObserver jeu) {
        super();
        sujetObserver = jeu;
        sujetObserver.ajouterObservateur(this);
        reagir();
    }

    public int getK(){
        return k++;
    }

    @Override
    public void reagir() {
        Jeu leJeu = (Jeu) sujetObserver;
        this.plateau = leJeu.getPlateau();
        cases = new Bouton[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Bouton b = new Bouton(i, j);
                cases[i][j] = b;
                cases[i][j].setPrefSize(65, 65);
                setConstraints(cases[i][j], j, i);
                this.getChildren().add(cases[i][j]);

                Case c = plateau.recupererCase(i, j);
                if (!c.estVide()) {
                    if (c.isPionBlanc()){
                        b.setGraphic(new ImageView(image2));
                    }
                    if (c.isPionNoir()){
                        b.setGraphic(new ImageView(image1));
                    }
                }
                if (leJeu.getJoueurCourant().estHumain()){
                    b.setOnAction(e -> leJeu.getJoueurCourant().jouerHumain(b.getX(), b.getY()));
                } else {
                    b.setOnAction(null);
                }
            }
        }
    }
}