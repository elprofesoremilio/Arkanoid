package Scenes;

import Engine.Game;
import Engine.Scene;
import Objects.Ball;
import Game.Config;
import Objects.Brick;
import Objects.DeadLine;
import Objects.Player;

import java.awt.*;

public class Pantalla1 extends Scene {
    public Pantalla1(Game game) {
        super(game);
        Player player = new Player(
                Config.GAME_WIDTH/2f-Config.PLAYER_WIDTH/2f,
                Config.GAME_HEIGHT - Config.PLAYER_Y_OFFSET,
                Config.PLAYER_WIDTH,
                Config.PLAYER_HEIGHT
        );
        this.addObject(player);
        this.addObject(new Ball(Config.GAME_WIDTH/2f - Config.BALL_RADIUS/2f, Config.GAME_HEIGHT/2f + Config.BALL_Y_OFFSET, Config.BALL_RADIUS));
        this.addObject(new DeadLine());
        for (int i=0; i<6; i++) {
            for (int j = 0; j < 3; j++) {
                this.addObject(
                    new Brick(
                        25 + (Config.BRICK_WIDTH + Config.BRICK_GAP_X) * i,
                        50 + (Config.BRICK_HEIGHT + Config.BRICK_GAP_Y) * j,
                        Config.BRICK_WIDTH,
                        Config.BRICK_HEIGHT,
                        this
                    )
                );
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setBackground(Color.WHITE);
        g.clearRect(0,0, Config.GAME_WIDTH, Config.GAME_HEIGHT);
        super.render(g);
    }
}
