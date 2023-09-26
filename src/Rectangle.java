// Itay Zahor 208127480

import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 * the rectangle has the upper left point width and height
 *
 * @author Itay Zahor
 */
public class Rectangle implements Collidable {
    private final double width;
    private final double height;
    private Point upperLeft;

    /**
     * Constructor.
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Sets the upper left point.
     *
     * @param x the x
     * @param y the y
     */
    public void setUpperLeft(double x, double y) {
        this.upperLeft = new Point(x, y);
    }

    /**
     * Intersection points list.
     * Returns a list of all the intersection points of this rectangle with a given line.
     *
     * @param line the line
     * @return the list of intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();

        // Get the four edges of the rectangle as lines
        Line top = getTop();
        Line right = getRight();
        Line bottom = getBottom();
        Line left = getLeft();

        // Check intersection between each of the rectangle's edges and the given line
        if (line.isIntersecting(top)) {
            Point intersection = line.intersectionWith(top);
            intersectionPoints.add(intersection);
        }
        if (line.isIntersecting(right)) {
            Point intersection = line.intersectionWith(right);
            intersectionPoints.add(intersection);
        }
        if (line.isIntersecting(bottom)) {
            Point intersection = line.intersectionWith(bottom);
            intersectionPoints.add(intersection);
        }
        if (line.isIntersecting(left)) {
            Point intersection = line.intersectionWith(left);
            intersectionPoints.add(intersection);
        }
        return intersectionPoints;
    }

    /**
     * Gets top border.
     *
     * @return the top border
     */
    public Line getTop() {
        return new Line(this.upperLeft,
                new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()));
    }

    /**
     * Gets left border.
     *
     * @return the left border
     */
    public Line getLeft() {
        return new Line(this.upperLeft, new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height));
    }

    /**
     * Gets bottom border.
     *
     * @return the bottom border
     */
    public Line getBottom() {
        return new Line(new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height),
                new Point(this.upperLeft.getX() + this.width,
                        this.upperLeft.getY() + this.height));
    }

    /**
     * Gets right border.
     *
     * @return the right border
     */
    public Line getRight() {
        return new Line(new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()),
                new Point(this.upperLeft.getX() + this.width,
                        this.upperLeft.getY() + this.height));
    }

    /**
     * Gets the width of the rectangle.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the upper left point.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Gets x of the upper left point.
     *
     * @return the x
     */
    public double getX() {
        return this.getUpperLeft().getX();
    }

    /**
     * Gets y of the upper left point.
     *
     * @return the y
     */
    public double getY() {
        return this.getUpperLeft().getY();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // Check if the horizontal border was hit
        if (collisionPoint.getY() == this.getUpperLeft().getY()
                || collisionPoint.getY() == this.getUpperLeft().getY()
                + this.getHeight()) {
            dy = -dy;
        }
        // check if the vertical border was hit
        if (collisionPoint.getX() == this.getUpperLeft().getX()
                || collisionPoint.getX() == this.getUpperLeft().getX()
                + this.getWidth()) {
            dx = -dx;
        }
        return new Velocity(dx, dy);
    }
}

