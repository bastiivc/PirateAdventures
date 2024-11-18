package io.github.some_example_name;

/**
 * Clase Singleton que gestiona el estado global del juego.
 * Maneja la puntuación, velocidad de los objetos y el cambio de fondo,
 * así como las probabilidades de aparición de los diferentes elementos.
 */
public class GameManager {

    private static GameManager instance;

    // Variables globales del juego
    private int score = 0; // Puntuación actual del jugador
    private float fallSpeed = 300; // Velocidad inicial de caída de objetos
    private float playerSpeed = 280; // Velocidad inicial del jugador
    private boolean isNight = false; // Indica si es de noche en el juego
    private int extraLivesGiven = 0; // Contador de vidas otorgadas extra
    private Pirate pirate; // Referencia al jugador
    private int probabilidadMoneda = 60;  // Probabilidad inicial de monedas
    private int probabilidadTiburon = 20; // Probabilidad inicial de tiburones
    private int probabilidadEspada = 20;  // Probabilidad inicial de espadas

    /**
     * Establece la probabilidad de aparición de monedas.
     * @param probabilidad la nueva probabilidad de monedas.
     */
    public void setProbabilidadMoneda(int probabilidad) {
        probabilidadMoneda = probabilidad;
    }

    /**
     * Establece la probabilidad de aparición de tiburones.
     * @param probabilidad la nueva probabilidad de tiburones.
     */
    public void setProbabilidadTiburon(int probabilidad) {
        probabilidadTiburon = probabilidad;
    }

    /**
     * Establece la probabilidad de aparición de espadas.
     * @param probabilidad la nueva probabilidad de espadas.
     */
    public void setProbabilidadEspada(int probabilidad) {
        probabilidadEspada = probabilidad;
    }

    /**
     * Obtiene la probabilidad de aparición de monedas.
     * @return la probabilidad actual de monedas.
     */
    public int getProbabilidadMoneda() {
        return probabilidadMoneda;
    }

    /**
     * Obtiene la probabilidad de aparición de tiburones.
     * @return la probabilidad actual de tiburones.
     */
    public int getProbabilidadTiburon() {
        return probabilidadTiburon;
    }

    /**
     * Obtiene la probabilidad de aparición de espadas.
     * @return la probabilidad actual de espadas.
     */
    public int getProbabilidadEspada() {
        return probabilidadEspada;
    }

    // Constructor privado para evitar instancias externas
    private GameManager() {}

    /**
     * Obtiene la instancia única de GameManager.
     * @return la instancia única de GameManager.
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Añade puntos a la puntuación actual y ajusta la dificultad del juego.
     * También otorga vidas extra cada 300 puntos acumulados.
     * @param points la cantidad de puntos a añadir.
     */
    public void addScore(int points) {
        score += points;
        ajustarDificultad();

        // Lógica para otorgar vidas extra
        int livesToGive = score / 300; // Una vida extra por cada 300 puntos
        if (livesToGive > extraLivesGiven) {
            int newLives = livesToGive - extraLivesGiven;
            extraLivesGiven = livesToGive;
            if (pirate != null) {
                pirate.addLives(newLives);
            }
        }
    }

    /**
     * Obtiene la puntuación actual del jugador.
     * @return la puntuación actual.
     */
    public int getScore() {
        return score;
    }

    /**
     * Obtiene la velocidad actual de caída de objetos.
     * @return la velocidad de caída.
     */
    public float getFallSpeed() {
        return fallSpeed;
    }

    /**
     * Obtiene la velocidad actual del jugador.
     * @return la velocidad del jugador.
     */
    public float getPlayerSpeed() {
        return playerSpeed;
    }

    /**
     * Registra una referencia al objeto Pirate del jugador.
     * @param pirate el objeto Pirate que representa al jugador.
     */
    public void setPirate(Pirate pirate) {
        this.pirate = pirate;
    }

    /**
     * Verifica si el fondo del juego debe ser de noche.
     * @return true si es de noche, false de lo contrario.
     */
    public boolean isNight() {
        return isNight;
    }

    /**
     * Ajusta la dificultad del juego en función de la puntuación actual.
     * Cambia las velocidades de caída y de movimiento, así como las probabilidades de aparición de elementos.
     */
    private void ajustarDificultad() {
        int baseProbabilidadMoneda = 60;
        int baseProbabilidadTiburon = 20;
        int baseProbabilidadEspada = 20;

        if (score >= 2000) {
            float increment = ((score - 2000) / 100) * 0.014f;
            fallSpeed = 800 * (1 + increment);
            playerSpeed = 500 * (1 + increment);

            baseProbabilidadMoneda = 50;
            baseProbabilidadTiburon = 30;
            baseProbabilidadEspada = 20;

        } else if (score >= 1500) {
            float increment = ((score - 1500) / 100) * 0.013f;
            fallSpeed = 700 * (1 + increment);
            playerSpeed = 450 * (1 + increment);

            baseProbabilidadMoneda = 55;
            baseProbabilidadTiburon = 25;
            baseProbabilidadEspada = 20;

        } else if (score >= 1000) {
            float increment = ((score - 1000) / 100) * 0.012f;
            fallSpeed = 600 * (1 + increment);
            playerSpeed = 400 * (1 + increment);
            isNight = true;

            baseProbabilidadMoneda = 60 - (int) ((increment / 0.012f) * 2);
            baseProbabilidadTiburon = 20 + (int) ((increment / 0.012f) * 1);
            baseProbabilidadEspada = 20 + (int) ((increment / 0.012f) * 1);

        } else {
            float increment = (score / 100) * 0.011f;
            fallSpeed = 300 * (1 + increment);
            playerSpeed = 280 * (1 + increment);

            baseProbabilidadMoneda = 60;
            baseProbabilidadTiburon = 20;
            baseProbabilidadEspada = 20;
        }

        actualizarProbabilidades(baseProbabilidadMoneda, baseProbabilidadTiburon, baseProbabilidadEspada);
    }

    /**
     * Actualiza las probabilidades de aparición de los elementos en el juego.
     * @param probMoneda la probabilidad de monedas.
     * @param probTiburon la probabilidad de tiburones.
     * @param probEspada la probabilidad de espadas.
     */
    private void actualizarProbabilidades(int probMoneda, int probTiburon, int probEspada) {
        setProbabilidadMoneda(probMoneda);
        setProbabilidadTiburon(probTiburon);
        setProbabilidadEspada(probEspada);
    }

    /**
     * Reinicia el estado global del juego a los valores iniciales.
     * Restablece puntuación, velocidades y fondo.
     */
    public void reset() {
        score = 0;
        fallSpeed = 300;
        playerSpeed = 280;
        isNight = false;
        extraLivesGiven = 0;
        if (pirate != null) {
            pirate.reiniciar();
        }
    }
}
