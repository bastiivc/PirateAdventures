package io.github.some_example_name;

/**
 * Interfaz que define el comportamiento de destrucción para los objetos del juego.
 * Las clases que implementen esta interfaz deben proporcionar la lógica específica
 * para liberar recursos y realizar tareas de limpieza.
 */

public interface Destruible
{

    /**
     * Libera los recursos asociados al objeto y realiza tareas de limpieza.
     */

    void destruir();
}
