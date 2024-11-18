package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

/**
 * Interfaz para las fábricas de objetos de lluvia.
 */
public interface ObjetoLluviaFactory {

    /**
     * Crea un nuevo objeto de lluvia.
     *
     * @param textura La textura que usará el objeto.
     * @return Un objeto de tipo ObjetoLluvia.
     */
    ObjetoLluvia crearObjeto(Texture textura);
}
