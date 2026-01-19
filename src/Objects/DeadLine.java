package Objects;

import Engine.GameObject;
import Game.Config;

import java.awt.*;

public class DeadLine extends GameObject {
    public DeadLine() {
        super(0, Config.GAME_HEIGHT-5, Config.GAME_WIDTH, 5);
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
