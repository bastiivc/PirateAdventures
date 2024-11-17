package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * La clase abstracta ObjetoLluvia representa un objeto genérico que cae desde la parte superior de la pantalla en el juego.
 * Proporciona métodos y propiedades comunes para todos los objetos de lluvia como su área de colisión y textura,
 * y define métodos abstractos para actualizar el movimiento, dibujar el objeto y manejar colisiones con el tarro.
 */

public abstract class ObjetoLluvia implements Actualizable, Dibujable {

    private Rectangle area;
    private Texture textura;

    public ObjetoLluvia(Texture textura) {
        this.textura = textura;
        area = new Rectangle();
        area.width = 64;
        area.height = 64;
    }

    public Rectangle getArea() {
        return area;
    }

    protected float getY() {
        return area.y;
    }

    protected float getX() {
        return area.x;
    }

    protected void setPosition(float x, float y) {
        area.setPosition(x, y);
    }

    protected Texture getTextura() {
        return textura;
    }

    /**
     * Método Template que define los pasos para actualizar un objeto.
     * Este método utiliza la lógica común, delegando los pasos específicos a las subclases.
     *
     * @param velocidad la velocidad a la que el objeto debe moverse.
     * @param pirate    el objeto Pirate para verificar colisiones.
     */
    public final void actualizar(float velocidad, Pirate pirate) {
        actualizarMovimiento(velocidad);
        if (colisionaCon(pirate)) {
            alColisionar(pirate);
        }
    }

    /**
     * Verifica si el objeto colisiona con el Pirate.
     *
     * @param pirate el objeto Pirate a verificar.
     * @return true si hay colisión, false de lo contrario.
     */
    private boolean colisionaCon(Pirate pirate) {
        return area.overlaps(pirate.getArea());
    }

    @Override
    public abstract void actualizarMovimiento(float velocidad);

    public abstract void alColisionar(Pirate pirate);

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, getX(), getY());
    }
}
