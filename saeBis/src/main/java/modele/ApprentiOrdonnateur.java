package modele;

import java.util.Collection;

public class ApprentiOrdonnateur implements ConstantesCanvas {
    private Position positionApprenti;

    private Collection<Temple> temples;

    private boolean crystalPorte;

    public ApprentiOrdonnateur(){
        // Position initiale du joueur
        positionApprenti = new Position(LARGEUR_CANVAS/(CARRE*2),HAUTEUR_CANVAS/(CARRE*2) );
    }


    public Collection<Temple> getTemples() {
        return temples;
    }

    public void setTemples(Collection<Temple> temples) {
        this.temples = temples;
    }

    public Position getPosition() {
        return positionApprenti;
    }

    public void setPositionApprenti(Position positionApprenti) {
        this.positionApprenti = positionApprenti;
    }

}
