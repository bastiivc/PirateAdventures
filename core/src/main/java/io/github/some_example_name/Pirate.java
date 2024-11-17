package io.github.some_example_name;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * La clase Pirate representa el personaje controlado por el jugador en el juego.
 * El pirata se mueve por la pantalla, recolecta objetos y puede perder vidas si es dañado.
 * Además, tiene una animación basada en diferentes sprites y direcciones de movimiento.
 */

public class Pirate implements Destruible, Dibujable{
    private Rectangle bucket;
    private Texture bucketImage;
    private TextureRegion[][] pirateRegions; // Matriz para los sprites del pirata
    private Sound sonidoHerido;

    private int vidas = 3;
    private int puntos;
    private int velx = 280;
    private boolean herido = false;
    private final int tiempoHeridoMax = 50;
    private int tiempoHerido;
    private int direccion; // 0 = Frontal (cara), 1 = Lado, 2 = Espalda
    private int frameActual = 0; // Frame actual de la animación
    private boolean flipX = false; // Indica si se debe voltear el sprite horizontalmente
    private static final float MARGEN_SUPERIOR = 125f; // valor para definir un margen superior posteriormente

    /**
     * Constructor de la clase Pirate.
     *
     * @param tex La textura que representa el sprite del pirata.
     * @param ss  El sonido que se reproduce cuando el pirata es herido.
     */

    public Pirate(Texture tex, Sound ss) {
        bucketImage = tex;
        sonidoHerido = ss;

        // Dividir la imagen en una matriz de regiones de 32x32
        pirateRegions = TextureRegion.split(bucketImage, 32, 32);
    }

    /**
     * Inicializa el área de colisión del pirata y lo posiciona en la parte inferior de la pantalla.
     */

    public void crear() {
        bucket = new Rectangle();
        bucket.width = 64; // Tamaño del sprite
        bucket.height = 64;

        bucket.x = (MyGame.WORLD_WIDTH - bucket.width) / 2;
        bucket.y = 20; // Posición inicial en Y
    }

    /**
     * Dibuja el pirata en la pantalla.
     * Si el pirata está herido, se aplica un efecto de sacudida.
     *
     * @param batch El SpriteBatch utilizado para dibujar el pirata.
     */

    @Override
    public void dibujar(SpriteBatch batch) {
        // Verificar que los índices no estén fuera del rango de la matriz
        if (frameActual >= pirateRegions.length ||
            direccion >= pirateRegions[frameActual].length) {
            // Establecer valores por defecto si los índices no son válidos
            frameActual = 0;
            direccion = 0;
        }

        // Seleccionar la región adecuada del sprite según el frame y la dirección
        TextureRegion region = new TextureRegion(pirateRegions[frameActual][direccion]);

        // Aplicar flip horizontal si es necesario
        if (flipX != region.isFlipX()) {
            region.flip(true, false);
        }

        if (!herido) {
            // Dibujar con el tamaño del bucket (asegurar consistencia)
            batch.draw(region, bucket.x, bucket.y, bucket.width, bucket.height);
        } else {
            // Efecto de sacudida cuando está herido
            batch.draw(region, bucket.x, bucket.y + MathUtils.random(-5, 5), bucket.width, bucket.height);
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
    }

    /**
     * Actualiza el movimiento del pirata en función de las teclas presionadas y ajusta su velocidad.
     * Si el pirata está herido, no puede moverse hasta que se recupere.
     */

    public void actualizarMovimiento() {
        if (herido) {
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
            return; // No actualizar movimiento si está herido
        }

        // Obtener velocidad del jugador desde GameManager
        velx = (int) GameManager.getInstance().getPlayerSpeed();

        boolean moving = false;

        boolean up = Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S);
        boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);

        float deltaTime = Gdx.graphics.getDeltaTime();

        // Movimiento y actualización de posición
        if (left && !right) {
            bucket.x -= velx * deltaTime;
            moving = true;
            flipX = true;
        } else if (right && !left) {
            bucket.x += velx * deltaTime;
            moving = true;
            flipX = false;
        }

        if (up && !down) {
            bucket.y += velx * deltaTime;
            moving = true;
        } else if (down && !up) {
            bucket.y -= velx * deltaTime;
            moving = true;
        }

        // Asegurar que el personaje no se salga de los límites del mundo virtual
        bucket.x = MathUtils.clamp(bucket.x, 0, MyGame.WORLD_WIDTH - bucket.width);
        bucket.y = MathUtils.clamp(bucket.y, 0, MyGame.WORLD_HEIGHT - bucket.height - MARGEN_SUPERIOR);

        // Determinar dirección y actualizar animación
        if (moving) {
            if (left || right) {
                direccion = 1; // Lado
            } else if (down) {
                direccion = 0; // Frontal
            } else if (up) {
                direccion = 2; // Espalda
            }
            frameActual = (frameActual + 1) % 4;
        } else {
            direccion = 2; // Espalda al estar parado
            frameActual = 0;
        }
    }

    /**
     * Resta una vida al pirata cuando recibe daño, reproduce un sonido de herido y aplica un efecto de sacudida.
     * Si las vidas llegan a 0, no puede perder más.
     */

    public void dañar() {
        if (vidas > 0) {
            vidas--;
            herido = true;
            tiempoHerido = tiempoHeridoMax;
            sonidoHerido.play();
        }
        if (vidas <= 0) {
            vidas = 0;
        }
    }

    /**
     * Libera los recursos utilizados por el pirata, como la textura.
     */

    @Override
    public void destruir() {
        bucketImage.dispose();
    }

    /**
     * Verifica si el pirata está en estado herido.
     *
     * @return true si el pirata está herido, false de lo contrario.
     */

    public boolean estaHerido() {
        return herido;
    }

    /**
     * Reinicia el estado del pirata a sus valores iniciales: 3 vidas y 0 puntos.
     * Coloca el pirata en su posición inicial.
     */

    public void reiniciar() {
        vidas = 3;
        puntos = 0;
        bucket.x = (MyGame.WORLD_WIDTH - bucket.width) / 2;
        bucket.y = 20;
    }

    /**
     * Obtiene el número de vidas restantes del pirata.
     *
     * @return El número de vidas actuales.
     */

    public int getVidas() {
        return vidas;
    }

    /**
     * Obtiene los puntos acumulados por el pirata.
     *
     * @return El número de puntos actuales.
     */

    public int getPuntos() {
        return puntos;
    }

    /**
     * Obtiene el área de colisión del pirata.
     *
     * @return Un objeto Rectangle que representa el área de colisión.
     */

    public Rectangle getArea() {
        return bucket;
    }

    /**
     * Suma puntos al total acumulado por el pirata.
     *
     * @param pp La cantidad de puntos a sumar.
     */

    public void sumarPuntos(int pp) {
        GameManager.getInstance().addScore(pp);
    }

    public void addLives(int lives) {
        vidas += lives;
        System.out.println("¡Has ganado " + lives + " vida(s)! Vidas actuales: " + vidas);
    }
}
