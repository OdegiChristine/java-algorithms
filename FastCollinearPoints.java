import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* The class below solves the collinear points problem much faster than the brute force problem */

public class FastCollinearPoints {
    private final List<LineSegment> segments = new ArrayList<>();
    
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("The argument cannot be null");
        }

        // Check for null points before performing any operations
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("Point cannot be null");
            }
        }
        
        // Sort the points and check for duplicates
        Point[] sorted = points.clone();
        Arrays.sort(sorted);

        for (int i = 0; i < sorted.length; i++) {
            if (i > 0 && sorted[i].compareTo(sorted[i - 1]) == 0) {
                throw new IllegalArgumentException("Points cannot be duplicate");
            } 
        }

        int n = sorted.length;
        for (int i = 0; i < n; i++) {
            Point p = sorted[i];
            // Sort the remaining points by slope relative to p
            Point[] pointsBySlope = sorted.clone();
            Arrays.sort(pointsBySlope, p.slopeOrder());
            
            // Find collinear points
            int j = 1;
            while (j < n) {
                List<Point> collinear = new ArrayList<>();
                final double slope = p.slopeTo(pointsBySlope[j]);

                // Collect all points with the same slope
                do {
                    collinear.add(pointsBySlope[j++]);
                } while (j < n && Double.compare(slope, p.slopeTo(pointsBySlope[j])) == 0);

                // Check if there are 3 or more points with the same slope
                if (collinear.size() >=3 && p.compareTo(collinear.get(0)) < 0) {
                    // Add segment from p to the last point in collinear points
                    segments.add(new LineSegment(p, collinear.get(collinear.size() - 1)));
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}
