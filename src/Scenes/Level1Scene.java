package Scenes;

import Engine.Game;
import Engine.SceneWithScore;
import Engine.TextRenderer;
import Objects.*;
import Game.Config;
import Objects.bricks.Brick;
import Objects.bricks.HardBrick;
import Objects.bricks.MovableBrick;

import java.awt.*;
import java.util.Random;

public class Level1Scene extends SceneWithScore {
    private boolean running;

    public Level1Scene(Game game) {
        super(game);
        running = false;
        Random r = new Random();

        Player player = new Player(
                Config.GAME_WIDTH/2f-Config.PLAYER_WIDTH/2f,
                Config.GAME_HEIGHT - Config.PLAYER_Y_OFFSET,
                Config.PLAYER_WIDTH,
                Config.PLAYER_HEIGHT,
                this
        );
        this.addObject(player);
        this.addObject(new Ball(
                Config.GAME_WIDTH/2f - Config.BALL_RADIUS/2f,
                Config.GAME_HEIGHT/2f + Config.BALL_Y_OFFSET,
                Config.BALL_RADIUS,
                this)
        );
        for (int i=0; i<6; i++) {
            for (int j = 0; j < 3; j++) {
                if (r.nextInt(5)==3) {
                    this.addObject(
                            new HardBrick(
                                    25 + (Config.BRICK_WIDTH + Config.BRICK_GAP_X) * i,
                                    50 + (Config.BRICK_HEIGHT + Config.BRICK_GAP_Y) * j,
                                    Config.BRICK_WIDTH,
                                    Config.BRICK_HEIGHT,
                                    this
                            )
                    );
                } else {
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
        int movableBrickX = (int)(Math.random()*Config.GAME_WIDTH);
        if (movableBrickX>Config.GAME_WIDTH-Config.BRICK_WIDTH) {
            movableBrickX = Config.GAME_WIDTH-Config.BRICK_WIDTH;
        }
        this.addObject(new MovableBrick(
                movableBrickX,
                50 + (Config.BRICK_HEIGHT + Config.BRICK_GAP_Y) * 3,
                Config.BRICK_WIDTH,
                Config.BRICK_HEIGHT,
                this)
        );
        this.addObject(new DeadLine(this));
    }

    @Override
    public void render(Graphics2D g) {
        g.setBackground(Color.WHITE);
        g.clearRect(0,0, Config.GAME_WIDTH, Config.GAME_HEIGHT);
        super.render(g);
        if (!running) {
            g.setFont(Config.DEFAULT_FONT);
            TextRenderer.drawCentered(g,"Pulsa ESPACIO para empezar", Config.GAME_WIDTH, Config.GAME_HEIGHT, Config.DEFAULT_FONT, Color.BLUE);
        }

    }

    @Override
    public void update(float delta) {
        if (running) {
            super.update(delta);
        } else if (input.space ){
            running = true;
        }
    }
}
