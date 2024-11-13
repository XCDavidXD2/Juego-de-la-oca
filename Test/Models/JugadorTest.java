package Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JugadorTest {

    @org.junit.Test
    @Test
    public void testAvanzarPositivo() {
        Jugador jugador = new Jugador(Color.RED);
        jugador.avanzar(3);
        assertEquals(4, jugador.getPosicion(), "El jugador debería estar en la posición 4");
    }

    @org.junit.Test

    @Test
    public void testAvanzarMultiple() {
        Jugador jugador = new Jugador(Color.RED);
        jugador.avanzar(3);
        jugador.avanzar(2);
        assertEquals(6, jugador.getPosicion(), "El jugador debería estar en la posición 6");
    }
    @org.junit.Test

    @Test
    public void testNoAvanzar() {
        Jugador jugador = new Jugador(Color.GREEN);
        jugador.avanzar(0);
        assertEquals(1, jugador.getPosicion(), "El jugador debería estar en la posición 1");
    }
    @org.junit.Test

    @Test
    public void testRetroceder() {
        Jugador jugador = new Jugador(Color.GREEN);
        jugador.avanzar(-2);
        assertEquals(-1, jugador.getPosicion(), "El jugador debería estar en la posición -1");
    }
}
