package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * La clase Espada representa un objeto en forma de espada que cae y puede interactuar con otros objetos en el juego.
 */

public class Espada extends ObjetoLluvia {

    public Espada(Texture textura) {
        super(textura);
    }

    @Override
    public void actualizarMovimiento(float velocidad) {
        float deltaY = velocidad * com.badlogic.gdx.Gdx.graphics.getDeltaTime();
        setPosition(getX(), getY() - deltaY);
    }

    @Override
    public void alColisionar(Pirate pirate) {
        GameManager.getInstance().addScore(20);
    }
}
