// Itay Zahor 208127480

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Ball, has a center point radius and color.
 * can add to it velocity and borders of movement
 *
 * @author Itay Zahor
 */
public class Ball implements Sprite {
    static final double THRESHOLD = 0.00001;
    private final int radius;
    private final Color color;
    private Point center;
    private Velocity velocity;
    private Rectangle rectangle;
    private GameEnvironment gameEnvironment;
    private Collidable collidable;


    /**
     * Constructor.
     * Instantiates a new Ball with a point.
     *
     * @param center the center
     * @param r      the radius
     * @param color  the color
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * Constructor.
     * Instantiates a new Ball with x and y.
     *
     * @param x     the x of the point
     * @param y     the y of the point
     * @param r     the radius
     * @param color the color
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * Sets the game environment.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Gets x.
     * returns the x of the center
     *
     * @return the x of the center
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Gets y.
     * returns the y of the center
     *
     * @return the y of the center
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Gets size.
     * returns the size of the radius
     *
     * @return the size of the radius
     */
    public int getSize() {
        return radius;
    }

    /**
     * Gets color.
     * returns the color of the ball
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // draw the ball with a black frame around it
        d.setColor(Color.black);
        d.fillCircle(getX(), getY(), radius);
        d.setColor(getColor());
        d.fillCircle(getX(), getY(), radius - 1);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Constructor.
     * Sets the velocity of the ball with dx and dy which control the velocity.
     *
     * @param dx the dx the horizontal movement
     * @param dy the dy the vertical movement
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     * returns the velocity of the ball
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Constructor.
     * Sets the velocity of the ball with the type velocity.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * Move one step.
     * move a step according to the velocity of the ball. if the ball reaches
     * the vertical borders or a block change the velocity dy -> -dy and if the
     * ball reaches the horizontal borders change the dx -> -dx.
     */
    public void moveOneStep() {
        Point end = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, end);
        // finds the closest collision point
        CollisionInfo col = gameEnvironment.getClosestCollision(trajectory);
        if (col != null) {
            this.velocity = col.collisionObject().hit(col.collisionPoint(), this.velocity, this);
            // checks if the ball hits the second border in first movement
            end = this.getVelocity().applyToPoint(this.center);
            trajectory = new Line(this.center, end);
            // finds the closest collision point
            col = gameEnvironment.getClosestCollision(trajectory);
            if (col != null) {
                this.velocity = col.collisionObject().hit(col.collisionPoint(), this.velocity, this);
            }
        }
        // applying the movement to the ball
        this.center = this.getVelocity().applyToPoint(this.center);
    }


    /**
     * Constructor.
     * Sets a rectangle borders to the ball movement. the x and y are the
     * point the rectangle starts and the width and height are going
     * downwards from the point.
     *
     * @param x      the x of the point
     * @param y      the y of the point
     * @param width  the width
     * @param height the height
     */
    public void setRectangle(int x, int y, int width, int height) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
    }


    /**
     * Constructor.
     * Sets a rectangle borders to the ball movement.
     *
     * @param r the rectangle
     */
    public void setRectangle(Rectangle r) {
        this.rectangle = r;
    }

    /**
     * adds the ball to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove the ball from the game.
     *
     * @param g the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
