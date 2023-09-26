// Itay Zahor 208127480

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * The type Paddle.
 * the paddle of the game.
 *
 * @author Itay Zahor
 */
public class Paddle implements Sprite, Collidable {
    static final double THRESHOLD = 0.00001;
    private static final int GUI_WIDTH = 800;
    private static final int BORDER_THICKNESS = 25;
    private final Rectangle paddle;
    private final KeyboardSensor keyboard;
    private final int paddleSpeed;

    /**
     * Instantiates a new Paddle.
     *
     * @param paddle      the paddle
     * @param keyboard    the keyboard
     * @param paddleSpeed the paddle speed
     */
    public Paddle(Rectangle paddle, KeyboardSensor keyboard, int paddleSpeed) {
        this.paddle = paddle;
        this.keyboard = keyboard;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * Move left.
     * moves the paddle to the left until the border blocks
     */
    public void moveLeft() {
        int newX = (int) this.paddle.getX() - this.paddleSpeed;
        if (newX < BORDER_THICKNESS) {
            newX = BORDER_THICKNESS;
        }
        this.paddle.setUpperLeft(newX, this.paddle.getY());
    }

    /**
     * Move right.
     * moves the paddle to the right until the border blocks
     */
    public void moveRight() {
        int newX = (int) this.paddle.getX() + this.paddleSpeed;
        if (newX + this.paddle.getWidth() + BORDER_THICKNESS > GUI_WIDTH) {
            newX = GUI_WIDTH - (int) this.paddle.getWidth() - BORDER_THICKNESS;
        }
        this.paddle.setUpperLeft(newX, this.paddle.getY());
    }

    @Override
    public void timePassed() {
        // moves the paddle left
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        // moves the paddle right
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        // paints the frame with a black color
        d.setColor(Color.black);
        d.fillRectangle((int) this.paddle.getX(), (int) this.paddle.getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        // and the paddle with and orange color
        d.setColor(Color.ORANGE);
        d.fillRectangle((int) this.paddle.getX() + 1, (int) this.paddle.getY() + 1,
                (int) this.paddle.getWidth() - 2,
                (int) this.paddle.getHeight() - 2);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        double paddleWidth = this.paddle.getWidth();
        double regionWidth = paddleWidth / 5;
        // divides the paddle to 5 regions each of the regions sends the ball
        // in a different angle.
        double ballAngle;
        // leftest region
        if (collisionPoint.getX() >= this.paddle.getX()
                && collisionPoint.getX() < this.paddle.getX() + regionWidth) {
            ballAngle = 300;
        } else if (collisionPoint.getX() >= this.paddle.getX() + regionWidth
                && collisionPoint.getX() < this.paddle.getX() + (2 * regionWidth)) {
            // second region
            ballAngle = 330;
        } else if (collisionPoint.getX() >= this.paddle.getX() + (2 * regionWidth)
                && collisionPoint.getX() < this.paddle.getX() + (3 * regionWidth)) {
            ballAngle = 360;
        } else if (collisionPoint.getX() >= this.paddle.getX() + (3 * regionWidth)
                && collisionPoint.getX() < this.paddle.getX() + (4 * regionWidth)) {
            // fourth region
            ballAngle = 30;
        } else {
            // rightest region
            ballAngle = 60;
        }
        // calculates the speed of the ball
        double angle;
        // if the ball is moving horizontally
        if (Math.abs(currentVelocity.getDx()) <= THRESHOLD) {
            return Velocity.fromAngleAndSpeed(ballAngle, currentVelocity.getDy());
        }
        // if the ball is moving vertically
        if (Math.abs(currentVelocity.getDy()) <= THRESHOLD) {
            return Velocity.fromAngleAndSpeed(ballAngle, currentVelocity.getDx());
        }
        // calculates the speed and continues it
        angle =
                Math.atan(Math.abs(currentVelocity.getDx() / currentVelocity.getDy()));
        double speed = Math.abs(currentVelocity.getDx() / Math.sin(angle));
        return Velocity.fromAngleAndSpeed(ballAngle, speed);
    }

    /**
     * Adds this paddle to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
