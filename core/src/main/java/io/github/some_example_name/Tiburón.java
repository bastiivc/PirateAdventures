package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * La clase Tiburón representa un objeto peligroso en forma de tiburón que cae y puede dañar al jugador cuando colisiona con el tarro.
 */

public class Tiburón extends ObjetoLluvia {

    public Tiburón(Texture textura) {
        super(textura, new DañarPirata()); // Asignamos la estrategia de dañar al pirata
    }

    @Override
    public void actualizarMovimiento(float velocidad) {
        float deltaY = velocidad * Gdx.graphics.getDeltaTime();
        setPosition(getX(), getY() - deltaY);
    }
}
