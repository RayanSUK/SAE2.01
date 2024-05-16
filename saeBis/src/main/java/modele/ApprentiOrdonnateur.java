package modele;

import java.util.Collection;
import java.util.List;

public class ApprentiOrdonnateur implements ConstantesCanvas {
    private Position positionApprenti;

    private List<Temple> temples;

    private List<Cristal> crystaux;

    private boolean crystalPorte;

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

    public Position getPosition() {
        return positionApprenti;
    }

    public void setPositionApprenti(Position positionApprenti) {
        this.positionApprenti = positionApprenti;
    }

    public List<Cristal> getCrystaux() {
        return crystaux;
    }

    public void setCrystaux(List<Cristal> crystaux) {
        this.crystaux = crystaux;
    }
}
