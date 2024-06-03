package modele;

import static modele.ConstantesCanvas.*;
import static org.junit.jupiter.api.Assertions.*;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File; //pour les tests de fichier
import java.io.FileWriter; //Pour les scénarios
import java.io.IOException;
import java.util.List;

class LectureScenarioTest {

    // Pour le fichier temporaire
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        //Instancie un fichier temporaire
        tempFile = File.createTempFile("scenario", ".txt");

        //FileWriter pour des scénarios dans le fichier temporaire
        FileWriter writer = new FileWriter(tempFile);
        writer.write("0 0 1 1\n"); // position (0,0), couleur temple 1, couleur cristal 1
        writer.write("10 10 2 2\n");
        writer.write("-5 -5 3 3\n");
        writer.write("0 0 0 0\n");
        writer.close();
    }

    @Test
    void testLectureScenario() {
        // Appelle la méthode lecture pour lire les données du fichier temporaire
        Pair<List<Temple>, List<Cristal>> result = LectureScenario.lecture(tempFile);

        // Récupère les listes de temples et de cristaux
        List<Temple> temples = result.getKey();
        List<Cristal> cristaux = result.getValue();

        // Vérifie que les listes contiennent le bon nombre d'éléments
        assertEquals(4, temples.size());
        assertEquals(4, cristaux.size());

        // Vérification des valeurs des temples
        assertEquals(0 + LARGEUR_CANVAS / (2 * CARRE), temples.get(0).getPositionTemple().getAbscisse());
        assertEquals(0 + HAUTEUR_CANVAS / (2 * CARRE), temples.get(0).getPositionTemple().getOrdonnee());
        assertEquals(1, temples.get(0).getCouleurTemple());

        assertEquals(10 + LARGEUR_CANVAS / (2 * CARRE), temples.get(1).getPositionTemple().getAbscisse());
        assertEquals(10 + HAUTEUR_CANVAS / (2 * CARRE), temples.get(1).getPositionTemple().getOrdonnee());
        assertEquals(2, temples.get(1).getCouleurTemple());

        assertEquals(-5 + LARGEUR_CANVAS / (2 * CARRE), temples.get(2).getPositionTemple().getAbscisse());
        assertEquals(-5 + HAUTEUR_CANVAS / (2 * CARRE), temples.get(2).getPositionTemple().getOrdonnee());
        assertEquals(3, temples.get(2).getCouleurTemple());

        assertEquals(0 + LARGEUR_CANVAS / (2 * CARRE), temples.get(3).getPositionTemple().getAbscisse());
        assertEquals(0 + HAUTEUR_CANVAS / (2 * CARRE), temples.get(3).getPositionTemple().getOrdonnee());
        assertEquals(0, temples.get(3).getCouleurTemple());

        // Vérification des valeurs des cristaux
        assertEquals(0 + LARGEUR_CANVAS / (2 * CARRE), cristaux.get(0).getPositionCristal().getAbscisse());
        assertEquals(0 + HAUTEUR_CANVAS / (2 * CARRE), cristaux.get(0).getPositionCristal().getOrdonnee());
        assertEquals(1, cristaux.get(0).getCouleurCristal());

        assertEquals(10 + LARGEUR_CANVAS / (2 * CARRE), cristaux.get(1).getPositionCristal().getAbscisse());
        assertEquals(10 + HAUTEUR_CANVAS / (2 * CARRE), cristaux.get(1).getPositionCristal().getOrdonnee());
        assertEquals(2, cristaux.get(1).getCouleurCristal());

        assertEquals(-5 + LARGEUR_CANVAS / (2 * CARRE), cristaux.get(2).getPositionCristal().getAbscisse());
        assertEquals(-5 + HAUTEUR_CANVAS / (2 * CARRE), cristaux.get(2).getPositionCristal().getOrdonnee());
        assertEquals(3, cristaux.get(2).getCouleurCristal());

        assertEquals(0 + LARGEUR_CANVAS / (2 * CARRE), cristaux.get(3).getPositionCristal().getAbscisse());
        assertEquals(0 + HAUTEUR_CANVAS / (2 * CARRE), cristaux.get(3).getPositionCristal().getOrdonnee());
        assertEquals(0, cristaux.get(3).getCouleurCristal());
    }
}