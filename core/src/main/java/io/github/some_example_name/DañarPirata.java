package io.github.some_example_name;

/**
 * Clase que representa la estrategia de dañar al jugador {@code Pirate}.
 *
 * <p>Esta clase implementa la interfaz {@link ColisionEstrategia}, definiendo
 * una acción específica a ejecutar cuando ocurre una colisión: restar una vida
 * al jugador. Es útil para objetos peligrosos en el juego, como enemigos o
 * trampas que deben penalizar al jugador.</p>
 *
 * <p>El uso de esta clase permite aplicar el patrón de diseño Estrategia,
 * encapsulando el comportamiento de colisión de los objetos que dañan al
 * jugador de forma modular y reutilizable.</p>
 */
public class DañarPirata implements ColisionEstrategia {

    /**
     * Ejecuta la acción de daño al jugador {@code Pirate}.
     *
     * <p>Cuando se llama a este método, se invoca el método {@code dañar()} del
     * objeto {@code Pirate}, lo que reduce las vidas del jugador.</p>
     *
     * @param pirate El objeto {@code Pirate} involucrado en la colisión.
     *               Este parámetro permite modificar directamente el estado
     *               del jugador, como reducir sus vidas.
     */
    @Override
    public void ejecutarAccion(Pirate pirate) {
        pirate.dañar(); // Aplica daño al pirata
    }
}
