// Itay Zahor 208127480

/**
 * The type Collision info.
 * contains a collision info between the ball and a collidable.
 *
 * @author Itay Zahor
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * returns the collision point.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * returns the shape of the object that the collision was with.
     *
     * @return the collidable
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}

