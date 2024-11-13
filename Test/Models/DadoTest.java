package Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DadoTest {

    @Test
    public void testDistribucionDado() {
        int[] contador = new int[6];
        for (int i = 0; i < 6000; i++) {
            int resultado = Dado.tirar();
            contador[resultado - 1]++;
        }
        for (int i = 0; i < 6; i++) {
            assertTrue(contador[i] > 900 && contador[i] < 1100, "La distribución de los valores del dado debería ser uniforme");
        }
    }
}