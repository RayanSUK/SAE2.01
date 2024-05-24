package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import modele.Cristal;
import modele.LectureScenario;
import modele.Temple;
import vue.VBoxCanva;
import vue.VBoxRoot;
import java.util.List;
import java.io.File;
import java.util.Collection;
/** Le controleur fait le lien entre le modele et la vue*/
public class Controleur implements EventHandler {
    public void handle(Event event) {
        Object userData = ((MenuItem)event.getSource()).getUserData();
        if (userData instanceof File) { //l'utilisateur a choisi un scénario
            File fichierScenario = (File) userData;
            System.out.println(fichierScenario.getName());
            File scenario = fichierScenario;
            List<Temple> temples = LectureScenario.lecture(fichierScenario).getKey();
            List<Cristal> cristaux = LectureScenario.lecture(fichierScenario).getValue();
            VBoxRoot.getCanvas().effacerCanva();
            VBoxRoot.getApprenti().setTemples(temples);
            VBoxRoot.getCanvas().setTemples(temples);
            VBoxRoot.getApprenti().setCristaux(cristaux);
            VBoxRoot.getCanvas().setCristaux(cristaux);
            System.out.println(VBoxRoot.getApprenti());




        }
    }


}
