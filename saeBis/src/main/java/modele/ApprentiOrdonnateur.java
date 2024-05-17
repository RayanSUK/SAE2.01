package modele;

import javafx.geometry.Pos;

import java.util.Collection;
import java.util.List;

public class ApprentiOrdonnateur implements ConstantesCanvas {
    private Position positionApprenti;

    private List<Temple> temples;

    private List<Cristal> cristaux;

    private final Cristal [] cristalPorte = new Cristal[1];

    public ApprentiOrdonnateur(){
        // Position initiale du joueur
        positionApprenti = new Position(LARGEUR_CANVAS/(CARRE*2),HAUTEUR_CANVAS/(CARRE*2) );

    }


    public Collection<Temple> getTemples() {
        return temples;
    }

    public void setTemples(List<Temple> temples) {
        this.temples = temples;
    }

    public Cristal echangeCristaux(Cristal cristal) {
        if (positionApprenti.equals(cristal.getPositionCristal())) {
            if (!(cristalPorte[0] == null)) {
                Position positionCristal = cristalPorte[0].getPositionCristal();
                int couleurCristal = cristal.getCouleurCristal();
                Cristal cristal2 = new Cristal(positionCristal, couleurCristal);
                cristalPorte[0] = cristal;
                cristal = cristal2;
            } else {
                cristalPorte[0] = cristal;

            }
            return cristalPorte[0];
        }
        return null;
    }

    public Position getPosition() {
        return positionApprenti;
    }

    public void setPositionApprenti(Position positionApprenti) {
        this.positionApprenti = positionApprenti;
    }

    public List<Cristal> getCristaux() {
        return cristaux;
    }

    public void setCristaux(List<Cristal> cristaux) {
        this.cristaux = cristaux;
    }

    public Cristal[] getCristalPorte() {
        return cristalPorte;
    }
}
