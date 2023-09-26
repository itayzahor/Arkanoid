// Itay Zahor 208127480

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Game.
 * initiates and runs the whole game with blocks paddle and balls.
 *
 * @author Itay Zahor
 */
public class GameLevel implements Animation {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int PADDLE_HEIGHT = 20;
    private static final int BORDER_THICKNESS = 25;

    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter blockCounter;
    private final Counter ballCounter;
    private final Counter score;
    private final GUI gui;
    private final KeyboardSensor keyboard;
    private final AnimationRunner runner;
    private final LevelInformation level;
    private boolean running;

    /**
     * Instantiates a new Game level.
     *
     * @param level  the level info
     * @param runner the animation runner
     * @param ks     the keyboard sensor
     * @param gui    the gui
     * @param score  the score counter
     */
    public GameLevel(LevelInformation level, AnimationRunner runner,
                     KeyboardSensor ks, GUI gui, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.score = score;
        this.gui = gui;
        this.keyboard = ks;
        this.runner = runner;
        this.level = level;
    }

    /**
     * Add collidable to the list of collidable.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite to the list of sprites.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize borders.
     * creates 4 blocks that will be the frame of the game.
     */
    public void initializeBorders() {
        // creates the score indicator block
        ScoreIndicator scoreRect = new ScoreIndicator(new Rectangle(new Point(0, 0), GUI_WIDTH,
                20), score, this.level.levelName());
        scoreRect.addToGame(this);
        // the top border
        Block borderTop = new Block(new Point(0, 20), GUI_WIDTH,
                BORDER_THICKNESS, Color.DARK_GRAY);
        borderTop.addToGame(this);
        // the left border
        Block borderLeft = new Block(new Point(0, BORDER_THICKNESS + 20),
                BORDER_THICKNESS, GUI_HEIGHT - BORDER_THICKNESS, Color.DARK_GRAY);
        borderLeft.addToGame(this);
        // the right border
        Block borderRight = new Block(new Point(GUI_WIDTH - BORDER_THICKNESS,
                BORDER_THICKNESS + 20),
                BORDER_THICKNESS, GUI_HEIGHT - BORDER_THICKNESS, Color.DARK_GRAY);
        borderRight.addToGame(this);
        // the death region
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        Block borderBottom = new Block(new Point(BORDER_THICKNESS,
                GUI_HEIGHT - 1), GUI_WIDTH - 2 * BORDER_THICKNESS,
                BORDER_THICKNESS, Color.DARK_GRAY);
        borderBottom.addToGame(this);
        borderBottom.addHitListener(ballRemover);
    }


    /**
     * Initialize the game according to the level info.
     */
    public void initialize() {
        // creates the hit listeners
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        ScoreTrackingListener score = new ScoreTrackingListener(this.score);
        Block background = (Block) this.level.getBackground();
        background.addToGame(this);
        // creates the border blocks
        initializeBorders();
        // creates all the game blocks
        for (int i = 0; i < this.level.numberOfBlocksToRemove(); i++) {
            Block block = this.level.blocks().get(i);
            block.addToGame(this);
            // add it to the hit listeners
            block.addHitListener(blockRemover);
            this.blockCounter.increase(1);
            block.addHitListener(score);
        }
        // creates the paddle
        double startXPaddle = (double) (GUI_WIDTH - this.level.paddleWidth()) / 2;
        Rectangle pad = new Rectangle(new Point(startXPaddle,
                GUI_HEIGHT - 2 * PADDLE_HEIGHT), this.level.paddleWidth(),
                PADDLE_HEIGHT);
        Paddle paddle = new Paddle(pad, gui.getKeyboardSensor(), this.level.paddleSpeed());
        paddle.addToGame(this);
        //creates the balls
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(GUI_WIDTH / 2,
                    GUI_HEIGHT - (4 * BORDER_THICKNESS), 5, Color.white);
            ball.setVelocity(this.level.initialBallVelocities().get(i));
            Rectangle rect = new Rectangle(new Point(0, 0), GUI_WIDTH, GUI_HEIGHT);
            ball.setRectangle(rect);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            this.ballCounter.increase(1);
        }
    }

    /**
     * runs the game.
     * drawing the paddle blocks and the balls and runs the game.
     */
    public void run() {
        // runs the countdown animation
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // runs the game until the player presses pause
        this.runner.run(new KeyPressStoppableAnimation(
                this.keyboard, "p", this));
        // if pause is pressed runs the pause screen until space is entered
        if (this.keyboard.isPressed("p")) {
            Animation pause = new PauseScreen(this.gui);
            this.runner.run(new KeyPressStoppableAnimation(
                    this.keyboard, KeyboardSensor.SPACE_KEY, pause));
        }
    }

    /**
     * Remove a collidable from the collidable list.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
        this.blockCounter.decrease(1);
    }

    /**
     * Remove a sprite from the sprite's collection.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // Draw all the sprites and notify them that the time passed
        sprites.drawAllOn(d);
        sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        // if there's no more balls or blocks ends the animation
        if (this.blockCounter.getValue() == 0 || this.ballCounter.getValue() == 0) {
            this.running = false;
        }
        return !this.running;
    }

    /**
     * Gets ball counter.
     *
     * @return the ball counter
     */
    public int getBallCounter() {
        return ballCounter.getValue();
    }

    /**
     * Gets block counter.
     *
     * @return the block counter
     */
    public int getBlockCounter() {
        return blockCounter.getValue();
    }
}

