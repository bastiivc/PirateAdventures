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
 * Gestiona la creación, actualización y dibujo de objetos que caen, así como las colisiones con el jugador.
 */
public class Lluvia implements Destruible {

    private Array<ObjetoLluvia> objetosLluvia; // Lista de objetos en la lluvia
    private long lastDropTime; // Tiempo del último objeto creado
    private Sound dropSound; // Sonido reproducido al colisionar con el jugador
    private Music rainMusic; // Música de fondo de la lluvia

    private long intervaloCreacion = 80000000; // Intervalo entre creaciones de objetos
    private float velocidadCaida = 300; // Velocidad inicial de caída de los objetos

    private Map<String, ObjetoLluviaFactory> fabricas; // Mapa de fábricas de objetos
    private Texture texturaMoneda, texturaEspada, texturaTiburon; // Texturas de los objetos

    /**
     * Constructor de la clase Lluvia.
     * Inicializa las texturas, sonidos, música y las fábricas de objetos de lluvia.
     *
     * @param moneda La textura para los objetos de tipo Moneda.
     * @param tiburon La textura para los objetos de tipo Tiburón.
     * @param espada La textura para los objetos de tipo Espada.
     * @param ss El sonido que se reproduce cuando un objeto colisiona con el jugador.
     * @param mm La música de fondo que se reproduce durante la lluvia.
     */
    public Lluvia(Texture moneda, Texture tiburon, Texture espada, Sound ss, Music mm) {
        this.texturaMoneda = moneda;
        this.texturaEspada = espada;
        this.texturaTiburon = tiburon;
        this.dropSound = ss;
        this.rainMusic = mm;

        // Inicializar las fábricas de objetos
        fabricas = new HashMap<>();
        fabricas.put("moneda", new MonedaFactory());
        fabricas.put("espada", new EspadaFactory());
        fabricas.put("tiburon", new TiburónFactory());
    }

    /**
     * Inicializa la lluvia, creando los objetos iniciales y comenzando la música de fondo.
     */
    public void crear() {
        objetosLluvia = new Array<>();
        crearObjetoLluvia();

        rainMusic.setLooping(true);
        rainMusic.play();
    }

    /**
     * Crea un nuevo objeto de lluvia basado en las probabilidades definidas por el GameManager.
     * El objeto se posiciona aleatoriamente en el eje X en la parte superior de la pantalla.
     */
    private void crearObjetoLluvia() {
        ObjetoLluvia objeto;
        int probabilidad = MathUtils.random(1, 100);

        // Obtener las probabilidades de aparición desde el GameManager
        GameManager gameManager = GameManager.getInstance();
        int probMoneda = gameManager.getProbabilidadMoneda();
        int probTiburon = gameManager.getProbabilidadTiburon();

        // Decidir el tipo de objeto basado en las probabilidades
        if (probabilidad <= probMoneda) {
            objeto = fabricas.get("moneda").crearObjeto(texturaMoneda);
        } else if (probabilidad <= probMoneda + probTiburon) {
            objeto = fabricas.get("tiburon").crearObjeto(texturaTiburon);
        } else {
            objeto = fabricas.get("espada").crearObjeto(texturaEspada);
        }

        // Establecer la posición inicial del objeto
        objeto.setPosition(MathUtils.random(0, 800 - 64), 480);
        objetosLluvia.add(objeto);
        lastDropTime = TimeUtils.nanoTime();
    }

    /**
     * Actualiza el movimiento de los objetos en la lluvia.
     * Verifica las colisiones con el jugador y elimina los objetos que salen de la pantalla.
     *
     * @param pirate El objeto Pirate que representa al jugador, usado para verificar colisiones.
     * @param juegoPausado Indica si el juego está pausado para detener la actualización.
     */
    public void actualizarMovimiento(Pirate pirate, boolean juegoPausado) {
        if (juegoPausado) return;

        // Obtener velocidad actualizada desde el GameManager
        velocidadCaida = GameManager.getInstance().getFallSpeed();

        // Crear un nuevo objeto si ha pasado el intervalo de creación
        if (TimeUtils.nanoTime() - lastDropTime > intervaloCreacion) {
            crearObjetoLluvia();
        }

        // Actualizar la posición de los objetos
        for (int i = 0; i < objetosLluvia.size; i++) {
            ObjetoLluvia objeto = objetosLluvia.get(i);
            objeto.actualizarMovimiento(velocidadCaida);

            // Verificar colisión con el jugador
            if (objeto.getArea().overlaps(pirate.getArea())) {
                objeto.alColisionar(pirate);
                objetosLluvia.removeIndex(i);
                dropSound.play();
            }

            // Eliminar objetos que salen de la pantalla
            if (objeto.getArea().y + 64 < 0) {
                objetosLluvia.removeIndex(i);
            }
        }
    }

    /**
     * Dibuja los objetos de la lluvia en la pantalla utilizando un SpriteBatch.
     *
     * @param batch El SpriteBatch utilizado para dibujar los objetos.
     */
    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (ObjetoLluvia objeto : objetosLluvia) {
            objeto.dibujar(batch);
        }
    }

    /**
     * Libera los recursos asociados con la lluvia, como los sonidos y la música.
     */
    @Override
    public void destruir() {
        dropSound.dispose();
        rainMusic.dispose();
    }

    /**
     * Reinicia la lluvia, eliminando todos los objetos y reiniciando el estado inicial.
     */
    public void reiniciar() {
        objetosLluvia.clear();
        crearObjetoLluvia();
        lastDropTime = TimeUtils.nanoTime();
        rainMusic.play();
    }
}
