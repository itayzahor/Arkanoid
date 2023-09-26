// Itay Zahor 208127480

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level 2 Direct Hit.
 *
 * @author Itay Zahor
 */
public class Level2 implements LevelInformation {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int BLOCK_WIDTH = 30;
    private static final int BLOCK_HEIGHT = 30;
    private static final int Y_START_BLOCKS = 200;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_SPEED = 7;
    private static final double BALL_SPEED = 5;
    private static final Color BACKGROUND_COLOR = Color.black;

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<>();
        l.add(Velocity.fromAngleAndSpeed(180, BALL_SPEED));
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Point(0, 0), GUI_WIDTH, GUI_HEIGHT,
                BACKGROUND_COLOR);
    }

    @Override
    public List<Block> blocks() {
        List<Block> l = new ArrayList<>();
        Block block =
                new Block(new Point((((double) GUI_WIDTH / 2) - ((double) BLOCK_WIDTH / 2)),
                Y_START_BLOCKS),
                BLOCK_WIDTH,
                BLOCK_HEIGHT, Color.red);
        l.add(block);
        return l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}


