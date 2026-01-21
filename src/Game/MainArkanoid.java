package Game;

import Engine.Game;
import Scenes.Levels.Level1;

public class MainArkanoid {
    static void main() {
        Game game = new Game(Config.GAME_WIDTH, Config.GAME_HEIGHT, "Arkanoid");
        game.setScene(new Level1(game,1));
        game.start();
    }
}