package io.github.some_example_name;

/**
 * Clase que representa la estrategia de dañar al jugador.
 */
public class DañarPirata implements ColisionEstrategia {

    @Override
    public void ejecutarAccion(Pirate pirate) {
        pirate.dañar(); // Aplica daño al pirata
    }
}
