package Objects;

import Engine.*;
import Game.Config;

import java.awt.*;

public class Brick extends GameObject implements Collidable {
    protected Scene scene;
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
        super(x, y, width, height);
        this.scene = scene;
    }

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof Ball) {
            scene.removeObject(this);
            if (scene instanceof SceneWithScore) {
                ((SceneWithScore) scene).addPoint();
            }
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
