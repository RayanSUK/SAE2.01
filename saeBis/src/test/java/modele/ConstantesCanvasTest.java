package modele;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.scene.paint.Color;

class ConstantesCanvasTest {

    @Test
    void testConstantesCanvas() {
        //messages servent en cas d'erreur
        // Vérification des dimensions de la grille
        assertEquals(640, ConstantesCanvas.LARGEUR_CANVAS, "La largeur du canvas devrait être 640");
        assertEquals(640, ConstantesCanvas.HAUTEUR_CANVAS, "La hauteur du canvas devrait être 640");
        assertEquals(20, ConstantesCanvas.CARRE, "La taille d'un carré devrait être 20");

        // Vérification des couleurs
        assertEquals(Color.BLACK, ConstantesCanvas.COULEUR_GRILLE, "La couleur de la grille devrait être noire");
        assertEquals(Color.PURPLE, ConstantesCanvas.COULEUR_APPRENTI, "La couleur de l'apprenti devrait être violette");
        assertEquals(Color.RED, ConstantesCanvas.COULEUR_CLIQUE, "La couleur pour un clic devrait être rouge");

        // Vérification des couleurs des temples
        Color[] expectedTempleColors = {
                Color.BLUE, Color.YELLOW, Color.GREEN, Color.ORANGE, Color.FUCHSIA,
                Color.BROWN, Color.SKYBLUE, Color.SIENNA, Color.PINK, Color.LEMONCHIFFON, Color.SEAGREEN
        };
        assertArrayEquals(expectedTempleColors, ConstantesCanvas.COULEURS_TEMPLES, "Les couleurs des temples ne correspondent pas");

        // Vérification des dimensions des ovales
        assertEquals(19, ConstantesCanvas.LARGEUR_OVALE, "La largeur des ovales devrait être 19");
        assertEquals(19, ConstantesCanvas.HAUTEUR_OVALE, "La hauteur des ovales devrait être 19");

        // Vérification des noms des options de menu pour le choix des scénarios
        String[] expectedMenuScenarios = {"Choix du scénario"};
        assertArrayEquals(expectedMenuScenarios, ConstantesCanvas.INTITULE_MENU_SCENARIOS, "Les intitulés des scénarios ne correspondent pas");
    }
}
