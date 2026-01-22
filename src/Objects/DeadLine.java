package Objects;

import Engine.Collidable;
import Engine.GameObject;
import Engine.Scene;
import Game.Config;
import Scenes.GameOverScene;

import java.awt.*;

public class DeadLine extends GameObject implements Collidable {
    @Override
    public void onCollision(GameObject other) {
    }

    public DeadLine(Scene scene) {
        super(0, Config.GAME_HEIGHT-5, Config.GAME_WIDTH, 5, scene);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect((int)x,(int)y,width,height);
    }
}
