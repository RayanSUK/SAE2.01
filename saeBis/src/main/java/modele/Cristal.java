package modele;

public class Cristal {
    private Position positioncristal;

    private int couleurcristal;

    public Cristal(Position parPositioncristal, int parCouleurcristal ){
        couleurcristal = parCouleurcristal;
        positioncristal = parPositioncristal;
    }




    public int getCouleurCristal() {
        return couleurcristal;
    }

    public Position getPositionCristal() {
        return positioncristal;
    }

    public void setCouleurCristal(int couleurcristal) {
        this.couleurcristal = couleurcristal;
    }


}

