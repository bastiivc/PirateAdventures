package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Interfaz que define el comportamiento de dibujado para los objetos del juego.
 *
 * <p>Esta interfaz establece un contrato para las clases que necesitan ser renderizadas
 * en la pantalla del juego. Cualquier clase que implemente esta interfaz debe proporcionar
 * su propia implementación del método {@code dibujar}, utilizando un {@link SpriteBatch}
 * para realizar las operaciones de renderizado.</p>
 *
 * <p>El uso de esta interfaz asegura que los objetos gráficos sean consistentes y
 * reutilizables dentro del contexto de renderizado del juego.</p>
 */
public interface Dibujable {

    /**
     * Dibuja el objeto utilizando el {@link SpriteBatch} proporcionado.
     *
     * <p>Este método debe ser implementado por las clases que requieren ser renderizadas
     * en la pantalla. Se espera que el objeto utilice el {@code SpriteBatch} para dibujar
     * texturas, sprites o cualquier elemento gráfico asociado al objeto.</p>
     *
     * <p>Recomendaciones de uso:</p>
     * <ul>
     *     <li>Invoca este método dentro del ciclo de renderizado del juego, después de
     *     haber iniciado el {@code SpriteBatch} con {@code begin()} y antes de llamar
     *     a {@code end()}.</li>
     *     <li>Evita cambiar las configuraciones globales del {@code SpriteBatch}, como
     *     matrices de proyección o mezclado, dentro de este método.</li>
     * </ul>
     *
     * @param batch El {@link SpriteBatch} utilizado para realizar las operaciones de dibujo.
     *              Este parámetro no debe ser nulo y debe estar inicializado antes de su uso.
     */
    void dibujar(SpriteBatch batch);
}
