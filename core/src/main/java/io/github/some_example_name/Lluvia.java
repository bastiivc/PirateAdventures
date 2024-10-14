package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
    private Array<Rectangle> rainDropsPos;
    private Array<Integer> rainDropsType;
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Sound dropSound;
    private Music rainMusic;

    // Nueva variable para controlar la velocidad de las gotas
    private float velocidadLluvia = 300; // Velocidad inicial

    public Lluvia(Texture gotaBuena, Texture gotaMala, Sound ss, Music mm) {
        rainMusic = mm;
        dropSound = ss;
        this.gotaBuena = gotaBuena;
        this.gotaMala = gotaMala;
    }

    public void crear() {
        rainDropsPos = new Array<>();
        rainDropsType = new Array<>();
        crearGotaDeLluvia();
        
        // Iniciar la reproducción de la música en bucle
        rainMusic.setLooping(true);
        rainMusic.play();
    }

    private void crearGotaDeLluvia() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        rainDropsPos.add(raindrop);

        // Determinar el tipo de gota (buena o mala)
        if (MathUtils.random(1, 10) < 3)
            rainDropsType.add(1); // Gota dañina
        else
            rainDropsType.add(2); // Gota buena
        
        lastDropTime = TimeUtils.nanoTime();
    }

    public void actualizarMovimiento(Tarro tarro, boolean juegoPausado) {
        if (juegoPausado) return;  // No actualizar si el juego está en pausa

        // Incrementar la velocidad si se alcanzan 1000 puntos
        if (tarro.getPuntos() >= 1000) {
            velocidadLluvia = 500; // Aumentar la velocidad de las gotas
        } else {
            velocidadLluvia = 300; // Mantener la velocidad inicial
        }

        // Crear nuevas gotas si ha pasado suficiente tiempo
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) {
            crearGotaDeLluvia();
        }

        // Actualizar la posición de las gotas
        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            raindrop.y -= velocidadLluvia * Gdx.graphics.getDeltaTime(); // Usar la velocidad actual

            // Eliminar gotas que caen fuera de la pantalla
            if (raindrop.y + 64 < 0) {
                rainDropsPos.removeIndex(i);
                rainDropsType.removeIndex(i);
            }

            // Verificar colisión con el tarro
            if (raindrop.overlaps(tarro.getArea())) {
                if (rainDropsType.get(i) == 1) { // Gota dañina
                    tarro.dañar();
                } else { // Gota buena
                    tarro.sumarPuntos(10);
                    dropSound.play();
                }

                // Eliminar la gota tras colisión
                rainDropsPos.removeIndex(i);
                rainDropsType.removeIndex(i);
            }
        }
    }

    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            if (rainDropsType.get(i) == 1) {
                batch.draw(gotaMala, raindrop.x, raindrop.y); // Dibujar gota mala
            } else {
                batch.draw(gotaBuena, raindrop.x, raindrop.y); // Dibujar gota buena
            }
        }
    }

    public void destruir() {
        dropSound.dispose();
        rainMusic.dispose();
    }

    public void reiniciar() {
        // Limpiar todas las gotas
        rainDropsPos.clear();
        rainDropsType.clear();

        // Crear la primera gota nuevamente
        crearGotaDeLluvia();

        // Reiniciar el tiempo de la última gota
        lastDropTime = TimeUtils.nanoTime();

        // Reiniciar la música
        rainMusic.setLooping(true);
        rainMusic.play();
    }
}
