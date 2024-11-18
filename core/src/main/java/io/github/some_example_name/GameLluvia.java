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
 * La clase GameLluvia es el núcleo del juego, encargada de inicializar y gestionar los elementos principales
 * como la cámara, el tarro, la lluvia, los fondos y las interacciones del juego.
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

    private BitmapFont font; // Fuente para puntos y vidas

    private Stage stage;
    private boolean juegoPausado = false;
    private ImageButton botonReiniciar;

    /**
     * Constructor de la clase GameLluvia. Inicializa fondos, cámara, batch, fuente, tarro, lluvia y el botón de reinicio.
     */

    public GameLluvia() {
        // Inicializar fondos
        dayBackground = new Texture(Gdx.files.internal("DayBackground1.jpg"));
        nightBackground = new Texture(Gdx.files.internal("NightBackground.png"));
        background = dayBackground;

        // Inicializar cámara y batch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        // Inicializar fuente
        font = new BitmapFont();

        // Crear Tarro y Lluvia
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

        // Inicializar Stage
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Crear botón de reinicio
        buttonTexture = new Texture(Gdx.files.internal("button.png"));
        crearBotonReiniciar();

        // Establecer el pirata en el GameManager
        GameManager.getInstance().setPirate(pirate);
    }

    /**
     * Crea el botón de reiniciar y lo añade al stage.
     */

    private void crearBotonReiniciar() {
        // Crear drawable para el botón
        TextureRegionDrawable drawable = new TextureRegionDrawable(buttonTexture);

        // Crear ImageButton con el drawable
        botonReiniciar = new ImageButton(drawable);
        botonReiniciar.setSize(125, 62);

        // Configurar posición inicial del botón
        actualizarPosicionBotonReiniciar();

        // Añadir listener para reiniciar el juego
        botonReiniciar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                reiniciarJuego();
            }
        });

        // Añadir el botón al Stage
        stage.addActor(botonReiniciar);
        botonReiniciar.setVisible(false);
    }

    // Método para actualizar la posición del botón dinámicamente
    private void actualizarPosicionBotonReiniciar() {
        botonReiniciar.setPosition(
            (stage.getViewport().getWorldWidth() - botonReiniciar.getWidth()) / 2,
            (stage.getViewport().getWorldHeight() - botonReiniciar.getHeight()) / 2
        );
    }

    /**
     * Ajusta el tamaño del viewport y reposiciona el botón de reinicio al cambiar el tamaño de la ventana.
     *
     * @param width  El nuevo ancho de la ventana.
     * @param height El nuevo alto de la ventana.
     */

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        actualizarPosicionBotonReiniciar();
    }

    /**
     * Actualiza el fondo del juego dependiendo de la puntuación del tarro.
     */

    public void actualizarFondo() {
        GameManager gameManager = GameManager.getInstance();
        if (gameManager.isNight()) {
            background = nightBackground;
        } else {
            background = dayBackground;
        }
    }


    /**
     * Renderiza el juego, dibujando los elementos en pantalla y gestionando el estado de pausa.
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
     * Reinicia el juego a su estado inicial.
     */

    public void reiniciarJuego() {
        // Reinicia los estados del pirata y la lluvia
        pirate.reiniciar(); // Restaura vidas y posición inicial del pirate
        lluvia.reiniciar(); // Limpia y restablece los objetos de lluvia

        // Reinicia el fondo al estado inicial (día)
        background = dayBackground;

        // Reinicia la puntuación y otros estados globales del juego
        GameManager.getInstance().reset(); // Reinicia puntuación y dificultad

        // Reinicia el estado de pausa
        juegoPausado = false;

        // Oculta el botón de reinicio
        botonReiniciar.setVisible(false);
    }

    /**
     * Libera los recursos utilizados por el juego.
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
     * Obtiene el Stage del juego.
     *
     * @return El Stage utilizado en el juego.
     */

    public Stage getStage() {
        return stage;
    }
}
