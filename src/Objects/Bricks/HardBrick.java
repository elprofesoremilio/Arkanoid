package Objects.Bricks;

import Engine.GameObject;
import Engine.Scene;
import Engine.SceneWithScore;
import Objects.Ball;

import java.awt.*;

public class HardBrick extends Brick {

    private int contadorGolpes;
    /**
     * Constructor base para un objeto de juego.
     *
     * @param x      Posición inicial en el eje X.
     * @param y      Posición inicial en el eje Y.
     * @param width  Ancho del objeto.
     * @param height Alto del objeto.
     * @param scene  Escena en la que está el ladrillo
     */
    public HardBrick(float x, float y, int width, int height, Scene scene) {
        super(x, y, width, height, scene);
        contadorGolpes = 2;
    }

    @Override
    public void render(Graphics2D g) {
        if (contadorGolpes!=1) {
            g.setColor(Color.DARK_GRAY);
        } else {
            g.setColor(Color.GRAY);
        }
        g.fillRect((int)x, (int)y, width, height);
    }

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof Ball) {
            contadorGolpes--;
            if (contadorGolpes==0) {
                scene.removeObject(this);
                if (scene instanceof SceneWithScore) {
                    ((SceneWithScore) scene).addPoint();
                }
            }
        }
    }
}
