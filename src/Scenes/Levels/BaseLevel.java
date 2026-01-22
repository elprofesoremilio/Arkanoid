package Scenes.Levels;

import Engine.Game;
import Engine.Scene;
import Engine.TextRenderer;
import Game.Config;
import Objects.Ball;
import Objects.DeadLine;
import Objects.Player;
import Objects.bricks.Brick;

import java.awt.*;

public abstract class BaseLevel extends Scene {

    protected Player player;
    protected Ball ball;
    protected int levelNumber;

    public BaseLevel(Game game, int levelNumber) {
        super(game);
        this.levelNumber = levelNumber;
        game.getGameState().setLevel(levelNumber);

        // Inicializamos lo común
        this.player = new Player(
                Config.GAME_WIDTH/2f- Config.PLAYER_WIDTH/2f,
                Config.GAME_HEIGHT - Config.PLAYER_Y_OFFSET,
                Config.PLAYER_WIDTH,
                Config.PLAYER_HEIGHT,
                this
        );
        this.ball = new Ball(
                Config.GAME_WIDTH/2f - Config.BALL_RADIUS/2f,
                Config.GAME_HEIGHT/2f + Config.BALL_Y_OFFSET,
                Config.BALL_RADIUS,
                this
        );

        this.addObject(player);
        this.addObject(ball);
        this.addObject(new DeadLine(this));

        // Llamamos al método que cada nivel concreto implementará
        initBricks();
    }

    /**
     * Cada nivel hijo decidirá dónde poner sus ladrillos.
     */
    protected abstract void initBricks();

    /**
     * El siguiente nivel al que pasaremos.
     */
    protected abstract Scene getNextLevel();

    @Override
    public void update(float delta) {
        super.update(delta);

        // Contamos solo los ladrillos que NO son indestructibles
        int breakableBricks = countIf(obj ->
                obj instanceof Brick && !((Brick) obj).isUnbreakeable()
        );

        if (breakableBricks == 0) {
            game.setScene(getNextLevel());
        }

    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        // Dibujamos el número de nivel
        TextRenderer.drawHorizontalCentered(g, "Level " + levelNumber, game.getWidth(), 20, new Font("Arial", Font.PLAIN, 20), Color.GRAY);
        String scoreString = String.format("%s%02d", Config.SCORE_TEXT, game.getGameState().getScore());
        TextRenderer.draw(g, scoreString, 10, 20, new Font("Arial", Font.BOLD, 20), Color.BLUE);
        TextRenderer.draw(g, String.format("Lives: %02d", game.getGameState().getLives()), 300, 20, new Font("Arial", Font.BOLD, 20), Color.BLUE);
    }

    public void resetPositions() {
        player.setX((Config.GAME_WIDTH-Config.PLAYER_WIDTH)/2f);
        player.setY(Config.GAME_HEIGHT- Config.PLAYER_Y_OFFSET);
        ball.setX((Config.GAME_WIDTH-Config.BALL_RADIUS)/2f);
        ball.setY(Config.GAME_HEIGHT/2f + Config.BALL_Y_OFFSET);
        ball.setVelocity(Config.BALL_SPEED,Config.BALL_SPEED);
    }
}
