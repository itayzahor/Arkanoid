// Itay Zahor 208127480

/**
 * The interface Collidable.
 * contains all the things that the ball can bump into.
 *
 * @author Itay Zahor
 */
public interface Collidable {
    /**
     * gets the "collision shape" of the object.
     *
     * @return the shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit.
     * the ball hit the shape returns the new velocity after the bump.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter          the ball that hit the block
     * @return the new velocity
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}
