package io.github.some_example_name;

public class QuitarVida implements ColisionEstrategia {
    @Override
    public void ejecutarAccion(Pirate pirate) {
        pirate.dañar(); // Resta una vida al jugador
    }
}
