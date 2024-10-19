package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * La clase Tiburón representa un objeto peligroso en forma de tiburón que cae y puede dañar al jugador cuando colisiona con el tarro.
 */

public class Tiburón extends ObjetoLluvia {

    /**
     * Constructor de la clase Tiburón.
     *
     * @param textura La textura del tiburón.
     */

    public Tiburón(Texture textura) {
        super(textura);
    }

    /**
     * Actualiza el movimiento del tiburón, haciendo que caiga en la pantalla a la velocidad especificada.
     *
     * @param velocidad La velocidad a la que el tiburón se mueve hacia abajo.
     */

    @Override
    public void actualizarMovimiento(float velocidad) {
        float deltaY = velocidad * Gdx.graphics.getDeltaTime();
        float nuevaY = getY() - deltaY;
        setPosition(getX(), nuevaY);
    }

    /**
     * Dibuja el tiburón en la pantalla en su posición actual.
     *
     * @param batch El SpriteBatch utilizado para dibujar el tiburón.
     */

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(getTextura(), getX(), getY());
    }

    /**
     * Realiza la acción correspondiente cuando el tiburón colisiona con el tarro.
     * En este caso, el tarro recibe daño y pierde una vida.
     *
     * @param tarro El tarro con el que el tiburón ha colisionado.
     */

    @Override
    public void alColisionar(Tarro tarro) {
        tarro.dañar();
    }
}
