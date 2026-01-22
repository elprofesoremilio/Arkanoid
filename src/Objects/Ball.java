package Objects;

import Engine.Collidable;
import Engine.GameObject;
import Engine.GameState;
import Engine.Scene;
import Game.Config;
import Objects.bricks.Brick;
import Scenes.GameOverScene;
import Scenes.Levels.BaseLevel;

import java.awt.*;

public class Ball extends GameObject implements Collidable {

    private int radius;
    private GameState gameState;

    /**
     * Constructor base para un objeto de juego.
     *
     * @param x      Posición inicial en el eje X.
     * @param y      Posición inicial en el eje Y.
     * @param radius Radio de la pelota.
     */
    public Ball(float x, float y, int radius, BaseLevel scene) {
        super(x, y, radius, radius, scene);
        this.radius = radius;
        speedX = speedY = Config.BALL_SPEED;
        gameState = scene.getGame().getGameState();
    }

    @Override
    public void update(float delta) {
        if (x > Config.GAME_WIDTH - radius || x < 0) {
            speedX = -speedX;
            x = (x<0) ? 0 : Config.GAME_WIDTH - radius;
        }
        if (y > Config.GAME_HEIGHT - radius || y < 0) {
            speedY = -speedY;
            y = (y<0) ? 0 : Config.GAME_HEIGHT - radius;
        }
        x += speedX * delta;
        y += speedY * delta;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Config.BALL_COLOR);
        g.fillOval((int) x, (int) y, radius, radius);
        if (Config.DEBUG) {
            g.setColor(Config.DEBUG_COLOR);
            g.drawRect((int) x, (int) y, width, height);
        }
    }

    @Override
    public void onCollision(GameObject other) {
        if (other.getClass() == Player.class || other instanceof Brick) {
            // Calculamos el rectángulo de intersección para saber cuánto se han metido uno en otro
            Rectangle interseccion = this.getBounds().intersection(other.getBounds());

            if (interseccion.width < interseccion.height) {
                // La colisión es lateral (el solapamiento es más estrecho que alto)
                speedX = -speedX;

                // CORRECCIÓN: Sacamos a la pelota del objeto para evitar el rebotado infinito
                if (this.x < other.getX()) this.x -= interseccion.width;
                else this.x += interseccion.width;

            } else {
                // La colisión es por arriba o por abajo
                speedY = -speedY;

                // CORRECCIÓN: Sacamos a la pelota del objeto
                if (this.y < other.getY()) this.y -= interseccion.height;
                else this.y += interseccion.height;
            }
        } else if (other instanceof DeadLine) {
            gameState.loseLife();

            if (gameState.getLives() > 0) {
                // Si le quedan vidas, reseteamos posiciones en el nivel actual
                ((BaseLevel)scene).resetPositions();
            } else {
                // Si no, Game Over directo
                gameState.reset(); // Opcional, según si quieres que el Game Over muestre el score final
                scene.getGame().setScene(new GameOverScene(scene.getGame()));
            }
        }
    }
}
