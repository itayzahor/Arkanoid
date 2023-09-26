// Itay Zahor 208127480

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level 1 wide and easy level.
 *
 * @author Itay Zahor
 */
public class Level1 implements LevelInformation {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int Y_START_BLOCKS = GUI_HEIGHT / 2;
    private static final int BORDER_THICKNESS = 25;
    private static final int PADDLE_WIDTH = 550;
    private static final int PADDLE_SPEED = 5;
    private static final Color BACKGROUND_COLOR = Color.white;

    @Override
    public int numberOfBalls() {
        return 11;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<>();
        // directs the balls to different angles from 300 to 420 degrees
        int angleSpread = 120 / (numberOfBalls() - 1);
        for (int i = 0; i < numberOfBalls(); i++) {
            l.add(Velocity.fromAngleAndSpeed(300 + (i * angleSpread), 5));
        }
        return l;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Wide and Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Point(0, 0), GUI_WIDTH, GUI_HEIGHT,
                BACKGROUND_COLOR);
    }

    @Override
    public List<Block> blocks() {
        List<Block> l = new ArrayList<>();
        Color c = Color.red;
        // creates the blocks in different colors
        for (int i = 0; i < 15; i++) {
            if (i == 2) {
                c = Color.ORANGE;
            }
            if (i == 4) {
                c = Color.YELLOW;
            }
            if (i == 6) {
                c = Color.green;
            }
            if (i == 9) {
                c = Color.blue;
            }
            if (i == 11) {
                c = Color.PINK;
            }
            if (i == 13) {
                c = Color.CYAN;
            }
            Block block = new Block(new Point(BORDER_THICKNESS + i * BLOCK_WIDTH,
                    Y_START_BLOCKS), BLOCK_WIDTH,
                    BLOCK_HEIGHT, c);
            l.add(block);
        }
        return l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}

