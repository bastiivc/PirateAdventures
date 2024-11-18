package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

/**
 * Fábrica para crear objetos de tipo Moneda.
 * Implementa la interfaz {@link ObjetoLluviaFactory} para proporcionar un método de creación
 * de instancias de la clase {@link Moneda}.
 */
public class MonedaFactory implements ObjetoLluviaFactory {

    /**
     * Crea una nueva instancia de {@link Moneda} con la textura proporcionada.
     *
     * @param textura La textura que se asignará a la moneda creada.
     * @return Una nueva instancia de {@link Moneda}.
     */
    @Override
    public ObjetoLluvia crearObjeto(Texture textura) {
        return new Moneda(textura);
    }
}
