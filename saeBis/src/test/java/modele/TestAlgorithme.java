package modele;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class TestAlgorithme implements ConstantesCanvas {

    private ApprentiOrdonnateur apprentiOrdonnateur;
    private List<Temple> temples;
    private List<Cristal> cristaux;
    private Algorithme algorithme;

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

        algorithme = new Algorithme(apprentiOrdonnateur, cristaux, temples);
    }

    @Test
    void testTriHeuristique() {
        // Déplacer les cristaux hors de leurs temples initiaux
        cristaux.get(0).setPositionCristal(new Position(5, 5));
        cristaux.get(1).setPositionCristal(new Position(15, 15));
        cristaux.get(2).setPositionCristal(new Position(25, 25));

        // Appliquer le tri heuristique
        algorithme.triHeuristique();

        // Vérifier que les cristaux sont sur leurs temples correspondants
        assertEquals(temples.get(0).getPositionTemple().getAbscisse(), cristaux.get(0).getPositionCristal().getAbscisse());
        assertEquals(temples.get(0).getPositionTemple().getOrdonnee(), cristaux.get(0).getPositionCristal().getOrdonnee());

        assertEquals(temples.get(1).getPositionTemple().getAbscisse(), cristaux.get(1).getPositionCristal().getAbscisse());
        assertEquals(temples.get(1).getPositionTemple().getOrdonnee(), cristaux.get(1).getPositionCristal().getOrdonnee());

        assertEquals(temples.get(2).getPositionTemple().getAbscisse(), cristaux.get(2).getPositionCristal().getAbscisse());
        assertEquals(temples.get(2).getPositionTemple().getOrdonnee(), cristaux.get(2).getPositionCristal().getOrdonnee());
    }

    @Test
    void testTriSelectionSansDeplacement() {
        // Désordonner les cristaux
        apprentiOrdonnateur.setCristaux(Arrays.asList(cristaux.get(2), cristaux.get(0), cristaux.get(1)));

        // Appliquer le tri par sélection sans déplacement
        algorithme.triSelectionSansDeplacement();

        // Vérifier l'ordre des cristaux après tri
        List<Cristal> cristauxTries = apprentiOrdonnateur.getCristaux();
        assertEquals(cristaux.get(0), cristauxTries.get(0));
        assertEquals(cristaux.get(1), cristauxTries.get(1));
        assertEquals(cristaux.get(2), cristauxTries.get(2));
    }
}
