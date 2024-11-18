package io.github.some_example_name;

/**
 * Clase que implementa la estrategia de colisión para quitar una vida al jugador.
 * Esta clase forma parte del patrón Strategy, en el cual las acciones específicas
 * de colisión se delegan a clases concretas que implementan la interfaz {@link ColisionEstrategia}.
 */
public class QuitarVida implements ColisionEstrategia {

    /**
     * Ejecuta la acción de colisión que resta una vida al jugador.
     *
     * @param pirate El objeto {@link Pirate} involucrado en la colisión.
     */
    @Override
    public void ejecutarAccion(Pirate pirate) {
        pirate.dañar(); // Resta una vida al jugador
    }
}
