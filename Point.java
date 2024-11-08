/* The class below constructs and draws points on the (x,y) axis,
and determines their slopes */ 

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;    // x-coordinate of this point
    private final int y;    // y-coordinate of this point

    // Constructs/Initializes the point, (x,y)
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    // Draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // Draws the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // Return a string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // Compare two points by y-coordinates, breaking ties by x-coordinates
    @Override
    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y ) {
            return 1; // this point is greater than that point
        } else {
            // y coordinates are equal, so compare by x coordinate
            return Integer.compare(this.x, that.x);
        }
    }

    // The slope between this point and that point
    public double slopeTo(Point that) {
        // Identical points: return Double.NEGATIVE_INFINITY
        if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        }
        // Vertical line: return Double.POSTIVE_INFINITY
        else if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        // Horizontal line: return +0.0
        else if (this.y == that.y) {
            return +0.0;
        }
        // Regular slope calculation
        else {
            return (double) (that.y - this.y) / (that.x - this.x);
        } 
    }

    // Compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                double slope1 = slopeTo(p1);
                double slope2 = slopeTo(p2);

                // Use Double.comapre to handle special cases of slope comparison
                return Double.compare(slope1, slope2);
            }
        };
    }

    // Unit tests on the point data type
    public static void main(String[] args) {

    }
}
