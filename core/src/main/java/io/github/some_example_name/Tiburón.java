package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * La clase Tiburón representa un objeto peligroso que cae desde la parte superior
 * de la pantalla y puede dañar al jugador al colisionar con él.
 *
 * Hereda de {@link ObjetoLluvia} y utiliza la estrategia {@link DañarPirata}
 * para implementar la acción de colisión que resta una vida al jugador.
 */
public class Tiburón extends ObjetoLluvia {

    /**
     * Constructor de la clase Tiburón.
     * Configura la textura, asigna la estrategia de colisión {@link DañarPirata},
     * y personaliza el tamaño de la hitbox.
     *
     * @param textura La textura que representa al tiburón en el juego.
     */
    public Tiburón(Texture textura) {
        super(textura, new DañarPirata());
        setHitboxSize(10, 64); // Personalizar la hitbox
    }

    /**
     * Actualiza el movimiento del tiburón en función de la velocidad proporcionada.
     * Reduce la posición vertical (eje Y) del tiburón en función del tiempo delta.
     *
     * @param velocidad La velocidad de caída del tiburón.
     */
    @Override
    public void actualizarMovimiento(float velocidad) {
        float deltaY = velocidad * Gdx.graphics.getDeltaTime();
        setPosition(getX(), getY() - deltaY);
    }
}
