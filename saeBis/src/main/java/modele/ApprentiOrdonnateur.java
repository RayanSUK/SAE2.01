package modele;

import java.util.Collection;
import java.util.List;
public class ApprentiOrdonnateur implements ConstantesCanvas {
    private Position positionApprenti;
    private List<Temple> temples;
    private List<Cristal> cristaux;
    private int cristalPorte =-1;
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
        //if (!(cristalPorte == -1)) {
        //    for (int i = 0; i< cristaux.size(); i++) {
        //        if (cristaux.get(i) == cristal) {
        //            cristalPorte = i;
        //        }
        //        break;
        //    }
        //}
        //else {
        //    cristaux.set(cristalPorte, cristal);

        //}
        for (int i = 0; i < cristaux.size(); i++) {
            if (cristaux.get(i) == cristal) {
                cristalPorte = i;
            }
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
        return cristaux.get(cristalPorte);
    }
}
