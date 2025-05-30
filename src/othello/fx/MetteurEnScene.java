package othello.fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import othello.Jeu;
import othello.joueur.Joueur;
import othello.joueur.JoueurHumain;
import othello.joueur.JoueurIA;
import othello.plateau.SujetObserver;

import java.io.IOException;

public class MetteurEnScene implements Scene {
    private Scene scene;
    private Jeu jeu;
    private Stage stage;
    private Joueur j1;
    private Joueur j2;

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
            this.jeu = new Jeu((Scene) this, new JoueurHumain(false), new JoueurHumain(true));
            afficherPartie();
        });

        Button b2 = new Button("JcIA");
        b2.setOnAction(e -> { choixIA1(); });

        Button b3 = new Button("IA vs IA");
        b3.setOnAction(e -> { choixIA2(); });

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
        resetJoueur();
        stage.setTitle("Othello");
        BorderPane root = new BorderPane();

        HBox texte = new HBox();
        Texte t1 = new Texte("Score Joueur Blanc : ", 0, (SujetObserver) jeu);
        Texte t2 = new Texte("Score Joueur Noire : ", 1, (SujetObserver) jeu);
        Texte t3 = new Texte("Joueur qui joue : ", 2, (SujetObserver) jeu);

        texte.getChildren().addAll(t1, t3, t2);
        texte.setSpacing(50);
        texte.setAlignment(Pos.CENTER);

        GridPane grid = new PlateauGraphique((SujetObserver) jeu);
        grid.setAlignment(Pos.CENTER);

        HBox bouton = new HBox();
        bouton.setAlignment(Pos.CENTER);
        bouton.setSpacing(10);

        Button recommencer = new Button("Commencer nouvelle partie");
        recommencer.setOnAction(e -> {
            this.jeu = new Jeu((Scene) this, new JoueurHumain(false), new JoueurHumain(true));
            afficherPartie();
        });

        Button menu = new Button("Retour menu selection");
        menu.setOnAction(e -> {
            afficherMenu();
        });

        bouton.getChildren().addAll(recommencer, menu);


        root.setTop(texte);
        root.setCenter(grid);
        root.setBottom(bouton);
        root.setMargin(texte, new Insets(25,0,25,0));
        root.setMargin(bouton, new Insets(25,0,25,0));

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
        dialog.setContentText("En fermant cette boite de dialogue vous allez retourner sur le menu de sélection");
        dialog.showAndWait();
        afficherMenu();
    }




    @Override
    public void afficherPasseTour() {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Il faut passer le tour");
        dialog.setHeaderText("Joueur " + jeu.getJoueurQuiJoue() + ", vous ne pouvez plus jouer de coup pour ce tour, le tour passe au joueur adverse");
        dialog.setContentText("En fermant cette boite de dialogue vous allez retourner au jeu");
        dialog.showAndWait();
    }




    public void choixIA1(){
        VBox root = new VBox();

        VBox v = new VBox();
        Label l = new Label("Choix de statégie de l'IA");
        Label l2 = new Label("Stratégie 1 : cherche à avoir le plus de pion possible");
        Label l3 = new Label("Stratéfie 2 : cherche à prendre les cases qui ont\n                      le plus d'importances");
        v.getChildren().addAll(l, l2, l3);
        v.setMargin(v, new Insets(25,25,25,25));
        v.setSpacing(10);

        HBox hb = new HBox();
        Button strat1 = new Button("Strat 1");
        strat1.setOnAction(e -> {
            jeu = new Jeu((Scene) this, new JoueurHumain(false), new JoueurIA(true, 0));
            afficherPartie();
        });

        Button strat2 = new Button("Strat 2");
        strat2.setOnAction(e -> {
            jeu = new Jeu((Scene) this, new JoueurHumain(false), new JoueurIA(true, 1));
            afficherPartie();
        });

        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(30);
        hb.setMargin(hb, new Insets(50,0, 0,0));

        HBox hb2= new HBox();
        Button retour = new Button("Retour Menu");
        retour.setOnAction(e -> {afficherMenu();});
        hb2.setAlignment(Pos.CENTER);
        hb2.setPadding(new Insets(35,0,0,0));

        hb2.getChildren().add(retour);
        hb.getChildren().addAll(strat1, strat2);
        root.getChildren().addAll(v, hb, hb2);

        javafx.scene.Scene scene = new javafx.scene.Scene(root, 400,250);
        stage.setScene(scene);
        stage.show();
    }




    public void choixIA2(){
        VBox root = new VBox();

        VBox v = new VBox();
        Label l = new Label("Choix de statégie de l'IA");
        Label l2 = new Label("Stratégie 1 : cherche à avoir le plus de pion possible");
        Label l3 = new Label("Stratéfie 2 : cherche à prendre les cases qui ont\n                      le plus d'importances");
        v.getChildren().addAll(l, l2, l3);
        v.setMargin(v, new Insets(25,25,25,25));
        v.setSpacing(10);

        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);

        VBox vb1 = new VBox();
        Label ia1 = new Label("IA 1");
        HBox hb1 = new HBox();
        Button strat1 = new Button("Strat 1");
        strat1.setOnAction(e -> {
            j1 = new JoueurIA(false, 0);
            if (j1 != null && j2 != null) {
                this.jeu = new Jeu((Scene) this, j1, j2);
                afficherPartie();
            }
        });

        Button strat2 = new Button("Strat 2");
        strat2.setOnAction(e -> {
            j1 = new JoueurIA(false, 1);
            if (j1 != null && j2 != null) {
                this.jeu = new Jeu((Scene) this, j1, j2);
                afficherPartie();
            }
        });
        hb1.getChildren().addAll(ia1, strat1, strat2);
        hb1.setSpacing(10);
        vb1.getChildren().addAll(ia1, hb1);
        vb1.setSpacing(10);
        vb1.setAlignment(Pos.CENTER);

        VBox vb2 = new VBox();
        Label ia2 = new Label("IA 2");
        HBox hb2 = new HBox();
        Button strat12 = new Button("Strat 1");
        strat12.setOnAction(e -> {
            j2 = new JoueurIA(true, 0);
            if (j1 != null && j2 != null) {
                this.jeu = new Jeu((Scene) this, j1, j2);
                afficherPartie();
            }
        });

        Button strat22 = new Button("Strat 2");
        strat22.setOnAction(e -> {
            j2 = new JoueurIA(true,  1);
            if (j1 != null && j2 != null) {
                this.jeu = new Jeu((Scene) this, j1, j2);
                afficherPartie();
            }
        });

        hb2.getChildren().addAll(strat12, strat22);
        hb2.setSpacing(10);
        vb2.getChildren().addAll(ia2, hb2);
        vb2.setSpacing(10);
        vb2.setAlignment(Pos.CENTER);

        hb.getChildren().addAll(vb1, vb2);
        hb.setSpacing(25);

        HBox bRetour = new HBox();
        Button retour = new Button("Retour Menu");
        retour.setOnAction(e -> {afficherMenu();});
        bRetour.getChildren().addAll(retour);
        bRetour.setAlignment(Pos.CENTER);
        bRetour.setMargin(retour, new Insets(30,0,0,0));

        root.getChildren().addAll(v, hb, bRetour);
        javafx.scene.Scene scene = new javafx.scene.Scene(root, 400,300);
        stage.setScene(scene);
        stage.show();
    }

    public  void resetJoueur(){
        j1 = null;
        j2 = null;
    }
}