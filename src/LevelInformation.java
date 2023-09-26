// Itay Zahor 208127480

import java.util.List;

/**
 * The interface Level information.
 *
 * @author Itay Zahor
 */
public interface LevelInformation {
    /**
     * Number of balls in the game.
     *
     * @return the Number of balls
     */
    int numberOfBalls();

    /**
     * Initial the balls velocities.
     *
     * @return the list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * The paddles speed.
     *
     * @return The paddles speed
     */
    int paddleSpeed();

    /**
     * The paddle's width.
     *
     * @return The paddle's width
     */
    int paddleWidth();

    /**
     * The levels name.
     *
     * @return The levels name
     */
    String levelName();

    /**
     * Gets background.
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * The game blocks list.
     *
     * @return The game blocks list
     */
    List<Block> blocks();

    /**
     * The number of blocks in the game.
     *
     * @return The number of blocks in the game
     */
    int numberOfBlocksToRemove();
}
