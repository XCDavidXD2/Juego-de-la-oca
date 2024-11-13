package App;

import Models.Tablero;
import Juego.Juego;

public class Main {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        Juego juego = new Juego(tablero);
        juego.iniciar();
    }
}
