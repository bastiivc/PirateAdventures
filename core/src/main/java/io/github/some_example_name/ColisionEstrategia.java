package io.github.some_example_name;

/**
 * Interfaz para representar estrategias de colisión en el juego.
 *
 * <p>Esta interfaz define un contrato para manejar las acciones específicas que deben
 * ejecutarse cuando ocurre una colisión entre un objeto y el jugador {@code Pirate}.
 * Las clases que implementen esta interfaz deben definir la lógica de colisión
 * para distintos tipos de objetos, como otorgar puntos, dañar al jugador, o
 * desencadenar algún efecto especial.</p>
 *
 * <p>El uso de esta interfaz permite aplicar el patrón de diseño Estrategia,
 * promoviendo la flexibilidad y reusabilidad en la gestión de colisiones.</p>
 */
public interface ColisionEstrategia {

    /**
     * Método que ejecuta la acción correspondiente cuando ocurre una colisión.
     *
     * <p>Este método será llamado cuando un objeto que implementa una estrategia
     * de colisión interactúe con el objeto {@code Pirate}. La acción específica
     * que se ejecutará depende de la implementación de esta interfaz.</p>
     *
     * @param pirate El objeto {@code Pirate} involucrado en la colisión.
     *               Este parámetro permite que la estrategia interactúe
     *               directamente con el estado del jugador.
     */
    void ejecutarAccion(Pirate pirate);
}
