package Scenes;

import Engine.Game;
import Engine.Scene;
import Objects.Ball;
import Game.Config;
import Objects.DeadLine;
import Objects.Player;

import java.awt.*;

public class Pantalla1 extends Scene {
    public Pantalla1(Game game) {
        super(game);
        Player player = new Player(
                Config.GAME_WIDTH/2f-Config.PLAYER_WIDTH/2f,
                Config.GAME_HEIGHT/2f+Config.GAME_HEIGHT/4f,
                Config.PLAYER_WIDTH,
                Config.PLAYER_HEIGHT
        );
        this.addObject(player);
        this.addObject(new Ball(Config.GAME_WIDTH/2f - Config.BALL_RADIUS/2f, 150, Config.BALL_RADIUS));
        this.addObject(new DeadLine());
    }

    @Override
    public void render(Graphics2D g) {
        g.setBackground(Color.WHITE);
        g.clearRect(0,0, Config.GAME_WIDTH, Config.GAME_HEIGHT);
        super.render(g);
    }
}
