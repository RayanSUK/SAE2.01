package modele;

public class Temple {
    private final Position positionTemple;
    private int couleurTemple;


    public Temple(Position parPosition, int parCouleurTemple) {
        positionTemple = parPosition;
        couleurTemple = parCouleurTemple;

    }

    //création des accesseurs et des setteurs
    public int getCouleurTemple() {
        return couleurTemple;
    }

    public void setCouleurTemple(int couleurTemple) {
        this.couleurTemple = couleurTemple;
    }

    public Position getPositionTemple() {
        return positionTemple;
    }
}





