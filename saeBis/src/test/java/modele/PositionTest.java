package modele;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PositionTest {

    private Position position1;
    private Position position2;

    private Position position3;

    @BeforeEach
    void setUp() {
        position1 = new Position(10, 20);
        position2 = new Position(15, 25);
    }

    @Test
    void testDeplacementUneCase() {

        //test avec valeur positive
        position1.deplacementUneCase(position2);
        assertEquals(11, position1.getAbscisse());
        assertEquals(20, position1.getOrdonnee());

        position1.deplacementUneCase(position2);
        assertEquals(12, position1.getAbscisse());
        assertEquals(20, position1.getOrdonnee());

        position1.setPosition(10, 20);
        position1.deplacementUneCase(new Position(5, 15));
        assertEquals(9, position1.getAbscisse());
        assertEquals(20, position1.getOrdonnee());

        position1.setPosition(10, 20);
        position1.deplacementUneCase(new Position(10, 25));
        assertEquals(10, position1.getAbscisse());
        assertEquals(21, position1.getOrdonnee());

        //assertEquals(4, position1.getNombreDePas());

        // Test avec Valeur négative
        position1.setPosition(-5,-5);
        position1.deplacementUneCase(new Position(-10,-10));
        assertEquals(-6,position1.getAbscisse());
        assertEquals(-5,position1.getOrdonnee());


        //Test avec 0
        position1.setPosition(0,0);
        position2.setPosition(0,0);
        position1.deplacementUneCase(position2);
        assertEquals(0,position1.getAbscisse());
        assertEquals(0,position2.getOrdonnee());
    }

    @Test
    void testDeplacementCristal() {

        //Valeur positive
        position1.deplacementCristal(position2);
        assertEquals(11, position1.getAbscisse());
        assertEquals(20, position1.getOrdonnee());

        position1.deplacementCristal(position2);
        assertEquals(12, position1.getAbscisse());
        assertEquals(20, position1.getOrdonnee());

        position1.setPosition(10, 20);
        position1.deplacementCristal(new Position(5, 15));
        assertEquals(9, position1.getAbscisse());
        assertEquals(20, position1.getOrdonnee());

        position1.setPosition(10, 20);
        position1.deplacementCristal(new Position(10, 25));
        assertEquals(10, position1.getAbscisse());
        assertEquals(21, position1.getOrdonnee());

        //valeur négative
        position1.setPosition(-5,-5);
        position1.deplacementCristal(new Position(-10,-10));
        assertEquals(-6,position1.getAbscisse());
        assertEquals(-5,position1.getOrdonnee());

        position1.deplacementCristal(new Position(-6,-10));
        assertEquals(-6,position1.getAbscisse());
        assertEquals(-6,position1.getOrdonnee());

        //Valeur avec 0
        position1.setPosition(0,0);
        position1.deplacementCristal(new Position(0,0));
        assertEquals(0,position1.getAbscisse());
        assertEquals(0,position1.getOrdonnee());


    }

    @Test
    void testEquals() {
        assertTrue(position1.equals(new Position(10, 20)));
        assertFalse(position1.equals(position2));
        assertFalse(position1.equals(new Position(0, 0)));
        assertFalse(position1.equals(new Position(-10, -20)));
    }

    @Test
    void testGetNombreDePas() {
        Position.resetNombreDePas();
        Position pos5 = new Position(0, 0);
        Position pos6 = new Position(1, 1);
        pos5.deplacementUneCase(pos6);
        pos5.deplacementUneCase(pos6);
        assertEquals(2, pos5.getNombreDePas());


        //On reset le nombre de pas
        Position.resetNombreDePas();

        Position pos3 = new Position(-1,-1);
        Position pos4 = new Position(0,0);
        pos3.deplacementUneCase(pos4);
        pos3.deplacementUneCase(pos4);
        assertEquals(2,pos3.getNombreDePas());
    }
     */

    @Test
    void testSetPosition() {

        //valeur positive
        position1.setPosition(30, 40);
        assertEquals(30, position1.getAbscisse());
        assertEquals(40, position1.getOrdonnee());

        //valeur négative
        position1.setPosition(-10, -20);
        assertEquals(-10, position1.getAbscisse());
        assertEquals(-20, position1.getOrdonnee());

        //valeur 0
        position1.setPosition(0, 0);
        assertEquals(0, position1.getAbscisse());
        assertEquals(0, position1.getOrdonnee());

    }

    @Test
    void testToString() {
        Position pos = new Position(26, 36);
        assertEquals("Position : 10, 20", pos.toString());
    }
    */
}