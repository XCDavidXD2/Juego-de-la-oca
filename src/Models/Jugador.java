package Models;

public class Jugador {
    private Color color;
    private int posicion;

    public Jugador(Color color) {
        this.color = color;
        this.posicion = 1; // Todos los jugadores comienzan en la casilla 1
    }

    public Color getColor() {
        return color;
    }

    public int getPosicion() {
        return posicion;
    }

    public void avanzar(int cantidad) {
        posicion += cantidad;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
