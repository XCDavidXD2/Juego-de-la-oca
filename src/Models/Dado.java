package Models;

import java.util.Random;

public class Dado {
    private static final Random random = new Random();

    // Método para simular el lanzamiento de un dado de 6 caras
    public static int tirar() {
        return random.nextInt(6) + 1;
    }
}
