package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.some_example_name.Main;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static void createApplication() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        // Configurar el título de la ventana y el icono
        config.setTitle("Pirate Adventures");
        config.setWindowIcon("pirate-captain.png"); // Icono para la ventana y barra de tareas

        config.setWindowedMode(800, 480); // Tamaño de la ventana al inicio
        config.setResizable(true); // Permitir redimensionar la ventana

        new Lwjgl3Application(new Main(), config);
    }
}
