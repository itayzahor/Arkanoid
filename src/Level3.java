// Itay Zahor 208127480

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level 3 hard level with a lot of blocks.
 *
 * @author Itay Zahor
 */
public class Level3 implements LevelInformation {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int Y_START_BLOCKS = 100;
    private static final int NUM_BLOCKS_FIRST_ROW = 12;
    private static final int NUM_BLOCKS_ROWS = 6;
    private static final int BORDER_THICKNESS = 25;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_SPEED = 5;
    private static final Color BACKGROUND_COLOR = Color.PINK;

    @Override
    public int numberOfBalls() {
        return 3;
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
        return "Blocks and Blocks";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Point(0, 0), GUI_WIDTH, GUI_HEIGHT,
                BACKGROUND_COLOR);
    }

    @Override
    public List<Block> blocks() {
        List<Block> l = new ArrayList<>();
        Color[] colors = new Color[]{Color.magenta, Color.red, Color.yellow, Color.green,
                Color.ORANGE, Color.blue};
        int startX = GUI_WIDTH - BORDER_THICKNESS - BLOCK_WIDTH;
        for (int i = 0; i < NUM_BLOCKS_ROWS; i++) {
            // generate random color for each line
            Color c = colors[i];
            for (int j = 0; j < NUM_BLOCKS_FIRST_ROW - i; j++) {
                // create the block and add it to the game
                Block block = new Block(new Point(startX - j * BLOCK_WIDTH,
                        Y_START_BLOCKS + i * BLOCK_HEIGHT), BLOCK_WIDTH,
                        BLOCK_HEIGHT, c);
                l.add(block);
            }
        }
        return l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
