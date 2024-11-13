package Models;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableroTest {

    @Test
    public void testCasillaOca() {
        Tablero tablero = new Tablero();
        assertEquals("oca", tablero.verificarCasillaEspecial(5), "La casilla 5 debería ser 'oca'");
    }

    @Test
    public void testCasillaPuente() {
        Tablero tablero = new Tablero();
        assertEquals("puente", tablero.verificarCasillaEspecial(6), "La casilla 6 debería ser 'puente'");
    }
    @Test
    public void testCasillaNormal() {
        Tablero tablero = new Tablero();
        assertNull(tablero.verificarCasillaEspecial(2), "La casilla 2 no debería tener efecto especial");
    }

    @Test
    public void testCasillaNormal2() {
        Tablero tablero = new Tablero();
        assertNull(tablero.verificarCasillaEspecial(10), "La casilla 10 no debería tener efecto especial");
    }
}