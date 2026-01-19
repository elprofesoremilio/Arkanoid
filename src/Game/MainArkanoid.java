package Game;

import Engine.Game;
import Scenes.Pantalla1;

public class MainArkanoid {
    static void main() {
        Game game = new Game(Config.GAME_WIDTH, Config.GAME_HEIGHT, "Arkanoid");
        game.setScene(new Pantalla1(game));
        game.start();
    }
}