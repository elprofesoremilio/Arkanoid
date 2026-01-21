package Scenes.Levels;

import Engine.Game;
import Engine.Scene;
import Game.Config;
import Objects.DeadLine;
import Objects.bricks.Brick;
import Objects.bricks.HardBrick;
import Objects.bricks.MovableBrick;
import Scenes.VictoryScene;

import java.util.Random;

public class Level1 extends BaseLevel {
    private boolean running;

    public Level1(Game game, int levelNumber) {
        running = false;
        super(game, levelNumber);
    }

    @Override
    protected void initBricks() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                Random r = new Random();

                if (r.nextInt(5) == 3) {
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
        int movableBrickX = (int) (Math.random() * Config.GAME_WIDTH);
        if (movableBrickX > Config.GAME_WIDTH - Config.BRICK_WIDTH) {
            movableBrickX = Config.GAME_WIDTH - Config.BRICK_WIDTH;
        }
        this.addObject(new MovableBrick(
                movableBrickX,
                50 + (Config.BRICK_HEIGHT + Config.BRICK_GAP_Y) * 3,
                Config.BRICK_WIDTH,
                Config.BRICK_HEIGHT,
                this)
        );
    }

    @Override
    protected Scene getNextLevel() {
        return new VictoryScene(game, this.score);
    }
}
