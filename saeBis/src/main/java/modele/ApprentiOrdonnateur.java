package modele;

import java.util.Collection;
import java.util.List;
@SuppressWarnings("ResultOfMethodCallIgnored")
public class ApprentiOrdonnateur implements ConstantesCanvas {
    private Position positionApprenti;
    private List<Temple> temples;
    private List<Cristal> cristaux;
    private int cristalPorte =-1;
    private boolean echangeDispo = true;

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

    public void echangeCristaux(Cristal cristal) {
        if (echangeDispo) {
            if (cristal.getPositionCristal().equals(positionApprenti)) {
                cristalPorte = cristaux.indexOf(cristal);
                echangeDispo = false;
            }
        }
    }

    public void lacherCristal() {
        for (Cristal cristal : cristaux ) {
            if (cristal.getPositionCristal().equals(positionApprenti)) {
                return;
            }
            cristalPorte = -1;
        }
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
        if ( cristalPorte == -1) {
            return null;
        }
        System.out.println(cristalPorte);
        return cristaux.get(cristalPorte) ;
    }

    public boolean isEchangeDispo() {
        return echangeDispo;
    }

    public void setEchangeDispo() {
        echangeDispo = true;
    }
}
