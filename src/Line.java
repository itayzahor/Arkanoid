// Itay Zahor 208127480

import java.util.List;

/**
 * The type Line.
 * connects between two points start and end of the line.
 *
 * @author Itay Zahor
 */
public class Line {
    static final double THRESHOLD = 0.00001;
    private final Point start;
    private final Point end;

    /**
     * Constructor.
     * Instantiates a new Line with 2 points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor.
     * Instantiates a new Line with (x1,y1) , (x2,y2).
     *
     * @param x1 the x of the first point
     * @param y1 the y of the first point
     * @param x2 the x of the second point
     * @param y2 the y of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Length double.
     * calculates the distance between the start and the end point.
     *
     * @return the distance
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Middle point.
     * Returns the middle point of the line between the start and the end
     *
     * @return the middle point
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2.0;
        double midY = (start.getY() + end.getY()) / 2.0;
        return new Point(midX, midY);
    }

    /**
     * Start point.
     * Returns the start point of the line
     *
     * @return the point
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     * Returns the end point of the line
     *
     * @return the point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Equals boolean.
     * if the two lines are equal return true otherwise false.
     *
     * @param other the other point
     * @return true /false
     */
    public boolean equals(Line other) {
        // Check if the two lines have the same start and end points
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * number in between range.
     * return true if a number is between a start and an end point
     *
     * @param number the number
     * @param start  the start
     * @param end    the end
     * @return true /false
     */
    public boolean inBetween(double number, double start, double end) {
        return number + THRESHOLD >= Math.min(start, end)
                && number - THRESHOLD <= Math.max(start, end);
    }

    /**
     * Is intersecting boolean.
     * Returns true if the lines intersect, false otherwise
     *
     * @param other the other line
     * @return true /false
     */
    public Boolean isIntersecting(Line other) {
        Point start1 = this.start;
        Point end1 = this.end;
        Point start2 = other.start;
        Point end2 = other.end;
        // if the lines are equal return true
        if (this.equals(other)) {
            return true;
        }
        // when both lines are vertical
        if (start1.getX() == end1.getX() && start2.getX() == end2.getX()) {
            if (start1.getX() == start2.getX()) {
                return inBetween(start1.getY(), start2.getY(), end2.getY())
                        || inBetween(end1.getY(), start2.getY(), end2.getY());
            }
            return false;
        }
        // when the first line is vertical
        if (start1.getX() == end1.getX()) {
            // if the other line is horizontal
            if (start2.getY() == end2.getY()) {
                double x1 = start1.getX();
                double y2 = start2.getY();
                return inBetween(x1, start2.getX(), end2.getX())
                        && inBetween(y2, start1.getY(), end1.getY());
            }
            // if the other line has a normal slope
            double m2 = (end2.getY() - start2.getY()) / (end2.getX() - start2.getX());
            double b2 = start2.getY() - (m2 * start2.getX());
            double y2 = (m2 * start1.getX()) + b2;
            return inBetween(y2, start2.getY(), end2.getY())
                    && inBetween(y2, start1.getY(), end1.getY());
        }
        // if the other line is vertical
        if (start2.getX() == end2.getX()) {
            // if the first line is horizontal
            if (start1.getY() == end1.getY()) {
                double x2 = start2.getX();
                double y1 = start1.getY();
                return inBetween(x2, start1.getX(), end1.getX())
                        && inBetween(y1, start2.getY(), end2.getY());
            }
            // if the first line has a normal slope
            double m1 = (end1.getY() - start1.getY()) / (end1.getX() - start1.getX());
            double b1 = start1.getY() - (m1 * start1.getX());
            double y1 = (m1 * start2.getX()) + b1;
            return inBetween(y1, start1.getY(), end1.getY())
                    && inBetween(y1, start2.getY(), end2.getY());
        }

        // Calculate slopes and intercepts
        double m1 = (end1.getY() - start1.getY()) / (end1.getX() - start1.getX());
        double m2 = (end2.getY() - start2.getY()) / (end2.getX() - start2.getX());
        double b1 = start1.getY() - (m1 * start1.getX());
        double b2 = start2.getY() - (m2 * start2.getX());

        // Check for parallel lines
        if (m1 == m2 && b1 != b2) {
            return false;
        }

        // if both lines are horizontal
        if (m1 == 0 && m2 == 0) {
            return !(Math.max(start1.getX(), end1.getX()) < Math.min(start2.getX(),
                    end2.getX()));
        }
        // if the first line is horizontal
        if (m1 == 0) {
            double solveX = (start1.getY() - b2) / m2;
            return inBetween(solveX, start2.getX(), end2.getX())
                    && inBetween(solveX, start1.getX(), end1.getX());
        }
        // if the other line is horizontal
        if (m2 == 0) {
            double solveX = (start2.getY() - b1) / m1;
            return inBetween(solveX, start1.getX(), end1.getX())
                    && inBetween(solveX, start2.getX(), end2.getX());
        }

        // Calculate intersection x point
        double solveX = (b2 - b1) / (m1 - m2);

        // Check if intersection point is within line segments
        return !(solveX < Math.min(start1.getX(), end1.getX()))
                && !(solveX > Math.max(start1.getX(), end1.getX()))
                && !(solveX < Math.min(start2.getX(), end2.getX()))
                && !(solveX > Math.max(start2.getX(), end2.getX()));
    }

    /**
     * Inclusion case.
     * in case both lines have the same slope checks if there's an
     * intersection return the intersection point if there's and inclusion or
     * no connection at all returns null. and if lines are vertical it should
     * compare the y otherwise compare the x.
     *
     * @param start1  the start of the first line
     * @param end1    the end of the first line
     * @param start2  the start of the second line
     * @param end2    the end of the second line
     * @param compare should we compare the x or y
     * @return the point or null
     */
    public Point inclusionCase(Point start1, Point end1, Point start2,
                               Point end2, char compare) {
        // Determine which coordinates to compare
        double max1, max2, min1, min2;
        if (compare == 'x') {
            max1 = Math.max(start1.getX(), end1.getX());
            max2 = Math.max(start2.getX(), end2.getX());
            min1 = Math.min(start1.getX(), end1.getX());
            min2 = Math.min(start2.getX(), end2.getX());
        } else {
            max1 = Math.max(start1.getY(), end1.getY());
            max2 = Math.max(start2.getY(), end2.getY());
            min1 = Math.min(start1.getY(), end1.getY());
            min2 = Math.min(start2.getY(), end2.getY());
        }
        // Check if one line is containing the other
        if (max1 >= max2 && min1 <= min2) {
            // supposed to be null, but it makes more sense with the ball
            // movement
            return start2;
        }
        if (max1 <= max2 && min1 >= min2) {
            return null;
        }
        // Check for endpoint intersection
        if (start1.equals(start2) || start1.equals(end2)) {
            return start1;
        }
        if (end1.equals(start2) || end1.equals(end2)) {
            return end1;
        }
        // Partial inclusion or no connection at all
        return null;
    }

    /**
     * Intersecting with point.
     * If the lines intersect returns the point and if there's an inclusion
     * or the lines don't intersect at all returns null.
     *
     * @param other the other line
     * @return the intersection point or null
     */
    public Point intersectionWith(Line other) {
        Point start1 = this.start;
        Point end1 = this.end;
        Point start2 = other.start;
        Point end2 = other.end;
        // if the lines are equal return true
        if (this.equals(other)) {
            return null;
        }
        // in case both lines are vertical check for the inclusion case by
        // comparing the y
        if (start1.getX() == end1.getX() && start2.getX() == end2.getX()) {
            return inclusionCase(start1, end1, start2, end2, 'y');
        }
        // when the first line is vertical
        if (start1.getX() == end1.getX()) {
            // if the other line is horizontal
            if (start2.getY() == end2.getY()) {
                double x1 = start1.getX();
                double y2 = start2.getY();
                if (inBetween(x1, start2.getX(), end2.getX())
                        && inBetween(y2, start1.getY(), end1.getY())) {
                    return new Point(x1, y2);
                }
                return null;
            }
            // if the other line has a normal slope
            double m2 = (end2.getY() - start2.getY()) / (end2.getX() - start2.getX());
            double b2 = start2.getY() - (m2 * start2.getX());
            double y2 = (m2 * start1.getX()) + b2;
            if (inBetween(y2, start2.getY(), end2.getY())
                    && inBetween(y2, start1.getY(), end1.getY())) {
                return new Point(start1.getX(), y2);
            }
            return null;
        }
        // when the other line is vertical
        if (start2.getX() == end2.getX()) {
            // if the first line is horizontal
            if (start1.getY() == end1.getY()) {
                double x2 = start2.getX();
                double y1 = start1.getY();
                if (inBetween(x2, start1.getX(), end1.getX())
                        && inBetween(y1, start2.getY(), end2.getY())) {
                    return new Point(x2, y1);
                }
                return null;
            }
            // if the first line has a normal slope
            double m1 = (end1.getY() - start1.getY()) / (end1.getX() - start1.getX());
            double b1 = start1.getY() - (m1 * start1.getX());
            double y1 = (m1 * start2.getX()) + b1;
            if (inBetween(y1, start1.getY(), end1.getY())
                    && inBetween(y1, start2.getY(), end2.getY())) {
                return new Point(start2.getX(), y1);
            }
            return null;
        }

        // Calculate slopes and intercepts
        double m1 = (end1.getY() - start1.getY()) / (end1.getX() - start1.getX());
        double m2 = (end2.getY() - start2.getY()) / (end2.getX() - start2.getX());
        double b1 = start1.getY() - (m1 * start1.getX());
        double b2 = start2.getY() - (m2 * start2.getX());

        // Check for parallel lines
        if (m1 == m2 && b1 != b2) {
            return null;
        }
        // Check for the inclusion case, comparing the x also checks if the
        // two lines are horizontal
        if (m1 == m2 && b1 == b2) {
            return inclusionCase(start1, end1, start2, end2, 'x');
        }
        // if the first line is horizontal
        if (m1 == 0) {
            double solveX = (start1.getY() - b2) / m2;
            if (inBetween(solveX, start2.getX(), end2.getX())
            && inBetween(solveX, start1.getX(), end1.getX())) {
                return new Point(solveX, start1.getY());
            }
        }
        // if the other line is horizontal
        if (m2 == 0) {
            double solveX = (start2.getY() - b1) / m1;
            if (inBetween(solveX, start1.getX(), end1.getX())
            && inBetween(solveX, start2.getX(), end2.getX())) {
                return new Point(solveX, start2.getY());
            }
        }

        // Calculate intersection x point
        double solveX = (b2 - b1) / (m1 - m2);

        // Check if intersection point is within line segments
        if (solveX < Math.min(start1.getX(), end1.getX())
                || solveX > Math.max(start1.getX(), end1.getX())
                || solveX < Math.min(start2.getX(), end2.getX())
                || solveX > Math.max(start2.getX(), end2.getX())) {
            return null;
        }
        // Calculate intersection y point
        double solveY = m1 * solveX + b1;
        return new Point(solveX, solveY);
    }

    /**
     * Closest intersection to start of line point from a rectangle.
     * calculates the intersections between a line and a rectangle and
     * returns the closest intersection to the start of the line.
     *
     * @param rect the rectangle
     * @return the intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);

        if (intersectionPoints.isEmpty()) {
            // If this line does not intersect with the rectangle, return null.
            return null;
        } else {
            // Otherwise, find the intersection point that is closest to the start of the line.
            Point closestIntersectionPoint = intersectionPoints.get(0);
            if (closestIntersectionPoint == null) {
                for (int i = 1; i < intersectionPoints.size() || closestIntersectionPoint != null; i++) {
                    closestIntersectionPoint = intersectionPoints.get(i);
                }
            }
            if (closestIntersectionPoint == null) {
                return null;
            }
            double closestDistance = this.start.distance(closestIntersectionPoint);
            // runs throw the intersection point and gets the closest one
            for (int i = 1; i < intersectionPoints.size(); i++) {
                Point intersectionPoint = intersectionPoints.get(i);
                if (intersectionPoint == null) {
                    continue;
                }
                double distance = this.start.distance(intersectionPoint);
                if (distance < closestDistance) {
                    closestIntersectionPoint = intersectionPoint;
                    closestDistance = distance;
                }
            }
            return closestIntersectionPoint;
        }
    }
}

