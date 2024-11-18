package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * La clase {@code Espada} representa un objeto en forma de espada que cae y puede interactuar con otros objetos en el juego.
 *
 * <p>La espada es una subclase de {@link ObjetoLluvia} y está diseñada para otorgar puntos al jugador cuando colisiona
 * con el objeto controlado por el jugador. Esta clase utiliza la estrategia {@link OtorgarPuntos} para definir el comportamiento
 * al colisionar.</p>
 *
 * <p>Características principales:</p>
 * <ul>
 *     <li>Es un objeto visual que cae desde la parte superior de la pantalla.</li>
 *     <li>Otorga 20 puntos al jugador al ser recogida.</li>
 *     <li>Utiliza el sistema de estrategias para manejar su interacción en el juego.</li>
 * </ul>
 */
public class Espada extends ObjetoLluvia {

    /**
     * Constructor que inicializa una instancia de {@code Espada} con la textura proporcionada.
     *
     * <p>Este constructor asigna la estrategia {@link OtorgarPuntos} con un valor de 20 puntos,
     * lo que significa que cada espada recogida sumará 20 puntos al marcador del jugador.</p>
     *
     * @param textura La textura gráfica utilizada para representar la espada en pantalla.
     *                Debe ser una instancia válida de {@link Texture}.
     */
    public Espada(Texture textura) {
        super(textura, new OtorgarPuntos(20)); // Asignamos la estrategia de otorgar 20 puntos
    }

    /**
     * Actualiza el movimiento de la espada en función de la velocidad proporcionada.
     *
     * <p>El movimiento se calcula en función del tiempo transcurrido desde el último cuadro,
     * utilizando el método {@code Gdx.graphics.getDeltaTime()}. Esto asegura que la espada
     * se mueva de manera uniforme independientemente de la velocidad de fotogramas del dispositivo.</p>
     *
     * @param velocidad La velocidad a la que la espada debe moverse hacia abajo en la pantalla.
     *                  Generalmente se obtiene del administrador de juego o de las reglas de dificultad.
     */
    @Override
    public void actualizarMovimiento(float velocidad) {
        float deltaY = velocidad * Gdx.graphics.getDeltaTime();
        setPosition(getX(), getY() - deltaY);
    }
}
