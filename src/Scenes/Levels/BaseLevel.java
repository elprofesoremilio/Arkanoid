package Scenes.Levels;

import Engine.Game;
import Engine.Scene;
import Engine.TextRenderer;
import Game.Config;
import Objects.Ball;
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

        // Condición de Victoria: No quedan ladrillos
        if (getCountOf(Brick.class) == 0) {
            game.setScene(getNextLevel());
        }

        // Condición de Derrota: Pelota cae (esto lo veremos en las vidas)
        if (ball.getY() > 600) {
            handleBallLoss();
        }
    }

    private void handleBallLoss() {
        // Por ahora, reiniciamos el nivel
        game.setScene(new Level1(game));
    }

    @Override
    public void render(Graphics2D g) {
        // Fondo genérico de nivel
        g.setColor(Config.LEVEL_BACKGROUND_COLOR);
        g.fillRect(0, 0, Config.GAME_WIDTH, Config.GAME_HEIGHT);

        // Dibujamos el número de nivel
        TextRenderer.draw(g, "Level " + levelNumber, 10, 20, new Font("Arial", Font.PLAIN, 20), Color.GRAY);

        super.render(g);
    }

    public void resetPositions() {
        player.setX((Config.GAME_WIDTH-Config.PLAYER_WIDTH)/2f);
        player.setY(Config.GAME_HEIGHT- Config.PLAYER_Y_OFFSET);
        ball.setX((Config.GAME_WIDTH-Config.BALL_RADIUS)/2f);
        ball.setY(Config.GAME_HEIGHT/2f + Config.BALL_Y_OFFSET);
        ball.setVelocity(Config.BALL_SPEED,Config.BALL_SPEED);
    }
}
