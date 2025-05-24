package othello.exceptions;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;

public class ExceptionCoup extends Exception {
    private String message;
    public ExceptionCoup() {
        super();
        StringBuilder MessageErreur = new StringBuilder();
        MessageErreur.append("\n\n").append("Vous devez fermez cette boite de dialogue pour revenir au jeu");
        this.message = MessageErreur.toString();
    }

    public void afficherMsg(){
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle("ERREUR COUP");
        dialog.setHeaderText("Ce coup est impossible !");
        dialog.setContentText(message);
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> dialog.close());
        pause.play();
        dialog.showAndWait();
    }
}