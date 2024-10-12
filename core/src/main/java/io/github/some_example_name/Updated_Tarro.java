package io.github.some_example_name;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class Tarro {
    private Rectangle bucket;
    private Texture bucketImage;
    private Sound sonidoHerido;
    private int vidas = 3; // Vidas iniciales
    private int puntos;
    private int velx = 400;
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;

    public Tarro(Texture tex, Sound ss) {
        bucketImage = tex;
        sonidoHerido = ss;
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntos() {
        return puntos;
    }

    public Rectangle getArea() {
        return bucket;
    }

    public void sumarPuntos(int pp) {
        puntos += pp;
    }

    public void crear() {
        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;
    }

    public void dañar() {
        if (vidas > 0) {
            vidas--;
            herido = true;
            tiempoHerido = tiempoHeridoMax;
            sonidoHerido.play();
        }

        // Si las vidas ya son 0, no permitimos que se reduzcan más
        if (vidas <= 0) {
            vidas = 0;  // Asegurarse de que no baje de 0
        }
    }



    public void dibujar(SpriteBatch batch) {
        if (!herido)
            batch.draw(bucketImage, bucket.x, bucket.y);
        else {
            batch.draw(bucketImage, bucket.x, bucket.y + MathUtils.random(-5, 5));
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
    }

    public void actualizarMovimiento() {
        // Si el tarro está herido, no permitimos que se mueva normalmente
        if (herido) {
            tiempoHerido--;
            if (tiempoHerido <= 0) {
                herido = false; // Termina el estado de herida cuando el tiempo se agota
            }
            return; // No actualizar movimiento cuando está herido
        }

        // Movimiento normal cuando no está herido
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= velx * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += velx * Gdx.graphics.getDeltaTime();
        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > 800 - 64) bucket.x = 800 - 64;
    }


    public void destruir() {
        bucketImage.dispose();
    }

    public boolean estaHerido() {
        return herido;
    }

    public void reiniciar() {
        vidas = 3;  // Reiniciar las vidas a 3
        puntos = 0;  // Reiniciar los puntos
        bucket.x = 800 / 2 - 64 / 2;  // Resetear la posición del tarro
    }
}

// Add additional logic to handle drawing different parts of the sprite sheet
private TextureRegion[][] pirateRegions;

// Split the sprite sheet into regions (assuming 3x4 grid in the sheet).
public void initPirateRegions() {
    pirateRegions = TextureRegion.split(bucketImage, bucketImage.getWidth() / 3, bucketImage.getHeight() / 4);
}

// Modify the drawing function to use different regions based on movement direction.
@Override
public void dibujar(SpriteBatch batch) {
    int frameIndex = 1; // default to middle frame (facing down or idle)

    // Update the frame index based on direction (for example)
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        frameIndex = 0; // left frame
    } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        frameIndex = 2; // right frame
    }

    // Assume the pirate sheet's first row is for walking, so we always use row 0.
    batch.draw(pirateRegions[0][frameIndex], bucket.x, bucket.y);
}
