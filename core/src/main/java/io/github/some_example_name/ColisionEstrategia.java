package io.github.some_example_name;

/**
 * Interfaz para representar estrategias de colisión.
 */
public interface ColisionEstrategia {

    /**
     * Método que ejecuta la acción correspondiente cuando ocurre una colisión.
     *
     * @param pirate El objeto Pirate involucrado en la colisión.
     */
    void ejecutarAccion(Pirate pirate);
}
