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
    public void render(Graphics2D g) {
        super.render(g);
        Font font = new Font("Arial", Font.BOLD, 30);
        g.setFont(font);
        g.setColor(Color.red);
        g.drawString("GAME OVER", 100, 250);
        font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.drawString("Pulse R para reiniciar", 90, 340);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (input.isKeyPressed(KeyEvent.VK_R)) {
            game.setScene(new Pantalla1(game));
        }
    }
}
