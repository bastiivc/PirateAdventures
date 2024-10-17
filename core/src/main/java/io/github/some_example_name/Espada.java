package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * La clase Espada representa un objeto en forma de espada que cae y puede interactuar con otros objetos en el juego.
 */

public class Espada extends ObjetoLluvia implements Destruible {

    /**
     * Constructor de la clase Espada.
     *
     * @param textura La textura de la espada.
     */

    public Espada(Texture textura) {
        super(textura);
    }

    /**
     * Actualiza el movimiento de la espada en función de la velocidad.
     *
     * @param velocidad La velocidad a la que la espada se mueve.
     */

    @Override
    public void actualizarMovimiento(float velocidad) {
        area.y -= velocidad * com.badlogic.gdx.Gdx.graphics.getDeltaTime();
    }

    /**
     * Dibuja la espada en la pantalla.
     *
     * @param batch El SpriteBatch utilizado para dibujar la espada.
     */

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, area.x, area.y);
    }

    /**
     * Acción a realizar cuando la espada colisiona con un tarro.
     *
     * @param tarro El tarro con el que la espada ha colisionado.
     */

    @Override
    public void alColisionar(Tarro tarro) {
        tarro.sumarPuntos(20); // La espada da más puntos que una moneda
    }

    /**
     * Libera los recursos utilizados por la espada.
     */

    @Override
    public void destruir() {
        textura.dispose();
    }
}
