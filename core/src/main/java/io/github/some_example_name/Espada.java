package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Espada extends ObjetoLluvia implements Destruible {

    public Espada(Texture textura) {
        super(textura);
    }

    @Override
    public void actualizarMovimiento(float velocidad) {
        area.y -= velocidad * com.badlogic.gdx.Gdx.graphics.getDeltaTime();
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, area.x, area.y);
    }

    @Override
    public void alColisionar(Tarro tarro) {
        tarro.sumarPuntos(20); // La espada da más puntos que una moneda
    }

    @Override
    public void destruir() {
        textura.dispose();
    }
}
