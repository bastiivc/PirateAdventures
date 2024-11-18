package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * La clase Moneda representa un objeto en forma de moneda que cae y otorga puntos al jugador cuando colisiona con el tarro.
 */

public class Moneda extends ObjetoLluvia {

    public Moneda(Texture textura) {
        super(textura, new OtorgarPuntos(10)); // Asignamos la estrategia de otorgar 10 puntos
    }

    @Override
    public void actualizarMovimiento(float velocidad) {
        float deltaY = velocidad * Gdx.graphics.getDeltaTime();
        setPosition(getX(), getY() - deltaY);
    }
}
