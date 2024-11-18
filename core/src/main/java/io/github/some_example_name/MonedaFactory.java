package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

/**
 * Fábrica para crear objetos de tipo Moneda.
 */
public class MonedaFactory implements ObjetoLluviaFactory {

    @Override
    public ObjetoLluvia crearObjeto(Texture textura) {
        return new Moneda(textura);
    }
}
