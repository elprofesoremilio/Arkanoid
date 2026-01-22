package Game;

import Engine.Game;
import Scenes.Levels.Level0;
import Scenes.Levels.Level1;

public class MainArkanoid {
    static void main() {
        Game game = new Game(Config.GAME_WIDTH, Config.GAME_HEIGHT, "Arkanoid");
        game.setScene(new Level0(game,0));
        game.start();
    }
}