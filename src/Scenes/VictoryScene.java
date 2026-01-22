package Scenes;

import Engine.Game;
import Engine.Scene;
import Engine.TextRenderer;
import Game.Config;
import Scenes.Levels.Level1;

import java.awt.*;
import java.awt.event.KeyEvent;

public class VictoryScene extends Scene {

    public VictoryScene(Game game) {
        super(game);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (input.isKeyPressed(KeyEvent.VK_N)) {
            game.setScene(new Level1(game,1));
        }
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        TextRenderer.drawHorizontalCentered(g, "YOU WIN! -> "+game.getGameState().getScore()+" points", Config.GAME_WIDTH, 300, new Font("Arial", Font.BOLD, 40), Color.BLUE);
        TextRenderer.drawHorizontalCentered(g, "Pulsa N para empezar otra partida", Config.GAME_WIDTH, 400,  new Font("Arial", Font.PLAIN, 20), Color.BLUE);
    }
}
