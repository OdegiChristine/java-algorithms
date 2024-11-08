/* The class below examines 4 points at a time and checks whether they all lie on the same line segment,
 * returning all such line segments.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        // Throw exception if points is null 
        if (points == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        // Check for null points
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("Point cannot be null");
            }
        }

        // Check for duplicates
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        for (int i = 0; i < sortedPoints.length; i++) {
            if (i > 0 && sortedPoints[i].compareTo(sortedPoints[i - 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points are not allowed");
            }
        }

        int n = sortedPoints.length;

        // Check all combinations of 4 points for collinearity
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p = sortedPoints[i];
                        Point q = sortedPoints[j];
                        Point r = sortedPoints[k];
                        Point s = sortedPoints[l];

                        // Check if p, q, r and s sre collinear
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
                            segments.add(new LineSegment(p, s));
                        }
                    }
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
