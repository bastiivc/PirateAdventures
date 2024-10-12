package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Main extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;	   
    private BitmapFont font;

    private Tarro tarro;
    private Lluvia lluvia;

    private boolean juegoPausado = false; // Estado de pausa
    private Stage stage;
    
    @Override
    public void create () {
        font = new BitmapFont(); // use libGDX's default Arial font

        // Cargar imágenes y sonidos
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("01._damage_grunt_male.wav"));
        tarro = new Tarro(new Texture(Gdx.files.internal("pirataSpriteA1OpenG.png")), hurtSound);

        // Cargar la textura de las gotas
        Texture moneda = new Texture(Gdx.files.internal("moneda_pirata.png"));
        Texture tiburon = new Texture(Gdx.files.internal("Shark1.png"));

        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("coins4.mp3"));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("pirate.mp3"));
        lluvia = new Lluvia(moneda, tiburon, dropSound, rainMusic);

        // Inicializar la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        // Crear el tarro y la lluvia
        tarro.crear();
        lluvia.crear();

        // Inicializar el stage para el botón de reinicio
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    public void mostrarBotonReiniciar() {
        // Cargar la imagen del botón
        Texture buttonTexture = new Texture(Gdx.files.internal("button.png")); // Asegúrate de tener button.png en tus assets
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));

        // Crear el botón de imagen sin texto
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.imageUp = buttonDrawable;  // Imagen cuando no está presionado
        imageButtonStyle.imageDown = buttonDrawable;  // Puedes cambiar esta a otra imagen si quieres un efecto al presionar

        // Crear el botón de imagen con el estilo
        ImageButton botonReiniciar = new ImageButton(imageButtonStyle);

        // Establecer el tamaño (resize) del botón
        botonReiniciar.setSize(125, 62);  // Cambia el tamaño a 200x100 píxeles

        // Posicionar el botón en el centro de la pantalla
        botonReiniciar.setPosition(Gdx.graphics.getWidth() / 2 - botonReiniciar.getWidth() / 2,
                                   Gdx.graphics.getHeight() / 2 - botonReiniciar.getHeight() / 2);

        // Añadir el botón al Stage
        stage.addActor(botonReiniciar);

        // Añadir listener al botón
        botonReiniciar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                reiniciarJuego();  // Reiniciar el juego al hacer clic en el botón
            }
        });
    }


    public void reiniciarJuego() {
        // Reiniciar el estado del juego
        tarro.reiniciar();
        lluvia.reiniciar();
        juegoPausado = false;  // Reanudar el juego

        // Limpiar el stage (eliminar el botón de reinicio)
        stage.clear();
    }

    @Override
    public void render () {
        // Limpiar la pantalla con color azul oscuro
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Actualizar la cámara
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // Dibujar la puntuación y las vidas
        font.draw(batch, "Gotas totales: " + tarro.getPuntos(), 5, 475);
        font.draw(batch, "Vidas : " + tarro.getVidas(), 720, 475);

        // Verificar si las vidas han llegado a 0 para pausar el juego
        if (tarro.getVidas() == 0 && !juegoPausado) {
            pausarJuego();  // Pausar el juego cuando llegue a 0 vidas
        }

        // Solo actualizar el juego si no está en pausa
        if (!juegoPausado) {
            tarro.actualizarMovimiento();  // Actualizar el movimiento del tarro
            lluvia.actualizarMovimiento(tarro, false);  // Actualizar el movimiento de la lluvia
        }

        tarro.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);
        batch.end();

        // Si el juego está en pausa, mostrar el botón de reinicio
        if (juegoPausado) {
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw(); // Dibujar el botón de reinicio
        }
    }

    public void pausarJuego() {
        juegoPausado = true;  // Detener el juego
        mostrarBotonReiniciar();  // Mostrar el botón de reinicio
    }

    @Override
    public void dispose () {
        tarro.destruir();
        lluvia.destruir();
        batch.dispose();
        font.dispose();
    }
}