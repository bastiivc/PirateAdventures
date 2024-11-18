package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

/**
 * La clase {@code EspadaFactory} implementa la interfaz {@link ObjetoLluviaFactory}
 * y es responsable de la creación de instancias de la clase {@link Espada}.
 *
 * <p>Utiliza el patrón de diseño Factory para encapsular la lógica de creación de
 * objetos de tipo {@link Espada}, asegurando flexibilidad y desacoplamiento en el
 * proceso de instanciación.</p>
 *
 * <p>Características principales:</p>
 * <ul>
 *     <li>Proporciona un método para crear instancias de {@link Espada}.</li>
 *     <li>Permite centralizar la creación de objetos, facilitando futuros cambios en
 *         el proceso de construcción.</li>
 * </ul>
 */
public class EspadaFactory implements ObjetoLluviaFactory {

    /**
     * Crea un nuevo objeto de tipo {@link Espada} utilizando la textura proporcionada.
     *
     * @param textura La textura que será utilizada para representar gráficamente la espada.
     *                Debe ser una instancia válida de {@link Texture}.
     * @return Una nueva instancia de {@link Espada} configurada con la textura proporcionada.
     */
    @Override
    public ObjetoLluvia crearObjeto(Texture textura) {
        return new Espada(textura);
    }
}
