package modele;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TempleTest {

    private Temple temple;
    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(10, 20);
        temple = new Temple(position, 1);
    }

    @Test
    void testGetCouleurTemple() {
        assertEquals(1, temple.getCouleurTemple());
    }

    @Test
    void testSetCouleurTemple() {
        //Valueur positive
        temple.setCouleurTemple(2);
        assertEquals(2, temple.getCouleurTemple());

        //Valeur nagative
        temple.setCouleurTemple(-1);
        assertEquals(-1, temple.getCouleurTemple());

        //Valeur 0
        temple.setCouleurTemple(0);
        assertEquals(0, temple.getCouleurTemple());
    }

    @Test
    void testGetPositionTemple() {
        assertEquals(position, temple.getPositionTemple());
    }

    @Test
    void testToString() {
        assertEquals("Position : -6, 4 1", temple.toString());

        temple.setCouleurTemple(2);
        assertEquals("Position : -6, 4 2", temple.toString());

    }
     */
}
