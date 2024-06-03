package modele;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class ApprentiOrdonnateurTest implements ConstantesCanvas {

    private ApprentiOrdonnateur apprentiOrdonnateur;
    private List<Temple> temples;
    private List<Cristal> cristaux;

    @BeforeEach
    void setUp() {
        // Initialiser les positions, temples et cristaux pour les tests
        Position position1 = new Position(0, 0);
        Position position2 = new Position(10, 10);
        Position position3 = new Position(20, 20);

        Temple temple1 = new Temple(position1, 1);
        Temple temple2 = new Temple(position2, 2);
        Temple temple3 = new Temple(position3, 3);

        Cristal cristal1 = new Cristal(position1, 1);
        Cristal cristal2 = new Cristal(position2, 2);
        Cristal cristal3 = new Cristal(position3, 3);

        temples = Arrays.asList(temple1, temple2, temple3);
        cristaux = Arrays.asList(cristal1, cristal2, cristal3);

        apprentiOrdonnateur = new ApprentiOrdonnateur();
        apprentiOrdonnateur.setTemples(temples);
        apprentiOrdonnateur.setCristaux(cristaux);
    }

    @Test
    void testEchangeCristaux() {
        // Apprenti sur la position du premier cristal
        apprentiOrdonnateur.setPositionApprenti(new Position(0, 0));

        // Echange de cristaux
        apprentiOrdonnateur.echangeCristaux(cristaux.get(0));

        // Vérifie si l'apprenti porte le cristal
        assertEquals(cristaux.get(0), apprentiOrdonnateur.getCristalPorte());

        // Echange indisponible après échange
        assertFalse(apprentiOrdonnateur.isEchangeDispo());
    }

    @Test
    void testLacherCristal() {
        // Apprenti sur la position du premier cristal
        apprentiOrdonnateur.setPositionApprenti(new Position(0, 0));

        // Echange de cristaux
        apprentiOrdonnateur.echangeCristaux(cristaux.get(0));
        assertFalse(apprentiOrdonnateur.isEchangeDispo());

        // Déplacer l'apprenti sur un temple
        apprentiOrdonnateur.setPositionApprenti(temples.get(0).getPositionTemple());

        // Déposer le cristal
        apprentiOrdonnateur.lacherCristal();

        // Vérifie que l'apprenti ne porte plus de cristal
        assertNull(apprentiOrdonnateur.getCristalPorte());

        // Echange disponible après dépôt
        apprentiOrdonnateur.setEchangeDispo();
        assertTrue(apprentiOrdonnateur.isEchangeDispo());
    }

    @Test
    void testTempleAssocie() {
        // Vérifie l'association correcte des temples
        assertEquals(0, apprentiOrdonnateur.templeAssocie(cristaux.get(0)));
        assertEquals(1, apprentiOrdonnateur.templeAssocie(cristaux.get(1)));
        assertEquals(2, apprentiOrdonnateur.templeAssocie(cristaux.get(2)));
    }

    @Test
    void testVictoire() {
        // Déplacer les cristaux sur leurs temples correspondants
        cristaux.get(0).setPositionCristal(temples.get(0).getPositionTemple());
        cristaux.get(1).setPositionCristal(temples.get(1).getPositionTemple());
        cristaux.get(2).setPositionCristal(temples.get(2).getPositionTemple());

        // Vérifie la condition de victoire
        assertTrue(apprentiOrdonnateur.victoire());
    }

    /**
     *

    @Test
    void testTriSelectionSansDeplacement() {
        // Désordonner les cristaux
        apprentiOrdonnateur.setCristaux(Arrays.asList(cristaux.get(2), cristaux.get(0), cristaux.get(1)));

        // Tri des cristaux
        apprentiOrdonnateur.triSelectionSansDeplacement();

        // Vérifie l'ordre des cristaux après tri
        List<Cristal> cristauxTries = apprentiOrdonnateur.getCristaux();
        assertEquals(cristaux.get(0), cristauxTries.get(0));
        assertEquals(cristaux.get(1), cristauxTries.get(1));
        assertEquals(cristaux.get(2), cristauxTries.get(2));
    }
     */
}
