package othello.exceptions;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;

public class PasserTour extends RuntimeException {
    private String message;

    public PasserTour() {
        super();
        StringBuilder MessageErreur = new StringBuilder();
        MessageErreur.append("\n\n").append("Vous devez fermez cette boite de dialogue pour au jeu");
        this.message = MessageErreur.toString();
    }

    public void afficherMsg(){
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle("ERREUR COUP");
        dialog.setHeaderText("Vous n'avez plus de coup possible,\nvous allez passez votre tour");
        dialog.setContentText(message);
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> dialog.close());
        pause.play();
        dialog.showAndWait();
    }

    public void afficherMsg2(){
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle("ERREUR COUP");
        dialog.setHeaderText("L'IA n'a plus de coup possible,\nelle passe son tour");
        dialog.setContentText(message);
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> dialog.close());
        pause.play();
        dialog.showAndWait();
    }
}
