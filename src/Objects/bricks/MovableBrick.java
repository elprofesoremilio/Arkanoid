package Objects.bricks;

import Engine.Scene;
import Game.Config;

public class MovableBrick extends Brick {
    /**
     * Constructor base para un objeto de juego.
     *
     * @param x      Posición inicial en el eje X.
     * @param y      Posición inicial en el eje Y.
     * @param width  Ancho del objeto.
     * @param height Alto del objeto.
     * @param scene  Escena en la que está el ladrillo
     */
    public MovableBrick(float x, float y, int width, int height, Scene scene) {
        super(x, y, width, height, scene);
        speedX = 2f;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        x += speedX * delta;
        if (x > Config.GAME_WIDTH - width || x < 0) {
            speedX = -speedX;
        }
    }
}
