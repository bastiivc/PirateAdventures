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
        if (score >= 2000) {
            // Incremento del 1.3% por cada 100 puntos, desde 2000 en adelante
            float increment = ((score - 2000) / 100) * 0.013f;
            fallSpeed = 800 * (1 + increment);
            playerSpeed = 500 * (1 + increment);
        } else if (score >= 1500) {
            // Incremento del 1.3% por cada 100 puntos hasta 2000
            float increment = ((score - 1500) / 100) * 0.013f;
            fallSpeed = 700 * (1 + increment);
            playerSpeed = 450 * (1 + increment);
        } else if (score >= 1000) {
            // Incremento del 1.2% por cada 100 puntos hasta 1500
            float increment = ((score - 1000) / 100) * 0.012f;
            fallSpeed = 600 * (1 + increment);
            playerSpeed = 400 * (1 + increment);
            isNight = true; // Cambiar a modo noche
        } else {
            // Incremento del 1.1% por cada 100 puntos hasta 1000
            float increment = (score / 100) * 0.011f;
            fallSpeed = 300 * (1 + increment);
            playerSpeed = 280 * (1 + increment);
        }
    }

    public void reset() {
        score = 0;
        fallSpeed = 300; // Velocidad inicial de caída
        playerSpeed = 280; // Velocidad inicial del jugador
        isNight = false; // Restablecer fondo a día
        extraLivesGiven = 0;
        pirate = null; // Desvincular pirate si es necesario
    }


}
