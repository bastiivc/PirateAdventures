package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * La clase abstracta ObjetoLluvia representa un objeto genérico que cae desde la parte superior de la pantalla en el juego.
 * Proporciona métodos y propiedades comunes para todos los objetos de lluvia como su área de colisión y textura,
 * y define métodos abstractos para actualizar el movimiento, dibujar el objeto y manejar colisiones con el tarro.
 */

public abstract class ObjetoLluvia implements Actualizable, Dibujable{
    protected Rectangle area;
    protected Texture textura;

    /**
     * Constructor de la clase ObjetoLluvia.
     * Inicializa la textura y define el área del objeto con dimensiones predeterminadas.
     *
     * @param textura La textura del objeto de lluvia.
     */

    public ObjetoLluvia(Texture textura) {
        this.textura = textura;
        area = new Rectangle();
        area.width = 64;
        area.height = 64;
    }

    /**
     * Método abstracto que debe ser implementado por las subclases para actualizar el movimiento del objeto.
     *
     * @param velocidad La velocidad a la que se debe mover el objeto.
     */

    @Override
    public abstract void actualizarMovimiento(float velocidad);

    /**
     * Método abstracto que debe ser implementado por las subclases para dibujar el objeto en pantalla.
     *
     * @param batch El SpriteBatch utilizado para dibujar el objeto.
     */

    @Override
    public abstract void dibujar(SpriteBatch batch);

    /**
     * Método abstracto que define la acción a realizar cuando el objeto colisiona con el tarro.
     *
     * @param tarro El tarro con el que el objeto ha colisionado.
     */

    public abstract void alColisionar(Tarro tarro);

    /**
     * Obtiene el área de colisión del objeto de lluvia.
     *
     * @return Un objeto Rectangle que representa el área de colisión del objeto.
     */

    public Rectangle getArea() {
        return area;
    }
}
