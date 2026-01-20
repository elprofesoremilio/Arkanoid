package Scenes;

import Engine.Game;
import Engine.Scene;

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
        Font font = new Font("Arial", Font.BOLD, 40);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("GAME OVER", 80, 250);
        font = new  Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.drawString("Pulsa R para reiniciar la partida", 45, 350);
    }
}
