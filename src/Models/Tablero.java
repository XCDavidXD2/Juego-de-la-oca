package Models;

import java.util.ArrayList;
import java.util.Arrays;

public class Tablero {
    private static final int TAMANO_TABLERO = 63;
    private ArrayList<Character> tablero;

    public Tablero() {
        inicializarTablero();
    }

    private void inicializarTablero() {
        tablero = new ArrayList<>(TAMANO_TABLERO);
        for (int i = 0; i < TAMANO_TABLERO; i++) {
            tablero.add(' ');
        }
    }

    public void imprimirTablero(ArrayList<Jugador> jugadores) {
        System.out.println("Tablero:");
        for (int i = 0; i < TAMANO_TABLERO; i++) {
            boolean fichaPresente = false;
            for (Jugador jugador : jugadores) {
                if (jugador.getPosicion() == i + 1) {
                    System.out.print("| " + jugador.getColor().getCode() + "●" + Color.RESET.getCode() + " ");
                    fichaPresente = true;
                    break;
                }
            }
            if (!fichaPresente) {
                System.out.print("|   ");
            }
            if ((i + 1) % 9 == 0) {
                System.out.println("|");
            }
        }
    }

    public String verificarCasillaEspecial(int posicion) {
        switch (posicion) {
            case 10:
            case 20:
            case 30:
            case 40:
            case 50:
                return "oca";
            case 6:
            case 12:
                return "puente";
            case 26:
                return "dados";
            case 53:
                return "dados";
            case 42:
                return "laberinto";
            case 58:
                return "muerte";
            default:
                return null;
        }
    }
}
