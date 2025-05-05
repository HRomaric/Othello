package othello.plateau;

public class Case {
    private boolean pionNoir ;
    private boolean pionBlanc ;

    public Case(){
        pionNoir = false;
        pionBlanc = false;
    }

    public void setPionNoir(){
        pionBlanc = false;
        pionNoir = true;
    }

    public void setPionBlanc(){
        pionNoir = false;
        pionBlanc = true;
    }


    public boolean isPionNoir() {
        return pionNoir;
    }

    public boolean isPionBlanc() {
        return pionBlanc;
    }

    public boolean estVide(){
        return !pionBlanc&& !pionNoir ;
    }



}
