package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * La clase abstracta ObjetoLluvia representa un objeto genérico que cae desde la parte superior de la pantalla en el juego.
 * Proporciona métodos y propiedades comunes para todos los objetos de lluvia como su área de colisión y textura,
 * y define métodos abstractos para actualizar el movimiento.
 */

public abstract class ObjetoLluvia implements Actualizable, Dibujable {

    private Rectangle area;
    private Texture textura;
    private ColisionEstrategia estrategia; // Estrategia para manejar la colisión

    public ObjetoLluvia(Texture textura, ColisionEstrategia estrategia) {
        this.textura = textura;
        this.estrategia = estrategia;
        area = new Rectangle();
        area.width = 48;
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

    public void setHitboxSize(float width, float height) {
        area.setSize(width, height);
    }

    @Override
    public abstract void actualizarMovimiento(float velocidad);

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, getX(), getY());
    }

    public void alColisionar(Pirate pirate) {
        if (estrategia != null) {
            estrategia.ejecutarAccion(pirate);
        }
    }
}
