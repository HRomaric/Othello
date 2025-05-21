package othello.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import othello.exceptions.ExceptionCoup;

public class EcouteurBouton implements EventHandler<ActionEvent> {
    private Bouton bouton;
    private PlateauGraphique p;
    private Image image1 = new Image(getClass().getResourceAsStream("/images/pion_noir.png"), 40,40, true, true);
    private Image image2 = new Image(getClass().getResourceAsStream("/images/pion_blanc.png"), 40,40, true, true);

    public EcouteurBouton(Bouton bouton, PlateauGraphique plateauGraphique) {
        this.bouton = bouton;
        p = plateauGraphique;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        //Etat e = ...FaireCoup()
        //if (e.coupPossible()){
        if(true){
            //if (e.quiJoue()){
            if(p.getK()%2==0){
                bouton.setGraphic(new ImageView(image1));
            }
            if (p.getK()==3) {
                try {
                    throw new ExceptionCoup();
                } catch (ExceptionCoup e) {
                    e.afficherMsg();
                }
            } else {
                bouton.setGraphic(new ImageView(image2));
            }
        }
        else {
            try {
                throw new ExceptionCoup();
            } catch (ExceptionCoup e) {
                e.afficherMsg();
            }
        }

    }
}
