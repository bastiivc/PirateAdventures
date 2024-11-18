package io.github.some_example_name;

/**
 * Clase que representa la estrategia de otorgar puntos al jugador.
 * Implementa la interfaz {@link ColisionEstrategia}, permitiendo que al producirse
 * una colisión con un objeto, se otorguen puntos al jugador.
 */
public class OtorgarPuntos implements ColisionEstrategia {

    private int puntos;

    /**
     * Constructor de la clase OtorgarPuntos.
     *
     * @param puntos La cantidad de puntos que se otorgarán al jugador al ejecutar la acción.
     */
    public OtorgarPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * Ejecuta la acción de otorgar puntos al jugador.
     * Añade la cantidad de puntos especificada al marcador global del juego.
     *
     * @param pirate El objeto {@link Pirate} involucrado en la colisión.
     */
    @Override
    public void ejecutarAccion(Pirate pirate) {
        GameManager.getInstance().addScore(puntos); // Añade puntos al marcador
    }
}
