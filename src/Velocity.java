// Itay Zahor 208127480

/**
 * Velocity specifies the change in position on the `x` and the `y` of the
 * ball movement.
 *
 * @author Itay Zahor
 */

public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Constructor.
     * Instantiates a new Velocity.
     *
     * @param dx the horizontal movement
     * @param dy the vertical movement
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructor.
     * sets the velocity with angle of degrees of the ball and the speed.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radianAngle = Math.toRadians(angle);
        double dx = (Math.sin(radianAngle)) * speed;
        double dy = -(Math.cos(radianAngle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Apply a movement to point.
     * Take a point with position (x,y) and return a new point with position
     * (x+dx, y+dy)
     *
     * @param p the point
     * @return the point after the movement
     */

    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}