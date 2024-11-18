package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

/**
 * Fábrica para crear objetos de tipo Espada.
 */
public class EspadaFactory implements ObjetoLluviaFactory {

    @Override
    public ObjetoLluvia crearObjeto(Texture textura) {
        return new Espada(textura);
    }
}
