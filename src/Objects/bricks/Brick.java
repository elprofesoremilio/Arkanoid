package Objects.bricks;

import Engine.*;
import Game.Config;
import Objects.Ball;

import java.awt.*;

public class Brick extends GameObject implements Collidable {

    protected boolean unbreakeable;

    /**
     * Constructor base para un objeto de juego.
     *
     * @param x      Posición inicial en el eje X.
     * @param y      Posición inicial en el eje Y.
     * @param width  Ancho del objeto.
     * @param height Alto del objeto.
     * @param scene  Escena en la que está el ladrillo
     */
    public Brick(float x, float y, int width, int height, Scene scene) {
        super(x, y, width, height, scene);
        this.unbreakeable = false;
    }

    /**
     * Indica si el ladrillo es indestructible.
     * @param unbreakeable true si es indestructible, false en caso contrario.
     */
    public void setUnbreakeable(boolean unbreakeable) {
        this.unbreakeable = unbreakeable;
    }

    /**
     * Comprueba si el ladrillo es indestructible.
     * @return true si es indestructible, false en caso contrario.
     */
    public boolean isUnbreakeable() {
        return unbreakeable;
    }

    /**
     * Maneja la colisión con otro objeto de juego.
     * @param other El otro objeto de juego con el que colisiona.
     */
    @Override
    public void onCollision(GameObject other) {
        if (!unbreakeable && other instanceof Ball) {
            scene.removeObject(this);
            scene.getGame().getGameState().addScore(1);
        }
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Config.BRICK_COLOR);
        g.fillRect((int)x, (int)y, width, height);
    }
}
