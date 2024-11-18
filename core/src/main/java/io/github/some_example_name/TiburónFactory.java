package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

/**
 * Fábrica para crear objetos de tipo Tiburón.
 */
public class TiburónFactory implements ObjetoLluviaFactory {

    @Override
    public ObjetoLluvia crearObjeto(Texture textura) {
        return new Tiburón(textura);
    }
}
