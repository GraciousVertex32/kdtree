import java.util.List;
import java.util.TreeSet;
import java.util.LinkedList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
public class PointSET
{
    private TreeSet<Point2D> set;
    public PointSET()                                       // construct an empty set of points
    {
        set = new TreeSet<Point2D>();
    }
    public boolean isEmpty()                      // is the set empty?
    {
        return set.isEmpty();
    }
    public int size()                         // number of points in the set
    {
        return set.size();
    }
    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        set.add(p);
    }
    public boolean contains(Point2D p)            // does the set contain point p?
    {
        return set.contains(p);
    }
    public void draw()                         // draw all points to standard draw
    {

    }
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
        List<Point2D> list = new LinkedList<>();
        for (Point2D points : set)
        {
            if (rect.contains(points))
            {
                list.add(points);
            }
        }
        return list;
    }
    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {
        double min = 10;
        Point2D temp = null;
        for (Point2D points : set)
        {
            double dst = p.distanceSquaredTo(points);
            if (dst < min)
            {
                min = dst;
                temp = points;
            }
        }
        return temp;
    }
}
