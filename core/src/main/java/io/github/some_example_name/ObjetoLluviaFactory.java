package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

/**
 * Interfaz para las fábricas de objetos de lluvia.
 * Define un método para la creación de objetos de lluvia con una textura específica.
 */
public interface ObjetoLluviaFactory {

    /**
     * Crea un nuevo objeto de lluvia.
     * Este método debe ser implementado por las clases concretas que definan el tipo de objeto de lluvia a crear.
     *
     * @param textura La textura que representará gráficamente el objeto de lluvia.
     * @return Un objeto de tipo {@link ObjetoLluvia} configurado con la textura especificada.
     */
    ObjetoLluvia crearObjeto(Texture textura);
}
