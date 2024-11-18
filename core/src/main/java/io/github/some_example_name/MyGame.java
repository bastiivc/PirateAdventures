package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Clase principal del juego que extiende {@link ApplicationAdapter}.
 * Esta clase gestiona la configuración inicial y el ciclo de vida del juego, incluyendo la creación de la cámara
 * y el viewport, y asegura que el mundo virtual mantenga dimensiones fijas independientemente del tamaño de la ventana.
 */
public class MyGame extends ApplicationAdapter {
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    /** Ancho del mundo virtual en unidades. */
    public static final float WORLD_WIDTH = 800;
    /** Alto del mundo virtual en unidades. */
    public static final float WORLD_HEIGHT = 600;

    /**
     * Método llamado al iniciar el juego.
     * Configura la cámara ortográfica, el viewport y el {@link SpriteBatch} para la renderización.
     */
    @Override
    public void create() {
        // Crear la cámara y el viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();

        // Configurar la posición inicial de la cámara
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
    }

    /**
     * Método llamado en cada frame del juego.
     * Actualiza la cámara y renderiza los objetos en la pantalla.
     */
    @Override
    public void render() {
        // Limpiar la pantalla
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Actualizar la cámara
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        // Dibujar los objetos del juego
        batch.end();
    }

    /**
     * Método llamado cuando la ventana es redimensionada.
     * Ajusta el viewport para reflejar las nuevas dimensiones de la ventana y mantener las proporciones del mundo virtual.
     *
     * @param width  Nuevo ancho de la ventana en píxeles.
     * @param height Nuevo alto de la ventana en píxeles.
     */
    @Override
    public void resize(int width, int height) {
        // Actualizar el viewport al redimensionar la ventana
        viewport.update(width, height);
    }
}
