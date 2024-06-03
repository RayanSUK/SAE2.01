package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import modele.Algorithme;
import modele.Cristal;
import modele.LectureScenario;
import modele.Temple;
import vue.VBoxCanva;
import vue.VBoxRoot;
import java.util.List;
import java.io.File;
import java.util.Collection;
/** Cette classe permet à la vue et au modèle d'intéragir ensemble
 * et de gérer les actions de l'utilisateur sur l'interface.
 * Il permet également de lire les différents scénarios
 */


public class Controleur implements EventHandler {

    /**
     * La méthode handler.
     * Gère les évènements déclenchés par les interactions de l'utilisateur.
     * Elle réagit :
     * - aux scénarios
     * - aux actions des boutons
     * @param event, l'évènement déclenché.
     */
    public void handle(Event event) {

        // Si l'utilisateur clique sur le menu de scénarios
        if (event.getSource()instanceof MenuItem) {
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

        // Si l'utilisateur clique sur le bouton échange cristaux
        if (event.getSource()instanceof Button) {
            if (((Button) event.getSource()).getText().equals("Echanger cristaux")) {
                VBoxRoot.getCanvas().echangeGraphique();
            }
            if (((Button) event.getSource()).getText().equals("Déposer cristal")){
                VBoxRoot.getCanvas().supprimerCristalPorter();
            }
            if (((Button) event.getSource()).getText().equals("tri par selection")){
                Algorithme algo = new Algorithme(VBoxRoot.getApprenti(), VBoxRoot.getApprenti().getCristaux(), VBoxRoot.getApprenti().getTemples());
                algo.triHeuristique();
            }
        }

    }




}
