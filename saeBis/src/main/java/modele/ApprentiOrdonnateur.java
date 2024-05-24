package modele;

import java.util.Collection;
import java.util.List;
public class ApprentiOrdonnateur implements ConstantesCanvas {
    private Position positionApprenti;
    private List<Temple> temples;
    private List<Cristal> cristaux;
    private Cristal cristalPorte;
    public ApprentiOrdonnateur(){
 // Position initiale du joueur
        positionApprenti = new Position(LARGEUR_CANVAS/(CARRE* 2),HAUTEUR_CANVAS/(CARRE*2) );
    }

    public Collection<Temple> getTemples() {
        return temples;
    }
    public void setTemples(List<Temple> temples) {
        this.temples = temples;
    }

    public Cristal echangeCristaux(Cristal cristal) {
        if (!(cristalPorte == null)) {
            Position positionCristal = cristalPorte.getPositionCristal();
            int couleurCristal = cristal.getCouleurCristal();
            Cristal cristal2 = new Cristal(positionCristal, couleurCristal);
            cristalPorte = cristal;
            cristal = cristal2;

        } else {
            cristalPorte = cristal;

        }
        return cristalPorte;
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

    public Cristal getCristalPorte() {
        return cristalPorte;
    }
}
