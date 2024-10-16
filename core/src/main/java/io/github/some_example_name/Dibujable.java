package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Interfaz que define el comportamiento de dibujado para los objetos del juego.
 * Las clases que implementen esta interfaz deben proporcionar su propia implementación
 * del método `dibujar` para renderizarse en la pantalla.
 */

public interface Dibujable
{

    /**
     * Dibuja el objeto utilizando el SpriteBatch proporcionado.
     *
     * @param batch El SpriteBatch utilizado para dibujar.
     */


    void dibujar(SpriteBatch batch);
}
