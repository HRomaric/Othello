import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import othello.fx.MetteurEnScene;
import othello.fx.PlateauGraphique;
import othello.fx.Texte;
import othello.plateau.Plateau;
import othello.Jeu;
import othello.plateau.SujetObserver;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MetteurEnScene metteurEnScene = new MetteurEnScene(stage);
    }
}