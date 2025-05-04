package othello.plateau;

public class Case {
    private boolean pionNoir ;
    private boolean pionBlanc ;

    public Case(){
        pionNoir = false;
        pionBlanc = false;
    }

    public void setPionNoir(){
        if (pionBlanc) pionBlanc = false;
        pionNoir = true;
    }

    public void setPionBlanc(){
        if (pionNoir) pionNoir = false;
        pionBlanc = true;
    }


    public boolean isPionNoir() {
        return pionNoir;
    }

    public boolean isPionBlanc() {
        return pionBlanc;
    }
}
