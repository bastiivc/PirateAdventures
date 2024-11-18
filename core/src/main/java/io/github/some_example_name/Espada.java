package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * La clase Espada representa un objeto en forma de espada que cae y puede interactuar con otros objetos en el juego.
 */

public class Espada extends ObjetoLluvia {

    public Espada(Texture textura) {
        super(textura, new OtorgarPuntos(20)); // Asignamos la estrategia de otorgar 20 puntos
    }

    @Override
    public void actualizarMovimiento(float velocidad) {
        float deltaY = velocidad * Gdx.graphics.getDeltaTime();
        setPosition(getX(), getY() - deltaY);
    }
}
