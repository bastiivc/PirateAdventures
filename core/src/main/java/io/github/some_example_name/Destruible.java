package io.github.some_example_name;

/**
 * Interfaz que define el comportamiento de destrucción para los objetos del juego.
 *
 * <p>Las clases que implementen esta interfaz deben proporcionar una lógica específica
 * para liberar recursos, realizar tareas de limpieza y garantizar que los objetos
 * se eliminen correctamente de la memoria o del contexto del juego.</p>
 *
 * <p>Esta interfaz es útil para garantizar que los recursos como texturas, sonidos
 * o cualquier otro componente cargado durante el ciclo de vida del objeto sean
 * liberados adecuadamente, evitando posibles fugas de memoria.</p>
 */
public interface Destruible {

    /**
     * Método que libera los recursos asociados al objeto y realiza tareas de limpieza.
     *
     * <p>Este método debe ser implementado para cerrar, eliminar o liberar cualquier
     * recurso utilizado por la instancia que implemente esta interfaz, como texturas,
     * sonidos, bases de datos, etc.</p>
     *
     * <p>Por ejemplo:</p>
     * <ul>
     *     <li>En objetos gráficos, se deben liberar texturas o {@code SpriteBatch}.</li>
     *     <li>En objetos con sonido, se deben detener y liberar {@code Sound} o {@code Music}.</li>
     * </ul>
     *
     * <p>Se recomienda invocar este método antes de eliminar el objeto o cuando ya no
     * sea necesario para evitar fugas de memoria.</p>
     */
    void destruir();
}
