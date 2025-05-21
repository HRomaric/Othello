package othello.fx;

import javafx.scene.layout.GridPane;
import othello.plateau.Plateau;

import java.util.Observer;

public class PlateauGraphique extends GridPane  implements Observateur{
    private Plateau plateau;
    private Bouton[][] cases;
    private int dim = 8;
    private int k = 0;

    public PlateauGraphique(Plateau plateau) {
        super();
        this.plateau = plateau;
        cases = new Bouton[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Bouton b = new Bouton(i, j);
                b.setOnAction(new EcouteurBouton(b, this));
                cases[i][j] = b;
                cases[i][j].setPrefSize(65, 65);
                this.setConstraints(cases[i][j], j, i);
                this.getChildren().add(cases[i][j]);
            }
        }
    }

    public int getK(){
        return k++;
    }

    @Override
    public void reagir() {

    }
}