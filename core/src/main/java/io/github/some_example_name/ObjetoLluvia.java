package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * La clase abstracta {@code ObjetoLluvia} representa un objeto genérico que cae desde la parte superior de la pantalla en el juego.
 * Proporciona métodos y propiedades comunes para todos los objetos de lluvia, como su área de colisión y textura.
 * Define métodos abstractos y concretos para manejar el movimiento y la interacción con otros objetos.
 */
public abstract class ObjetoLluvia implements Actualizable, Dibujable {

    private Rectangle area;
    private Texture textura;
    private ColisionEstrategia estrategia; // Estrategia para manejar la colisión

    /**
     * Constructor para inicializar un objeto de lluvia con su textura y estrategia de colisión.
     *
     * @param textura    La textura que representa gráficamente el objeto.
     * @param estrategia La estrategia que define la acción al colisionar con otro objeto.
     */
    public ObjetoLluvia(Texture textura, ColisionEstrategia estrategia) {
        this.textura = textura;
        this.estrategia = estrategia;
        area = new Rectangle();
        area.width = 48; // Ancho predeterminado del área de colisión
        area.height = 64; // Altura predeterminada del área de colisión
    }

    /**
     * Obtiene el área de colisión del objeto de lluvia.
     *
     * @return El área de colisión como un objeto {@link Rectangle}.
     */
    public Rectangle getArea() {
        return area;
    }

    /**
     * Obtiene la coordenada Y actual del objeto.
     *
     * @return La posición en el eje Y.
     */
    protected float getY() {
        return area.y;
    }

    /**
     * Obtiene la coordenada X actual del objeto.
     *
     * @return La posición en el eje X.
     */
    protected float getX() {
        return area.x;
    }

    /**
     * Establece la posición del objeto en la pantalla.
     *
     * @param x La nueva posición en el eje X.
     * @param y La nueva posición en el eje Y.
     */
    protected void setPosition(float x, float y) {
        area.setPosition(x, y);
    }

    /**
     * Obtiene la textura gráfica del objeto.
     *
     * @return La textura del objeto.
     */
    protected Texture getTextura() {
        return textura;
    }

    /**
     * Establece un nuevo tamaño para el área de colisión del objeto.
     *
     * @param width  El nuevo ancho del área de colisión.
     * @param height La nueva altura del área de colisión.
     */
    public void setHitboxSize(float width, float height) {
        area.setSize(width, height);
    }

    /**
     * Actualiza el movimiento del objeto en función de la velocidad proporcionada.
     * Este método debe ser implementado por las subclases para definir el comportamiento específico.
     *
     * @param velocidad La velocidad a la que el objeto debe moverse.
     */
    @Override
    public abstract void actualizarMovimiento(float velocidad);

    /**
     * Dibuja el objeto en la pantalla utilizando un {@link SpriteBatch}.
     *
     * @param batch El {@link SpriteBatch} utilizado para renderizar el objeto.
     */
    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, getX(), getY());
    }

    /**
     * Ejecuta la acción de colisión definida por la estrategia asignada al objeto.
     *
     * @param pirate El objeto {@link Pirate} con el que se verifica la colisión.
     */
    public void alColisionar(Pirate pirate) {
        if (estrategia != null) {
            estrategia.ejecutarAccion(pirate);
        }
    }
}
