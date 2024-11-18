package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * La clase Main es la clase principal del juego, extiende de ApplicationAdapter y gestiona el ciclo de vida de la aplicación.
 * Se encarga de inicializar y delegar la lógica de juego a la clase GameLluvia.
 */
public class Main extends ApplicationAdapter {
    private GameLluvia gameLluvia; // Instancia de la lógica principal del juego

    /**
     * Método llamado al iniciar la aplicación.
     * Este método inicializa la lógica principal del juego creando una instancia de GameLluvia.
     */
    @Override
    public void create() {
        gameLluvia = new GameLluvia(); // Inicializar la lógica del juego
    }

    /**
     * Método llamado en cada frame para renderizar el juego.
     * Este método actualiza el fondo según la puntuación, renderiza los elementos del juego
     * y actualiza los movimientos si el juego no está pausado.
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
     * Ajusta el viewport del Stage a las nuevas dimensiones de la pantalla.
     *
     * @param width  El nuevo ancho de la ventana.
     * @param height El nuevo alto de la ventana.
     */
    @Override
    public void resize(int width, int height) {
        gameLluvia.resize(width, height);
    }

    /**
     * Método llamado al cerrar la aplicación.
     * Libera los recursos utilizados por la lógica del juego y otros elementos.
     */
    @Override
    public void dispose() {
        gameLluvia.dispose(); // Liberar recursos
    }
}
