package Objects;

import Engine.*;
import Game.Config;

import java.awt.*;

public class Player extends GameObject implements Collidable {
    private static final Color[] colors = {Config.PLAYER_COLOR, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    private float speed = 5.0f;
    private int colorIndex;
    private InputHandler input;

    public Player(float x, float y, int width, int height, Scene scene) {
        super(x, y, width, height, scene);
        this.colorIndex = 0;
        input = scene.getInput();
    }

    @Override
    public void update(float delta) {
        if (input.left) {
            this.x -= speed * delta;
        }
        if (input.right) {
            this.x += speed * delta;
        }

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(colors[colorIndex]);
        g.fillRect((int) x, (int) y, width, height);
        if (Config.DEBUG) {
            g.setColor(Config.DEBUG_COLOR);
            g.drawRect((int) x, (int) y, width, height);
        }
    }

    @Override
    public void onCollision(GameObject other) {
//        if (other.getClass() == Ball.class) {
//            colorIndex++;
//            if (colorIndex == 5) {
//                colorIndex = 0;
//            }
//        }
    }
}
