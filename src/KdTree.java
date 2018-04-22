import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.LinkedList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import static edu.princeton.cs.algs4.Point2D.X_ORDER;
import static edu.princeton.cs.algs4.Point2D.Y_ORDER;
public class KdTree
{
    int size;
    boolean isempty;
    Node root;
    public KdTree()                                       // construct an empty set of points
    {

    }
    public boolean isEmpty()                      // is the set empty?
    {
        return isempty;
    }
    public int size()                         // number of points in the set
    {
        return size;
    }
    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (isEmpty())
        {
            root = new Node(p,false);
        }
        else
        {
            
        }
    }
    public boolean contains(Point2D p)            // does the set contain point p?
    {

    }
    public void draw()                         // draw all points to standard draw
    {

    }
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {

    }
    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {

    }
    private class Node
    {
        public Node left;
        public Node right;
        public Point2D point;
        public boolean isred;
        public Node(Point2D p,boolean isred)
        {
            this.point = p;
            this.isred = isred;
        }
    }
}
