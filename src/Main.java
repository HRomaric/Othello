import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import othello.fx.PlateauGraphique;
import othello.plateau.Plateau;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Othello");
        BorderPane root = new BorderPane();
        Label label1 = new Label("Score J1 : 0");
        Label label2 = new Label("Score J2 : 0");
        GridPane grid = new PlateauGraphique(new Plateau());
        root.setTop(label1);
        root.setCenter(grid);
        root.setBottom(label2);
        Scene scene = new Scene(root, 800,800);
        stage.setScene(scene);
        stage.show();
    }
}