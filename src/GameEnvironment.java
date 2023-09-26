// Itay Zahor 208127480

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game environment.
 * contains all the collidables in the game
 *
 * @author Itay Zahor
 */
public class GameEnvironment {

    private final List<Collidable> collidables;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Add collidable to the list.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Gets the list of collidables.
     *
     * @return the collidables list
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * finds the closest collision from the start of line.
     * goes throw the collidables and finds the closest intersection between
     * the line and the object.
     *
     * @param trajectory the line
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Initialize the closest collision information to null.
        CollisionInfo closestCollision = null;
        // Loop through all the collidable objects and check for collisions.
        for (Collidable c : collidables) {
            Rectangle rect = c.getCollisionRectangle();
            Point intersectionPoint = trajectory.closestIntersectionToStartOfLine(rect);
            // If there is an intersection, and this is the closest one so far,
            // update the collision information.
            if (intersectionPoint != null) {
                double distance = trajectory.start().distance(intersectionPoint);
                if (closestCollision == null
                        || distance < closestCollision.collisionPoint().distance(trajectory.start())) {
                    closestCollision = new CollisionInfo(intersectionPoint, c);
                }
            }
        }
        // Return the closest collision information, if there is one.
        return closestCollision;
    }
}
