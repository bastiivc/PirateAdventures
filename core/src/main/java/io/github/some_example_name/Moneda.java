package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * La clase Moneda representa un objeto en forma de moneda que cae desde la parte superior de la pantalla
 * y otorga puntos al jugador cuando colisiona con el personaje principal (tarro).
 * Hereda de la clase ObjetoLluvia, que define comportamientos comunes para los objetos que caen.
 */
public class Moneda extends ObjetoLluvia {

    /**
     * Constructor de la clase Moneda.
     * Inicializa la textura y asigna la estrategia de otorgar puntos al jugador.
     *
     * @param textura La textura que representa visualmente la moneda.
     */
    public Moneda(Texture textura) {
        super(textura, new OtorgarPuntos(10)); // Asignamos la estrategia de otorgar 10 puntos
    }

    /**
     * Actualiza el movimiento de la moneda en función de la velocidad proporcionada.
     * Calcula la nueva posición en el eje Y basándose en el tiempo transcurrido.
     *
     * @param velocidad La velocidad a la que la moneda debe caer.
     */
    @Override
    public void actualizarMovimiento(float velocidad) {
        float deltaY = velocidad * Gdx.graphics.getDeltaTime();
        setPosition(getX(), getY() - deltaY);
    }
}
