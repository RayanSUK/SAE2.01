package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import modele.Cristal;
import modele.LectureScenario;
import modele.Temple;
import vue.VBoxGauche;
import java.util.List;
import java.io.File;

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
            if (((MenuItem) event.getSource()).getText().equals("Algorithme Heuristique")) {
                try {
                    VBoxGauche.getCanvas().triHeuristiqueAvecAffichage();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("La liste des cristaux : " + VBoxGauche.getApprenti().getCristaux().toString());
                System.out.println("La liste des temples :" + VBoxGauche.getApprenti().getTemples().toString());
            }

            Object userData = ((MenuItem)event.getSource()).getUserData();
            if (userData instanceof File) { //l'utilisateur a choisi un scénario
                File fichierScenario = (File) userData;
                System.out.println(fichierScenario.getName());
                File scenario = fichierScenario;
                List<Temple> temples = LectureScenario.lecture(fichierScenario).getKey();
                List<Cristal> cristaux = LectureScenario.lecture(fichierScenario).getValue();
                VBoxGauche.getCanvas().effacerCanva();
                VBoxGauche.getApprenti().setTemples(temples);
                VBoxGauche.getCanvas().setTemples(temples);
                VBoxGauche.getApprenti().setCristaux(cristaux);
                VBoxGauche.getCanvas().setCristaux(cristaux);
                System.out.println(VBoxGauche.getApprenti());
            }
        }

        // Si l'utilisateur clique sur les boutons
        if (event.getSource()instanceof Button) {
            if (((Button) event.getSource()).getText().equals("Échanger les cristaux")) {
                System.out.println("Bouton echange test");
                VBoxGauche.getCanvas().echangeGraphique();
            }
            if (((Button) event.getSource()).getText().equals("Déposer cristal")){
                System.out.println("bouton déposer");
                VBoxGauche.getApprenti().lacherCristal();
                VBoxGauche.getCanvas().supprimerCristalPorter();
            }

            if (((Button) event.getSource()).getText().equals("Réinitialiser")) {
                VBoxGauche.getCanvas().recommencerPartie();
                VBoxGauche.getApprenti().recommencerPartie();

            }

        }

    }




}
