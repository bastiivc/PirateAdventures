package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * La clase Moneda representa un objeto en forma de moneda que cae y otorga puntos al jugador cuando colisiona con el tarro.
 */

public class Moneda extends ObjetoLluvia {

    /**
     * Constructor de la clase Moneda.
     *
     * @param textura La textura de la moneda.
     */

    public Moneda(Texture textura) {
        super(textura);
    }

    /**
     * Actualiza el movimiento de la moneda, haciendo que caiga en la pantalla a la velocidad especificada.
     *
     * @param velocidad La velocidad a la que la moneda se mueve hacia abajo.
     */

    @Override
    public void actualizarMovimiento(float velocidad) {
        float deltaY = velocidad * Gdx.graphics.getDeltaTime();
        float nuevaY = getY() - deltaY;
        setPosition(getX(), nuevaY);
    }

    /**
     * Dibuja la moneda en la pantalla en su posición actual.
     *
     * @param batch El SpriteBatch utilizado para dibujar la moneda.
     */

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(getTextura(), getX(), getY());
    }

    /**
     * Realiza la acción correspondiente cuando la moneda colisiona con el tarro del jugador.
     * En este caso, se suman 10 puntos al jugador.
     *
     * @param pirate El tarro con el que la moneda ha colisionado.
     */

    @Override
    public void alColisionar(Pirate pirate) {
        GameManager.getInstance().addScore(20); // Agregar puntos al GameManager
    }
}
