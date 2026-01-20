package Game;

import Engine.Game;
import Scenes.Level1Scene;

public class MainArkanoid {
    static void main() {
        Game game = new Game(Config.GAME_WIDTH, Config.GAME_HEIGHT, "Arkanoid");
        game.setScene(new Level1Scene(game));
        game.start();
    }
}