package io.github.some_example_name;

/**
 * Clase que representa la estrategia de otorgar puntos al jugador.
 */
public class OtorgarPuntos implements ColisionEstrategia {

    private int puntos;

    public OtorgarPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public void ejecutarAccion(Pirate pirate) {
        GameManager.getInstance().addScore(puntos); // Añade puntos al marcador
    }
}
