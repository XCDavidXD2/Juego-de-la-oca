package Juego;

import Models.Color;
import Models.Dado;
import Models.Jugador;
import Models.Tablero;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego {
    private ArrayList<Jugador> jugadores;
    private Tablero tablero;

    public Juego(Tablero tablero) {
        this.tablero = tablero;
    }

    public void iniciar() {
        jugadores = selecJugadores();
        if (jugadores == null) {
            System.out.println("Juego cancelado.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    jugar();
                    break;
                case 2:
                    verReglas();
                    break;
                case 3:
                    System.out.println("¡Chao Chao!");
                    break;
                default:
                    System.out.println("Introduce una opción válida del 1 al 3");
            }
        } while (opcion != 3);
    }

    private ArrayList<Jugador> selecJugadores() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el número de jugadores (2-4): ");
        int numJugadores = obtenerEntero();

        if (numJugadores < 2 || numJugadores > 4) {
            System.out.println("Número de jugadores no válido. Debe ser entre 2 y 4.");
            return null;
        }

        String[] colores = {Color.RED.toString(), Color.GREEN.toString(), Color.YELLOW.toString(), Color.BLUE.toString(), Color.RESET.toString()};
        ArrayList<String> coloresJugadores = new ArrayList<>();

        for (int i = 0; i < numJugadores; i++) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("                                                  ");
            System.out.println("║                                                ║");
            System.out.println("       Selecciona un color para el jugador " + (i + 1) + ":     ");
            System.out.println("║                                                ║");
            System.out.println("                                                  ");
            System.out.println("║                [1]" + Color.RED.getCode() + " Rojo" + Color.RESET.getCode() + "                        ║");
            System.out.println("                 [2]" + Color.GREEN.getCode() + " Verde" + Color.RESET.getCode() + "                        ");
            System.out.println("║                [3]" + Color.YELLOW.getCode() + " Amarillo" + Color.RESET.getCode() + "                    ║");
            System.out.println("                 [4]" + Color.BLUE.getCode() + " Azul" + Color.RESET.getCode() + "                         ");
            System.out.println("║                                                ║");
            System.out.print("           ");
            for (int j = 0; j < i; j++) {
                System.out.print(Color.valueOf(coloresJugadores.get(j)).getCode() + "    ●" + Color.RESET.getCode() + " ");
            }
            System.out.println("                                    ");
            System.out.println("║                                                ║");
            System.out.println("               Pulsa [0] para salir               ");
            System.out.println("╚════════════════════════════════════════════════╝");

            int opcionColor = obtenerEntero();
            if (opcionColor == 0) {
                return null;
            }
            if (opcionColor < 1 || opcionColor > 4) {
                System.out.println("Opción no válida. Por favor, elige una opción del 1 al 4.");
                i--;
                continue;
            }
            String colorSeleccionado = colores[opcionColor - 1];
            if (coloresJugadores.contains(colorSeleccionado)) {
                System.out.println("Color repetido. Elige otra opción");
                i--;
                continue;
            }
            coloresJugadores.add(colorSeleccionado);

            if (i == numJugadores - 1) {
                System.out.println("╔════════════════════════════════════════════════╗");
                System.out.println("                                                  ");
                System.out.println("║                  ¡TODO LISTO!                  ║");
                System.out.println("                                                  ");
                System.out.println("║        Ahora toca decidir quién empieza        ║");
                System.out.println("                                                  ");
                System.out.println("║                                                ║");
                System.out.print("           ");
                for (int j = 0; j < coloresJugadores.size(); j++) {
                    System.out.print(Color.valueOf(coloresJugadores.get(j)).getCode() + "    ●" + Color.RESET.getCode() + " ");
                }
                System.out.println("                                    ");
                System.out.println("║                                                ║");
                System.out.println("                                                  ");
                System.out.println("╚════════════════════════════════════════════════╝");
            }
        }

        ArrayList<Jugador> jugadores = new ArrayList<>();
        for (String color : coloresJugadores) {
            jugadores.add(new Jugador(Color.valueOf(color.replace(Color.RESET.getCode().toString(), ""))));
        }

        return jugadores;
    }

    private int obtenerEntero() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Introduce un número entero.");
                scanner.next();
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║                MENÚ DE LA OCA                  ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║                1. JUGAR                        ║");
        System.out.println("║                2. VER REGLAS                   ║");
        System.out.println("║                3. SALIR                        ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.print("Selecciona una opción: ");
    }

    private void jugar() {
        Scanner scanner = new Scanner(System.in);
        int turnoActual = determinarOrden(jugadores.size());

        System.out.println("Empieza el jugador " + (turnoActual + 1) + ".");

        while (true) {
            System.out.println(" ");
            System.out.println("Jugador " + (turnoActual + 1) + ": Presiona Enter para tirar el dado.");
            scanner.nextLine();
            scanner.nextLine();

            int dado = Dado.tirar();
            System.out.println("Has sacado un " + dado + ".");

            Jugador jugadorActual = jugadores.get(turnoActual);
            int nuevaPosicion = jugadorActual.getPosicion() + dado;

            if (nuevaPosicion == 63) {
                System.out.println("¡Felicidades! El jugador " + (turnoActual + 1) + " ha ganado.");
                break;
            } else if (nuevaPosicion > 63) {
                System.out.println("¡Te has pasado! Retrocedes " + (nuevaPosicion - 63) + " casillas");
                nuevaPosicion = 63 - (nuevaPosicion - 63);
            }

            // Verificar casillas especiales
            String casillaEspecial = tablero.verificarCasillaEspecial(nuevaPosicion);
            if (casillaEspecial != null) {
                switch (casillaEspecial) {
                    case "oca":
                        System.out.println("De oca en oca y... ¡Tiro porque me toca!");
                        nuevaPosicion += 10;
                        break;
                    case "puente":
                        System.out.println("Has caído en un puente. Viaja hasta el otro puente.");
                        nuevaPosicion = (nuevaPosicion == 6) ? 12 : 6;
                        break;
                    case "dados":
                        System.out.println("Has caído en los dados. Avanzas o retrocedes según corresponda.");
                        nuevaPosicion = (nuevaPosicion == 26) ? 53 : 26;
                        break;
                    case "laberinto":
                        System.out.println("Has caído en el laberinto. Te perdiste y retrocediste hasta la casilla 30.");
                        nuevaPosicion = 30;
                        break;
                    case "muerte":
                        System.out.println("Has caído en la muerte. Vuelves a la casilla 1.");
                        nuevaPosicion = 1;
                        break;
                }
            }

            jugadorActual.setPosicion(nuevaPosicion);

            // Comprobar si hay jugadores en la misma casilla
            for (Jugador jugador : jugadores) {
                if (jugador != jugadorActual && jugador.getPosicion() == nuevaPosicion) {
                    System.out.println("El jugador " + (jugador.getColor().name()) + " está en la misma casilla. Éste avanza una posición.");
                    jugador.avanzar(1);
                }
            }

            tablero.imprimirTablero(jugadores);

            turnoActual = (turnoActual + 1) % jugadores.size();
        }
    }

    private int determinarOrden(int numJugadores) {
        Scanner scanner = new Scanner(System.in);
        int primerJugador = 0;
        int maximo = 0;

        for (int i = 0; i < numJugadores; i++) {
            System.out.println(" ");
            System.out.println("Jugador " + (i + 1) + ", presiona Enter para tirar el dado.");
            scanner.nextLine();
            scanner.nextLine();
            int resultado = Dado.tirar();
            System.out.println("El jugador " + (i + 1) + " ha sacado un " + resultado + ".");

            if (resultado > maximo) {
                maximo = resultado;
                primerJugador = i;
            }
        }

        System.out.println(" ");
        System.out.println("El jugador " + (primerJugador + 1) + " ha sacado el número más alto: " + maximo);
        return primerJugador;
    }

    private void verReglas() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("╔═══════════════════════════════════════════╗");
            System.out.println("║              Reglas de La Oca             ║");
            System.out.println("║                                           ║");
            System.out.println("║ - El juego se desarrolla en un tablero de ║");
            System.out.println("║   63 casillas.                            ║");
            System.out.println("║ - El objetivo es ser el primer jugador en ║");
            System.out.println("║   llegar a la casilla 63.                 ║");
            System.out.println("║ - Los jugadores avanzan según el número   ║");
            System.out.println("║   obtenido al tirar el dado.              ║");
            System.out.println("║ - Algunas casillas tienen efectos         ║");
            System.out.println("║   especiales, como avanzar o retroceder.  ║");
            System.out.println("║ - Si un jugador supera la casilla 63,     ║");
            System.out.println("║   retrocede tantas casillas como se haya  ║");
            System.out.println("║   pasado.                                 ║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║                1. Volver                  ║");
            System.out.println("╚═══════════════════════════════════════════╝");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
        } while (opcion != 1);
    }
}
