package io.github.some_example_name;

public class MultiplicarPuntos implements ColisionEstrategia {
    private int factor;

    public MultiplicarPuntos(int factor) {
        this.factor = factor;
    }

    @Override
    public void ejecutarAccion(Pirate pirate) {
        pirate.sumarPuntos(pirate.getPuntos() * (factor - 1)); // Multiplica los puntos actuales
    }
}

