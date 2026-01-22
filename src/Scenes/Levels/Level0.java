package Scenes.Levels;

import Engine.Game;
import Engine.Scene;
import Game.Config;
import Objects.bricks.Brick;
import Objects.bricks.MovableBrick;

import java.util.Random;

public class Level0 extends BaseLevel {
    private boolean running;

    public Level0(Game game, int levelNumber) {
        running = false;
        super(game, levelNumber);
    }

    @Override
    protected void initBricks() {
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
        return new Level1(game, 1);
    }
}
