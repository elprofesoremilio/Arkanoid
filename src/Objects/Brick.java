package Objects;

import Engine.Collidable;
import Engine.GameObject;
import Engine.Scene;
import Game.Config;

import java.awt.*;

public class Brick extends GameObject implements Collidable {
    private Scene scene;
    /**
     * Constructor base para un objeto de juego.
     *
     * @param x      Posición inicial en el eje X.
     * @param y      Posición inicial en el eje Y.
     * @param width  Ancho del objeto.
     * @param height Alto del objeto.
     */
    public Brick(float x, float y, int width, int height, Scene scene) {
        super(x, y, width, height);
        this.scene = scene;
    }

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof Ball) {
            scene.removeObject(this);
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
