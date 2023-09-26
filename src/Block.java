// Itay Zahor 208127480

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 * a collidable block that can be added to the game
 *
 * @author Itay Zahor
 */
public class Block implements Collidable, Sprite, HitNotifier {
    static final double THRESHOLD = 0.00001;
    private final Rectangle rectangle;
    private final List<HitListener> hitListeners;
    private final Color color;

    /**
     * Instantiates a new Block with a rectangle type.
     *
     * @param rectangle the rectangle
     * @param color     the color or the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.hitListeners = new ArrayList<>();
        this.color = color;
    }

    /**
     * Instantiates a new Block with a point width and height.
     *
     * @param upperLeft the upper left point
     * @param width     the width of the block
     * @param height    the height of the block
     * @param color     the color of the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.hitListeners = new ArrayList<>();
        this.color = color;
    }
    /**
     * checks if two double numbers are equal with a threshold.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the boolean
     */
    public boolean equalDoubles(double num1, double num2) {
        return Math.abs(num1 - num2) <= THRESHOLD;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        // the block has been collided
         this.notifyHit(hitter);
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // Check if the hit was on the horizontal borders
        if (equalDoubles(collisionPoint.getY(),
                this.rectangle.getUpperLeft().getY())
                || equalDoubles(collisionPoint.getY(),
                this.rectangle.getUpperLeft().getY()
                        + this.rectangle.getHeight())) {
            dy = -dy;
        }
        // Check if the hit was on the vertical borders
        if (equalDoubles(collisionPoint.getX(),
                this.rectangle.getUpperLeft().getX())
                || equalDoubles(collisionPoint.getX(),
                this.rectangle.getUpperLeft().getX()
                        + this.rectangle.getWidth())) {
            dx = -dx;
        }
        return new Velocity(dx, dy);
    }

    /**
     * Adds the block to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        // creating the block with a black frame
        d.setColor(Color.black);
        d.fillRectangle((int) this.rectangle.getX(),
                (int) this.rectangle.getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        // and now paints the inside with the block color
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getX() + 1,
                (int) this.rectangle.getY() + 1,
                (int) this.rectangle.getWidth() - 2,
                (int) this.rectangle.getHeight() - 2);
    }

    @Override
    public void timePassed() {
    }

    /**
     * Remove a block from the game.
     *
     * @param g the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify to all the listeners that a hit happened.
     *
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
