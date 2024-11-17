package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * La clase Moneda representa un objeto en forma de moneda que cae y otorga puntos al jugador cuando colisiona con el tarro.
 */

public class Moneda extends ObjetoLluvia {

    public Moneda(Texture textura) {
        super(textura);
    }

    @Override
    public void actualizarMovimiento(float velocidad) {
        float deltaY = velocidad * com.badlogic.gdx.Gdx.graphics.getDeltaTime();
        setPosition(getX(), getY() - deltaY);
    }

    @Override
    public void alColisionar(Pirate pirate) {
        GameManager.getInstance().addScore(10);
    }
}
