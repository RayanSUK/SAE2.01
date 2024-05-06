package modele;

public class Temple {
    private final Position positionTemple;
    private int couleurTemple;
    private int couleurCrystal;


    public Temple(Position parPosition, int parCouleurTemple, int parCouleurCrystal){
        positionTemple = parPosition;
        couleurTemple = parCouleurTemple;
        couleurCrystal = parCouleurCrystal;

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


    public int getCouleurCrystal() {
        return couleurCrystal;
    }

    public void setCouleurCrystal(int couleurCrystal) {
        this.couleurCrystal = couleurCrystal;
    }
}
