package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * La clase Main es la clase principal del juego, extiende de ApplicationAdapter y gestiona el ciclo de vida de la aplicación.
 * Se encarga de inicializar y delegar la lógica de juego a la clase GameLluvia.
 */

public class Main extends ApplicationAdapter {
    private GameLluvia gameLluvia;

    /**
     * Método llamado al iniciar la aplicación.
     * Inicializa la lógica del juego creando una instancia de GameLluvia.
     */

    @Override
    public void create() {
        gameLluvia = new GameLluvia(); // Inicializar la lógica del juego
    }

    /**
     * Método llamado en cada frame para renderizar el juego.
     * Se encarga de limpiar la pantalla, actualizar el fondo, renderizar los elementos y actualizar los movimientos.
     */

    @Override
    public void render() {

        // Actualizar el fondo según los puntos
        gameLluvia.actualizarFondo();

        // Delegar renderizado a GameLluvia
        gameLluvia.render();

        // Actualizar movimiento si no está en pausa
        gameLluvia.actualizarMovimiento();
    }

    /**
     * Método llamado cuando la ventana de la aplicación cambia de tamaño.
     * Se encarga de ajustar el viewport del Stage a las nuevas dimensiones de la pantalla.
     *
     * @param width  El nuevo ancho de la ventana.
     * @param height El nuevo alto de la ventana.
     */

    @Override
    public void resize(int width, int height) {
        // Delegar resize al Stage del juego
        gameLluvia.getStage().getViewport().update(width, height, true);
    }

    /**
     * Método llamado al cerrar la aplicación.
     * Libera los recursos utilizados por el juego.
     */

    @Override
    public void dispose() {
        gameLluvia.dispose(); // Liberar recursos
    }
}
