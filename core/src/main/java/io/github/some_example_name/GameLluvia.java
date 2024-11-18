package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * La clase {@code GameLluvia} es el núcleo del juego, encargada de inicializar y gestionar
 * los elementos principales como la cámara, el pirata, la lluvia de objetos, los fondos,
 * el estado del juego y las interacciones.
 *
 * <p>Características principales:</p>
 * <ul>
 *   <li>Gestiona la inicialización de los recursos del juego, como texturas, sonidos y fuentes.</li>
 *   <li>Controla el ciclo de vida de los objetos del juego, incluyendo su renderizado y actualización.</li>
 *   <li>Maneja el sistema de pausa y reinicio del juego.</li>
 *   <li>Interacciona con {@link GameManager} para coordinar la lógica del juego.</li>
 * </ul>
 */
public class GameLluvia {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Pirate pirate;
    private Lluvia lluvia;

    private Texture background;
    private Texture dayBackground;
    private Texture nightBackground;
    private Texture buttonTexture;

    private BitmapFont font; // Fuente para mostrar puntos y vidas

    private Stage stage;
    private boolean juegoPausado = false;
    private ImageButton botonReiniciar;

    /**
     * Constructor de la clase {@code GameLluvia}.
     * <p>Inicializa los fondos, cámara, fuente, sprites, sonidos y otros elementos del juego.
     * También configura el sistema de pausa y reinicio.</p>
     */
    public GameLluvia() {
        // Inicialización de fondos
        dayBackground = new Texture(Gdx.files.internal("DayBackground1.jpg"));
        nightBackground = new Texture(Gdx.files.internal("NightBackground.png"));
        background = dayBackground;

        // Configuración de cámara y batch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        // Inicialización de la fuente
        font = new BitmapFont();

        // Configuración de pirata y lluvia
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("01._damage_grunt_male.wav"));
        pirate = new Pirate(new Texture(Gdx.files.internal("pirataSpriteA1OpenG.png")), hurtSound);

        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("Coins4.mp3"));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("pirate.mp3"));
        lluvia = new Lluvia(
            new Texture(Gdx.files.internal("moneda_pirata.png")),
            new Texture(Gdx.files.internal("Shark1.png")),
            new Texture(Gdx.files.internal("sword.png")),
            dropSound,
            rainMusic
        );

        pirate.crear();
        lluvia.crear();

        // Configuración del Stage
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Configuración del botón de reinicio
        buttonTexture = new Texture(Gdx.files.internal("button.png"));
        crearBotonReiniciar();

        // Registro del pirata en el GameManager
        GameManager.getInstance().setPirate(pirate);
    }

    /**
     * Crea el botón de reinicio del juego y lo añade al {@link Stage}.
     */
    private void crearBotonReiniciar() {
        TextureRegionDrawable drawable = new TextureRegionDrawable(buttonTexture);
        botonReiniciar = new ImageButton(drawable);
        botonReiniciar.setSize(125, 62);
        actualizarPosicionBotonReiniciar();

        botonReiniciar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                reiniciarJuego();
            }
        });

        stage.addActor(botonReiniciar);
        botonReiniciar.setVisible(false);
    }

    /**
     * Actualiza dinámicamente la posición del botón de reinicio en el centro de la pantalla.
     */
    private void actualizarPosicionBotonReiniciar() {
        botonReiniciar.setPosition(
            (stage.getViewport().getWorldWidth() - botonReiniciar.getWidth()) / 2,
            (stage.getViewport().getWorldHeight() - botonReiniciar.getHeight()) / 2
        );
    }

    /**
     * Ajusta el tamaño del {@link Stage} y reposiciona el botón al cambiar el tamaño de la ventana.
     *
     * @param width  El nuevo ancho de la ventana.
     * @param height El nuevo alto de la ventana.
     */
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        actualizarPosicionBotonReiniciar();
    }

    /**
     * Actualiza el fondo del juego según la puntuación del jugador.
     * Cambia entre los fondos de día y noche.
     */
    public void actualizarFondo() {
        GameManager gameManager = GameManager.getInstance();
        background = gameManager.isNight() ? nightBackground : dayBackground;
    }

    /**
     * Renderiza los elementos del juego en pantalla, gestionando el estado de pausa.
     */
    public void render() {
        if (pirate.getVidas() <= 0 && !juegoPausado) {
            pausarJuego();
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, 800, 480);
        pirate.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);
        font.draw(batch, "Puntuación: " + GameManager.getInstance().getScore(), 10, 470);
        font.draw(batch, "Vidas: " + pirate.getVidas(), 720, 470);
        batch.end();

        if (juegoPausado) {
            botonReiniciar.setVisible(true);
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }
    }

    /**
     * Actualiza el movimiento de los elementos del juego si este no está pausado.
     */
    public void actualizarMovimiento() {
        if (!juegoPausado) {
            pirate.actualizarMovimiento();
            lluvia.actualizarMovimiento(pirate, false);
        }
    }

    /**
     * Pausa el juego y muestra el botón de reinicio.
     */
    public void pausarJuego() {
        juegoPausado = true;
        botonReiniciar.setVisible(true);
    }

    /**
     * Reinicia el juego restaurando el estado inicial de los elementos y puntuaciones.
     */
    public void reiniciarJuego() {
        pirate.reiniciar();
        lluvia.reiniciar();
        background = dayBackground;
        GameManager.getInstance().reset();
        juegoPausado = false;
        botonReiniciar.setVisible(false);
    }

    /**
     * Libera todos los recursos utilizados por el juego, incluyendo texturas, sonidos y fuentes.
     */
    public void dispose() {
        dayBackground.dispose();
        nightBackground.dispose();
        buttonTexture.dispose();
        font.dispose();
        pirate.destruir();
        lluvia.destruir();
        batch.dispose();
    }

    /**
     * Obtiene el {@link Stage} utilizado por el juego para gestionar las interacciones.
     *
     * @return El {@link Stage} del juego.
     */
    public Stage getStage() {
        return stage;
    }
}
