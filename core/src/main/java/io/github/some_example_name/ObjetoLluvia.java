package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoLluvia {
    protected Rectangle area;
    protected Texture textura;

    public ObjetoLluvia(Texture textura) {
        this.textura = textura;
        area = new Rectangle();
        area.width = 64;
        area.height = 64;
    }

    // Método abstracto para actualizar el movimiento
    public abstract void actualizarMovimiento(float velocidad);

    // Método abstracto para dibujar el objeto en pantalla
    public abstract void dibujar(SpriteBatch batch);

    // Método abstracto para definir la acción al colisionar con el tarro
    public abstract void alColisionar(Tarro tarro);

    // Método para obtener el área de colisión
    public Rectangle getArea() {
        return area;
    }
}
