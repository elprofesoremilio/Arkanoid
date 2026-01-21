package Scenes;

import Engine.Game;
import Engine.Scene;
import Engine.TextRenderer;
import Game.Config;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverScene extends Scene {

    public GameOverScene(Game game) {
        super(game);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (input.isKeyPressed(KeyEvent.VK_R)) {
            game.setScene(new Level1Scene(game));
        }
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        TextRenderer.drawHorizontalCentered(g, "GAME OVER", Config.GAME_WIDTH, 300, new Font("Arial", Font.BOLD, 40), Color.RED);
        TextRenderer.drawHorizontalCentered(g, "Pulsa R para reiniciar la partida", Config.GAME_WIDTH, 400,  new Font("Arial", Font.BOLD, 20), Color.RED);
    }
}
