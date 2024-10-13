package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private GameLluvia gameLluvia;

    @Override
    public void create() {
        gameLluvia = new GameLluvia(); // Inicializar la lógica del juego
    }

    @Override
    public void render() {
        // Limpiar pantalla
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Actualizar el fondo según los puntos
        gameLluvia.actualizarFondo();

        // Delegar renderizado a GameLluvia
        gameLluvia.render();

        // Actualizar movimiento si no está en pausa
        gameLluvia.actualizarMovimiento();
    }

    @Override
    public void resize(int width, int height) {
        // Delegar resize al Stage del juego
        gameLluvia.getStage().getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        gameLluvia.dispose(); // Liberar recursos
    }
}
