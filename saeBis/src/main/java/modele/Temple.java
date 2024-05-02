package modele;

import javafx.scene.paint.Color;
import modele.Position;

public class Temple {
    private final Position positionTemple;
    private int couleurTemple;
    private boolean booleanCrystal;
    private int couleurCrystal;


    public Temple(Position parPosition, int parCouleurTemple,  boolean parBooleanCrystal, int parCouleurCrystal){
        positionTemple = parPosition;
        couleurTemple = parCouleurTemple;
        booleanCrystal = parBooleanCrystal;
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

    public boolean isBooleanCrystal() {
        return booleanCrystal;
    }

    public void setBooleanCrystal(boolean booleanCrystal) {
        this.booleanCrystal = booleanCrystal;
    }

    public int getCouleurCrystal() {
        return couleurCrystal;
    }

    public void setCouleurCrystal(int couleurCrystal) {
        this.couleurCrystal = couleurCrystal;
    }
}
