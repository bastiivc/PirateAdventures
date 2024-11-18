package io.github.some_example_name;

/**
 * Interfaz que define el comportamiento de actualización para los objetos del juego.
 * Los objetos que implementen esta interfaz deben proporcionar una implementación
 * del método {@code actualizarMovimiento}, que se encargará de actualizar la posición
 * o estado del objeto en función de una velocidad proporcionada.
 *
 * <p>Esta interfaz se utiliza principalmente para objetos que requieren un movimiento
 * dinámico en el juego, como elementos que caen o personajes móviles.</p>
 */
public interface Actualizable {

    /**
     * Actualiza el movimiento del objeto en función de la velocidad proporcionada.
     *
     * <p>Este método debe ser implementado por los objetos que necesitan moverse
     * continuamente, ajustando su posición o estado según la velocidad definida.</p>
     *
     * @param velocidad la velocidad a la que el objeto debe moverse.
     *                  Generalmente, se calcula como la distancia recorrida por unidad de tiempo.
     */
    void actualizarMovimiento(float velocidad);
}
