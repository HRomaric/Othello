package othello.fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import othello.Jeu;
import othello.plateau.SujetObserver;

import java.util.Iterator;

public class MetteurEnScene implements Scene {
    private Scene scene;
    private Jeu jeu;
    private Stage stage;

    public MetteurEnScene(Stage stage) {
        this.stage = stage;
        afficherMenu();
    }

    public void afficherMenu() {
        stage.setTitle("Othello");
        VBox root = new VBox();
        Label label1 = new Label("Bienvenu sur Othello !");
        Label label2 = new Label("Veulliez selectionnez un mode de jeu");
        root.getChildren().addAll(label1, label2);
        root.setAlignment(Pos.CENTER);

        HBox bouton = new HBox();

        Button b = new Button("JcJ");
        b.setOnAction(e ->
        {
            this.jeu = new Jeu((Scene) this);
            afficherPartie();
        });

        Button b2 = new Button("JcIA");
        b2.setOnAction(e -> {});

        Button b3 = new Button("IA vs IA");
        b3.setOnAction(e -> {});

        bouton.setAlignment(Pos.CENTER);
        bouton.getChildren().addAll(b, b2, b3);
        root.getChildren().add(bouton);
        root.setPadding(new Insets(10, 50, 50, 50));
        root.setSpacing(10);
        javafx.scene.Scene scene = new javafx.scene.Scene(root, 325,150);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void afficherPartie() {
        stage.setTitle("Othello");
        BorderPane root = new BorderPane();

        HBox texte = new HBox();
        Texte t1 = new Texte("Score Joueur Blanc : ", 0, (SujetObserver) jeu);
        Texte t2 = new Texte("Score Joueur Noire : ", 1, (SujetObserver) jeu);
        Texte t3 = new Texte("Joueur qui joue : ", 2, (SujetObserver) jeu);

        texte.getChildren().addAll(t1, t3, t2);
        texte.setSpacing(50);
        texte.setAlignment(Pos.CENTER);
        root.setMargin(texte, new Insets(25,0,0,0));
        root.setTop(texte);

        GridPane grid = new PlateauGraphique((SujetObserver) jeu);
        grid.setAlignment(Pos.CENTER);
        root.setCenter(grid);


        javafx.scene.Scene scene = new javafx.scene.Scene(root, 600, 625);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void afficherFinPartie() {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("La partie est fini");

        int nbPiontBlanc = jeu.getEtatCourant().getPlateau().getNbPiontBlanc();
        int nbPiontNoir = jeu.getEtatCourant().getPlateau().getNbPiontNoir();

        if (nbPiontBlanc == nbPiontNoir) {
            dialog.setHeaderText("Égalité les deux joueurs ont le même nombre de pion");
        } else if (nbPiontBlanc > nbPiontNoir) {
            dialog.setHeaderText("Le joueur Blanc à gagné de " + nbPiontBlanc + " à " + nbPiontNoir + " !");
        }
        else{
            dialog.setHeaderText("Le joueur Noire à gagné de " + nbPiontNoir + " à " + nbPiontBlanc + " !");
        }
        dialog.setContentText("Vous allez retourner sur le menu de sélection");
        dialog.showAndWait();dialog.showAndWait();
        afficherMenu();
    }
}