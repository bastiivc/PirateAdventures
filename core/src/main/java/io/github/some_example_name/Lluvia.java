package io.github.some_example_name;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Clase que controla la lluvia de objetos en el juego.
 */
public class Lluvia implements Destruible {
    private Array<ObjetoLluvia> objetosLluvia;
    private long lastDropTime;
    private Sound dropSound;
    private Music rainMusic;

    private long intervaloCreacion = 80000000;
    private float velocidadCaida = 300;

    private Map<String, ObjetoLluviaFactory> fabricas; // Fábricas de objetos
    private Texture texturaMoneda, texturaEspada, texturaTiburon;

    public Lluvia(Texture moneda, Texture tiburon, Texture espada, Sound ss, Music mm) {
        this.texturaMoneda = moneda;
        this.texturaEspada = espada;
        this.texturaTiburon = tiburon;
        this.dropSound = ss;
        this.rainMusic = mm;

        // Inicializar las fábricas
        fabricas = new HashMap<>();
        fabricas.put("moneda", new MonedaFactory());
        fabricas.put("espada", new EspadaFactory());
        fabricas.put("tiburon", new TiburónFactory());
    }

    public void crear() {
        objetosLluvia = new Array<>();
        crearObjetoLluvia();

        rainMusic.setLooping(true);
        rainMusic.play();
    }

    private void crearObjetoLluvia() {
        ObjetoLluvia objeto;
        int probabilidad = MathUtils.random(1, 100);

        GameManager gameManager = GameManager.getInstance();
        int probMoneda = gameManager.getProbabilidadMoneda();
        int probTiburon = gameManager.getProbabilidadTiburon();

        if (probabilidad <= probMoneda) {
            objeto = fabricas.get("moneda").crearObjeto(texturaMoneda);
        } else if (probabilidad <= probMoneda + probTiburon) {
            objeto = fabricas.get("tiburon").crearObjeto(texturaTiburon);
        } else {
            objeto = fabricas.get("espada").crearObjeto(texturaEspada);
        }

        objeto.setPosition(MathUtils.random(0, 800 - 64), 480);
        objetosLluvia.add(objeto);
        lastDropTime = TimeUtils.nanoTime();
    }


    public void actualizarMovimiento(Pirate pirate, boolean juegoPausado) {
        if (juegoPausado) return;

        // Obtener velocidad actualizada desde GameManager
        velocidadCaida = GameManager.getInstance().getFallSpeed();

        // Crear un nuevo objeto si ha pasado el intervalo definido
        if (TimeUtils.nanoTime() - lastDropTime > intervaloCreacion) {
            crearObjetoLluvia();
        }

        // Actualizar la posición de los objetos
        for (int i = 0; i < objetosLluvia.size; i++) {
            ObjetoLluvia objeto = objetosLluvia.get(i);
            objeto.actualizarMovimiento(velocidadCaida);

            // Verificar colisión con el pirata
            if (objeto.getArea().overlaps(pirate.getArea())) {
                objeto.alColisionar(pirate);
                objetosLluvia.removeIndex(i);
                dropSound.play();
            }

            // Eliminar objetos fuera de la pantalla
            if (objeto.getArea().y + 64 < 0) {
                objetosLluvia.removeIndex(i);
            }
        }
    }

    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (ObjetoLluvia objeto : objetosLluvia) {
            objeto.dibujar(batch);
        }
    }

    @Override
    public void destruir() {
        dropSound.dispose();
        rainMusic.dispose();
    }

    public void reiniciar() {
        objetosLluvia.clear();
        crearObjetoLluvia();
        lastDropTime = TimeUtils.nanoTime();
        rainMusic.play();
    }
}
