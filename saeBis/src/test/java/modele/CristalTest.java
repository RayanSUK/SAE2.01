package modele;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CristalTest {

    private Cristal cristal;
    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(10, 20);
        cristal = new Cristal(position, 1);
    }

    @Test
    void testGetCouleurCristal() {
        assertEquals(1, cristal.getCouleurCristal());
    }

    @Test
    void testSetCouleurCristal() {
        //Valeur positive
        cristal.setCouleurCristal(2);
        assertEquals(2, cristal.getCouleurCristal());

        //Valeur négative
        cristal.setCouleurCristal(-1);
        assertEquals(-1, cristal.getCouleurCristal());

        //Valeur 0
        cristal.setCouleurCristal(0);
        assertEquals(0, cristal.getCouleurCristal());
    }

    @Test
    void testGetPositionCristal() {
        assertEquals(position, cristal.getPositionCristal());
    }

    @Test
    void testSetPositionCristal() {
        //Valeur positive
        Position positivePosition = new Position(30, 40);
        cristal.setPositionCristal(positivePosition);
        assertEquals(positivePosition, cristal.getPositionCristal());

        //Valeur négative
        Position negativePosition = new Position(-10, -20);
        cristal.setPositionCristal(negativePosition);
        assertEquals(negativePosition, cristal.getPositionCristal());

        //Valeur 0
        Position zeroPosition = new Position(0, 0);
        cristal.setPositionCristal(zeroPosition);
        assertEquals(zeroPosition, cristal.getPositionCristal());
    }

    @Test
    void testToString() {
        assertEquals("Position : -6, 4 1", cristal.toString());

        cristal.setCouleurCristal(2);
        assertEquals("Position : -6, 4 2", cristal.toString());

    }
}
