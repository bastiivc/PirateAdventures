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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameLluvia extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Tarro tarro;
    private Lluvia lluvia;
    
    private Stage stage; // Para manejar la UI
    private TextButton botonReiniciar; // Botón de reinicio
    private boolean juegoPausado = false; // Estado de pausa
    
    @Override
    public void create () {
        new BitmapFont();
        
        // Cargar imágenes y sonidos
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        tarro = new Tarro(new Texture(Gdx.files.internal("bucket.png")), hurtSound);

        Texture gota = new Texture(Gdx.files.internal("drop.png"));
        Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("pirate.mp3"));
        lluvia = new Lluvia(gota, gotaMala, dropSound, rainMusic);

        // Cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        // Crear tarro y lluvia
        tarro.crear();
        lluvia.crear();

        // Inicialización del Stage
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage); // Permitir que el Stage maneje los inputs del usuario
    }

    public void reiniciarJuego() {
        // Reiniciar variables
        tarro.reiniciar();
        lluvia.reiniciar();
        juegoPausado = false;  // Reanudar el juego

        // Ocultar el botón
        stage.clear();
    }

    @Override
    public void render() {
        // Limpiar la pantalla con color azul oscuro
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Actualizar la cámara
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Verificar si el juego está en pausa
        if (juegoPausado) {
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
            return; // Detener el juego aquí mientras esté en pausa
        }

        // Si las vidas del tarro son 0, pausar el juego
        if (tarro.getVidas() == 0 && !juegoPausado) {
            pausarJuego();  // Pausar el juego solo una vez cuando llegue a 0 vidas
        }

        // Dibujar y actualizar el estado del juego si no está pausado
        batch.begin();
        tarro.dibujar(batch); // Dibuja el tarro
        lluvia.actualizarDibujoLluvia(batch); // Dibuja las gotas de lluvia
        batch.end();

        // Solo actualizar el movimiento si el juego no está pausado
        if (!juegoPausado) {
            tarro.actualizarMovimiento(); // Actualiza el movimiento del tarro
            lluvia.actualizarMovimiento(tarro, juegoPausado); // Actualiza el movimiento de las gotas de lluvia
        }
    }
    
    public void pausarJuego() {
        juegoPausado = true;  // Detener el juego
    }
}