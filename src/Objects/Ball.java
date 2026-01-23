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
        if (other instanceof Player || other instanceof Brick) {
            Rectangle interseccion = this.getBounds().intersection(other.getBounds());

            if (interseccion.width < interseccion.height) {
                // Colisión Lateral
                if (this.x < other.getX()) {
                    speedX = -Math.abs(speedX);
                    this.x -= interseccion.width;
                } else {
                    speedX = Math.abs(speedX);
                    this.x += interseccion.width;
                }
            } else {
                // Colisión Superior/Inferior
                speedY = -speedY;
                if (this.y < other.getY()) this.y -= interseccion.height;
                else this.y += interseccion.height;

                // --- LÓGICA DE JUGABILIDAD EN LA PALA ---
                if (other instanceof Player) {
                    float centroPelota = this.x + (this.width / 2f);
                    float centroPlayer = other.getX() + (other.getWidth() / 2f);

                    // Calculamos dónde golpeó (-1 izquierda, 0 centro, 1 derecha)
                    float relativaX = (centroPelota - centroPlayer) / (other.getWidth() / 2f);

                    // Aplicamos la nueva velocidad horizontal basada en Config
                    this.speedX = relativaX * Config.BALL_SPEED_X_MAX;

                    // SEGURIDAD: Aseguramos que la velocidad Y no sea demasiado lenta tras el rebote
                    if (Math.abs(speedY) < Config.BALL_SPEED_Y_MIN) {
                        speedY = (speedY < 0) ? -Config.BALL_SPEED_Y_MIN : Config.BALL_SPEED_Y_MIN;
                    }
                }
            }
        } else if (other instanceof DeadLine) {
            gameState.loseLife();

            if (gameState.getLives() > 0) {
                // Si le quedan vidas, reseteamos posiciones en el nivel actual
                ((BaseLevel)scene).resetPositions();
                ((BaseLevel)scene).setRunning(false);
            } else {
                // Si no, Game Over directo
                scene.getGame().setScene(new GameOverScene(scene.getGame()));
            }
        }
    }
}
