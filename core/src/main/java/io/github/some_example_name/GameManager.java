package io.github.some_example_name;

/**
 * Clase Singleton que gestiona el estado global del juego.
 * Maneja la puntuación, velocidad de los objetos y fondo actual.
 */
public class GameManager {
    private static GameManager instance;

    // Variables globales del juego
    private int score = 0;
    private float fallSpeed = 300; // Velocidad inicial de caída
    private float playerSpeed = 280; // Velocidad inicial del jugador
    private boolean isNight = false;
    private int extraLivesGiven = 0; // Contador de vidas otorgadas extra
    private Pirate pirate; // Referencia al jugador
    private int probabilidadMoneda = 60;  // Inicial
    private int probabilidadTiburon = 20; // Inicial
    private int probabilidadEspada = 20;  // Inicial

    public void setProbabilidadMoneda(int probabilidad) {
        probabilidadMoneda = probabilidad;
    }

    public void setProbabilidadTiburon(int probabilidad) {
        probabilidadTiburon = probabilidad;
    }

    public void setProbabilidadEspada(int probabilidad) {
        probabilidadEspada = probabilidad;
    }

    public int getProbabilidadMoneda() {
        return probabilidadMoneda;
    }

    public int getProbabilidadTiburon() {
        return probabilidadTiburon;
    }

    public int getProbabilidadEspada() {
        return probabilidadEspada;
    }

    // Constructor privado para evitar instancias externas
    private GameManager() {}

    /**
     * Método para obtener la instancia única de GameManager.
     *
     * @return La instancia única de GameManager.
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void addScore(int points) {
        score += points;
        ajustarDificultad();

        // Lógica para otorgar vidas extra
        int livesToGive = score / 300; // Una vida extra por cada 300 puntos
        if (livesToGive > extraLivesGiven) {
            int newLives = livesToGive - extraLivesGiven;
            extraLivesGiven = livesToGive;
            if (pirate != null) { // Verifica si pirate está registrado
                pirate.addLives(newLives);
            }
        }
    }

    public int getScore() {
        return score;
    }

    // Métodos para manejar la velocidad
    public float getFallSpeed() {
        return fallSpeed;
    }

    public float getPlayerSpeed() {
        return playerSpeed;
    }

    // Método para registrar el Pirate en el GameManager
    public void setPirate(Pirate pirate) {
        this.pirate = pirate;
    }

    // Métodos para manejar el fondo
    public boolean isNight() {
        return isNight;
    }

    // Ajustar la dificultad en función de la puntuación
    private void ajustarDificultad() {
        // Variables para probabilidades
        int baseProbabilidadMoneda = 60;
        int baseProbabilidadTiburon = 20;
        int baseProbabilidadEspada = 20;

        if (score >= 2000) {
            // Incremento del 1.4% por cada 100 puntos a partir de 2000
            float increment = ((score - 2000) / 100) * 0.014f;
            fallSpeed = 800 * (1 + increment);
            playerSpeed = 500 * (1 + increment);

            // Ajustar probabilidades
            baseProbabilidadMoneda = 50;
            baseProbabilidadTiburon = 30;
            baseProbabilidadEspada = 20;

        } else if (score >= 1500) {
            // Incremento del 1.3% entre 1500 y 2000
            float increment = ((score - 1500) / 100) * 0.013f;
            fallSpeed = 700 * (1 + increment);
            playerSpeed = 450 * (1 + increment);

            // Ajustar probabilidades
            baseProbabilidadMoneda = 55;
            baseProbabilidadTiburon = 25;
            baseProbabilidadEspada = 20;

        } else if (score >= 1000) {
            // Incremento del 1.2% entre 1000 y 1500
            float increment = ((score - 1000) / 100) * 0.012f;
            fallSpeed = 600 * (1 + increment);
            playerSpeed = 400 * (1 + increment);
            isNight = true;

            // Ajustar probabilidades
            baseProbabilidadMoneda = 60 - (int) ((increment / 0.012f) * 2); // Disminuir monedas
            baseProbabilidadTiburon = 20 + (int) ((increment / 0.012f) * 1); // Incrementar tiburones
            baseProbabilidadEspada = 20 + (int) ((increment / 0.012f) * 1);  // Incrementar espadas

        } else {
            float increment = (score / 100) * 0.011f;
            fallSpeed = 300 * (1 + increment);
            playerSpeed = 280 * (1 + increment);

            // Probabilidades base iniciales
            baseProbabilidadMoneda = 60;
            baseProbabilidadTiburon = 20;
            baseProbabilidadEspada = 20;
        }

        // Actualizar las probabilidades finales en el juego
        actualizarProbabilidades(baseProbabilidadMoneda, baseProbabilidadTiburon, baseProbabilidadEspada);
    }

    // Método para actualizar las probabilidades en el juego
    private void actualizarProbabilidades(int probMoneda, int probTiburon, int probEspada) {
        // Puedes usar setters en la clase `Lluvia` o similar para que refleje estos valores
        GameManager.getInstance().setProbabilidadMoneda(probMoneda);
        GameManager.getInstance().setProbabilidadTiburon(probTiburon);
        GameManager.getInstance().setProbabilidadEspada(probEspada);
    }

    public void reset() {
        score = 0;
        fallSpeed = 300; // Velocidad inicial de caída
        playerSpeed = 280; // Velocidad inicial del jugador
        isNight = false; // Restablecer fondo a día
        extraLivesGiven = 0; // Reiniciar contador de vidas extra otorgadas
        if (pirate != null) {
            pirate.reiniciar(); // Garantiza que el pirata también se reinicie
        }
    }
}
