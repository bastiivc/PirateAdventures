package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * La clase Tiburón representa un objeto peligroso en forma de tiburón que cae y puede dañar al jugador cuando colisiona con el tarro.
 */

public class Tiburón extends ObjetoLluvia {

    public Tiburón(Texture textura) {
        super(textura);
    }

    @Override
    public void actualizarMovimiento(float velocidad) {
        float deltaY = velocidad * com.badlogic.gdx.Gdx.graphics.getDeltaTime();
        setPosition(getX(), getY() - deltaY);
    }

    @Override
    public void alColisionar(Pirate pirate) {
        pirate.dañar();
    }
}
