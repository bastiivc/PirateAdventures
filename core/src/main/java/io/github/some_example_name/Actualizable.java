package io.github.some_example_name;

/**
 * Interfaz que define el comportamiento de actualización para los objetos del juego.
 */

public interface Actualizable {

    /**
     * Actualiza el movimiento del objeto en función de la velocidad proporcionada.
     *
     * @param velocidad la velocidad a la que el objeto debe moverse.
     */

    void actualizarMovimiento(float velocidad);
}
