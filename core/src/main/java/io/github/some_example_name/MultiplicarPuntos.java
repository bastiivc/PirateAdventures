package io.github.some_example_name;

/**
 * Clase que representa una estrategia de colisión que multiplica los puntos del jugador.
 * Implementa la interfaz {@link ColisionEstrategia} y aplica un multiplicador a los puntos actuales
 * del jugador al producirse una colisión.
 */
public class MultiplicarPuntos implements ColisionEstrategia {
    private int factor;

    /**
     * Constructor de la clase {@code MultiplicarPuntos}.
     *
     * @param factor El multiplicador que se aplicará a los puntos del jugador.
     */
    public MultiplicarPuntos(int factor) {
        this.factor = factor;
    }

    /**
     * Ejecuta la acción correspondiente a la estrategia de multiplicar puntos.
     * Multiplica los puntos actuales del jugador por el factor especificado menos uno.
     *
     * @param pirate El objeto {@link Pirate} involucrado en la colisión.
     */
    @Override
    public void ejecutarAccion(Pirate pirate) {
        pirate.sumarPuntos(pirate.getPuntos() * (factor - 1)); // Multiplica los puntos actuales
    }
}
