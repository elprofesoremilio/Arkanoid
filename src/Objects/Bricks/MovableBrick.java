package Objects.Bricks;

import Engine.GameObject;
import Engine.Scene;
import Game.Config;

public class MovableBrick extends Brick {
    private float speedX;
    private boolean undestructible;
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
        undestructible = false;
    }

    public void setUndestructible(boolean undestructible) {
        this.undestructible = undestructible;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.x += speedX*delta;
        if (x+Config.BRICK_WIDTH> Config.GAME_WIDTH || x < 0) {
            speedX = -speedX;
        }
    }

    @Override
    public void onCollision(GameObject other) {
        if (!undestructible) {
            super.onCollision(other);
        }
    }
}
