// An immutable data type for line segments in the plane

public class LineSegment {
    private final Point p;  // one endpoint of this line segment
    private final Point q;  // the other endpoint of this line segment

    // Initializes a new line segment
    public LineSegment(Point p, Point q) {
        if (p == null || q == null) {
            throw new IllegalArgumentException("argument to LineSegment constructor is null");
        }
        if (p.equals(q)) {
            throw new IllegalArgumentException("both arguments to LineSegment constructor are the same point: " + p);
        }
        this.p = p;
        this.q = q;
    }

    // Draws this line segment to standard draw
    public void draw() {
        p.drawTo(q);
    }

    // Returns a string representation of this line segment
    public String toString() {
        return p + " -> " + q;
    }

    // Throws exception if called
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported");
    }
}
