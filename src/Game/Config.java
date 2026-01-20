package Game;

import java.awt.*;

public class Config {
    // Game properties
    public static final int GAME_WIDTH = 400;
    public static final int GAME_HEIGHT = 600;
    public static final int PLAYER_WIDTH = 80;
    public static final int PLAYER_HEIGHT = 10;
    public static final int BALL_RADIUS = 20;
    public static final int PLAYER_Y_OFFSET = 75;
    public static final int BALL_Y_OFFSET = -50;
    public static final int BRICK_WIDTH = 50;
    public static final int BRICK_HEIGHT = PLAYER_HEIGHT;
    public static final int BRICK_GAP_X = 10;
    public static final int BRICK_GAP_Y = 20;


    // Colores
    public static final Color PLAYER_COLOR = Color.BLACK;
    public static final Color BALL_COLOR = Color.BLUE;
    public static final Color BRICK_COLOR = new Color(150, 100,20);

    // Debug properties
    public static final boolean DEBUG = false;
    public static final Color DEBUG_COLOR = Color.red;
}
