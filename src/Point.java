// Itay Zahor 208127480

/**
 * Point defined by (x,y) place in space.
 *
 * @author Itay Zahor
 */
public class Point {
    /**
     * The Threshold.
     */
    static final double THRESHOLD = 0.00001;
    private final double x;
    private final double y;

    /**
     * Constructor.
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     * distance return the distance from a point to the other point.
     *
     * @param other the other point
     * @return the double
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Equals boolean.
     * equals return true is the points are equal, false otherwise
     *
     * @param other the other point
     * @return true /false
     */
    public boolean equals(Point other) {
        return Math.abs(this.x - other.x) <= THRESHOLD
                && Math.abs(this.y - other.y) <= THRESHOLD;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }
}
