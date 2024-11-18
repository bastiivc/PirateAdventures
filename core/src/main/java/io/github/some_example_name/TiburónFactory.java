package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

/**
 * Fábrica para crear objetos de tipo {@link Tiburón}.
 * Implementa la interfaz {@link ObjetoLluviaFactory}, proporcionando
 * una instancia de {@link Tiburón} con la textura especificada.
 */
public class TiburónFactory implements ObjetoLluviaFactory {

    /**
     * Crea un nuevo objeto de tipo {@link Tiburón}.
     *
     * @param textura La textura que se usará para representar el tiburón en el juego.
     * @return Una nueva instancia de {@link Tiburón}.
     */
    @Override
    public ObjetoLluvia crearObjeto(Texture textura) {
        return new Tiburón(textura);
    }
}
