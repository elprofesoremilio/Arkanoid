package Scenes;

import Engine.Game;
import Engine.Scene;
import Engine.TextRenderer;
import Game.Config;
import Scenes.Levels.Level0;
import Scenes.Levels.Level1;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverScene extends Scene {

    public GameOverScene(Game game) {
        super(game);
    }

    @Override
    public void update(float delta) {
        // Al pulsar Espacio, reseteamos el estado y volvemos al Nivel 1
        if (game.getInput().isKeyPressed(KeyEvent.VK_SPACE)) {
            game.getGameState().reset();
            game.setScene(new Level0(game, 0));
        }
    }

    @Override
    public void render(Graphics2D g) {
        // Fondo negro con un toque de rojo
        g.setColor(new Color(40, 0, 0));
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        Font fontGrande = new Font("Arial", Font.BOLD, 60);
        Font fontMedia = new Font("Arial", Font.BOLD, 24);
        Font fontPequena = new Font("Arial", Font.PLAIN, 18);

        // Usamos el TextRenderer que creamos antes
        TextRenderer.drawCentered(g, "GAME OVER", game.getWidth(), game.getHeight() - 100, fontGrande, Color.RED, null);

        String scoreFinal = "PUNTUACIÓN FINAL: " + game.getGameState().getScore();
        TextRenderer.drawCentered(g, scoreFinal, game.getWidth(), game.getHeight(), fontMedia, Color.WHITE, null);

        TextRenderer.drawCentered(g, "Pulsa ESPACIO para reintentar", game.getWidth(), game.getHeight() + 150, fontPequena, Color.GRAY, null);
    }
}
