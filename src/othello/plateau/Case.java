package othello.plateau;

public class Case {
    private boolean pionNoir; //dit si sur la case il y a un pion blanc
    private boolean pionBlanc; //dit si sur la case il y a un pion noir


    /**
     * Cobstructeur de Case
     * Par défault ni noir ni blanc, "la case est vide"
     */
    public Case(){
        pionNoir = false;
        pionBlanc = false;
    }



    /**
     * Getteur - dit si le pion est noir ou pas
     * @return
     */
    public boolean isPionNoir() {
        return pionNoir;
    }

    /**
     * Getteur dit si le pion est blanc ou pas
     * @return
     */
    public boolean isPionBlanc() {
        return pionBlanc;
    }



    /**
     * Setteur
     * La case prend un pion noir
     */
    public void setPionNoir(){
        pionBlanc = false;
        pionNoir = true;
    }

    /**
     * Setteur
     * La case prend un pion blanc
     */
    public void setPionBlanc(){
        pionNoir = false;
        pionBlanc = true;
    }
}
