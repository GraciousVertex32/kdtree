import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.LinkedList;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import static edu.princeton.cs.algs4.Point2D.X_ORDER;
import static edu.princeton.cs.algs4.Point2D.Y_ORDER;
public class KdTree
{
    private int size = 0;
    private Node root;
    private double nearest = 2; // for nearest point usage
    private Point2D nearestpoint;
    public KdTree()                                       // construct an empty set of points
    {
    }
    public boolean isEmpty()                      // is the set empty?
    {
        return size == 0;
    }
    public int size()                         // number of points in the set
    {
        return size;
    }
    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (p == null)
        {
            throw new IllegalArgumentException();
        }
        if (isEmpty())
        {
            root = new Node(p, true);
            size ++;
        }
        else
        {
            boolean inserted = false;
            Node current = root;
            while (!inserted) // loop until inserted
            {
                if (current.byX) // when sort by x
                {
                    if (p.x() < current.point.x()) // when point is less than current
                    {
                        if (current.left != null) // go to left part of the tree
                        {
                            current = current.left;
                        }
                        else
                        {
                            current.left = new Node(p, false); // create new node if it's null
                            inserted = true;
                            size++;
                        }
                    }
                    else // when is equal or more than
                    {
                        if (current.point.compareTo(p) != 0) // check for duplicates
                        {
                            if (current.right != null)
                            {
                                current = current.right;
                            }
                            else
                            {
                                current.right = new Node(p, false);
                                size++;

                                inserted = true;
                            }
                        }
                        else
                        {
                            inserted = true; // if is duplicate than stop
                        }
                    }
                }
                else // same as above just split by Y
                {
                    if (p.y() < current.point.y())
                    {
                        if (current.left != null)
                        {
                            current = current.left;
                        }
                        else
                        {
                            current.left = new Node(p, true);
                            inserted = true;
                            size++;
                        }
                    }
                    else
                    {
                        if (current.point.compareTo(p) != 0)
                        {
                            if (current.right != null)
                            {
                                current = current.right;
                            }
                            else
                            {
                                current.right = new Node(p, true);
                                size++;
                                inserted = true;
                            }
                        }
                        else
                        {
                            inserted = true;
                        }
                    }
                }
            }
        }
    }
    public boolean contains(Point2D p)            // does the set contain point p?
    {
        if (p == null)
        {
            throw new IllegalArgumentException();
        }
        if (isEmpty())
        {
            return false;
        }
        Node current = root;
        while (true) // loop until inserted
        {
            if (current.byX) // when sort by x
            {
                if (p.x() < current.point.x()) //
                {
                    if (current.left != null)
                    {
                        current = current.left;
                    }
                    else
                    {
                        return current.point.compareTo(p) == 0;
                    }
                }
                else
                {
                    if (current.point.compareTo(p) != 0)
                    {
                        if (current.right != null)
                        {
                            current = current.right;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return true;
                    }
                }
            }
            else
            {
                if (p.y() < current.point.y())
                {
                    if (current.left != null)
                    {
                        current = current.left;
                    }
                    else
                    {
                        return current.point.compareTo(p) == 0;
                    }
                }
                else
                {
                    if (current.point.compareTo(p) != 0)
                    {
                        if (current.right != null)
                        {
                            current = current.right;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return true;
                    }

                }
            }
        }
    }
    public void draw()                         // draw all points to standard draw
    {
        drawthrow(root);
    }
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
        if (rect == null)
        {
            throw new IllegalArgumentException();
        }
        List<Point2D> list = new LinkedList<Point2D>();
        Node current = root;
        if (current == null)
        {
            return list;
        }
        checkcontain(current,rect,list);
        return list;
    }
    public Point2D nearest(Point2D p)// a nearest neighbor in the set to point p; null if the set is empty
    {
        if (p == null)
        {
            throw new IllegalArgumentException();
        }
        if (root == null)
        {
            return null;
        }
        SearchNearest(root,p,0,1,0,1);
        nearest = 2;
        return nearestpoint;
    }
    private class Node
    {
        public Node left;
        public Node right;
        public Node predecessor;                 // questionable
        public Point2D point;
        public boolean byX;
        public Node(Point2D p, Node predecessor, boolean byx)
        {
            this.point = p;
            this.predecessor = predecessor;
            this.byX = byx;
        }
        public Node(Point2D p, boolean byx)
        {
            this.point = p;
            this.byX = byx;
        }
    }
    private void drawthrow(Node current)
    {
        current.point.draw();
        if (current.byX)
        {
            StdDraw.line(current.point.x(),current.point.y(),current.point.x(),0);
            StdDraw.line(current.point.x(),current.point.y(),current.point.x(),1);
        }
        else
        {
            StdDraw.line(current.point.x(),current.point.y(),0,current.point.y());
            StdDraw.line(current.point.x(),current.point.y(),1,current.point.y());
        }
        if (current.left != null)
        {
            drawthrow(current.left);
        }
        if (current.right != null)
        {
            drawthrow(current.right);
        }
    }
    private void checkcontain(Node current,RectHV rect,List list)
    {
        boolean done = false;
        double xmin = rect.xmin();
        double xmax = rect.xmax();
        double ymin = rect.ymin();
        double ymax = rect.ymax();
        if (rect.contains(current.point))
        {
            list.add(current.point);
        }
        if (current.byX)
        {
            if ((xmin < current.point.x() || xmax < current.point.x()) && current.left != null)
            {
                checkcontain(current.left,rect,list);
            }
            if ((xmin >= current.point.x() || xmax >= current.point.x()) && current.right != null)
            {
                checkcontain(current.right,rect,list);
            }
        }
        else
        {
            if ((ymin < current.point.y() || ymax < current.point.y()) && current.left != null)
            {
                checkcontain(current.left,rect,list);
            }
            if ((ymin >= current.point.y() || ymax >= current.point.y()) && current.right !=null)
            {
                checkcontain(current.right,rect,list);
            }
        }
    }
    private void SearchNearest(Node current,Point2D p,double xmin,double xmax,double ymin,double ymax)
    {
        if (p.distanceTo(current.point) < nearest)
        {
            nearest = p.distanceTo(current.point);
            nearestpoint = current.point;
        }
        if (current.byX)
        {
            if (p.x() < current.point.x() && current.left != null)
            {
                SearchNearest(current.left,p,xmin,current.point.x(),ymin,ymax);
            }
            else if (p.x() >= current.point.x() && current.right != null)
            {
                SearchNearest(current.right,p,current.point.x(),xmax,ymin,ymax);
            }
        }
        else
        {
            if (p.y() < current.point.y() && current.left != null)
            {
                SearchNearest(current.left,p,xmin,xmax,ymin,current.point.y());
            }
            else if (p.y() >= current.point.y() && current.right != null)
            {
                SearchNearest(current.right,p,xmin,xmax,current.point.y(),ymax);
            }

        }
        if (current.byX)
        {
            if (p.x() < current.point.x() && current.right != null)
            {
                if (nearest >= distance(p,current.point.x(),xmax,ymin,ymax))
                {
                    SearchNearest(current.right,p,current.point.x(),xmax,ymin,ymax);
                }
            }
            else if (p.x() >= current.point.x() && current.left != null)
            {
                if (nearest >= distance(p,xmin,current.point.x(),ymin,ymax))
                {
                    SearchNearest(current.left,p,xmin,current.point.x(),ymin,ymax);
                }
            }
        }
        else
        {
            if (p.y() < current.point.y() && current.right != null)
            {
                if (nearest >= distance(p,xmin,xmax,current.point.y(),ymax))
                {
                    SearchNearest(current.right,p,xmin,xmax,current.point.y(),ymax);
                }
            }
            else if (p.y() >= current.point.y() && current.left != null)
            {
                if (nearest >= distance(p,xmin,xmax,ymin,current.point.y()))
                {
                    SearchNearest(current.left,p,xmin,xmax,ymin,current.point.y());
                }
            }
        }
    }
    private double distance(Point2D p,double xmin,double xmax,double ymin,double ymax)
    {
        if ((p.x() >= xmin && p.x() <= xmax) && (p.y() >= ymin && p.y() <= ymax))
        {
            return 0;
        }
        if (p.x() <= xmax && p.x() >= xmin)
        {
            if (Math.abs(p.y() - ymax) < Math.abs(p.y() - ymin))
            {
                return Math.abs(p.y() - ymax);
            }
            else
            {
                return Math.abs(p.y() - ymin);
            }
        }
        else if (p.y() <= ymax && p.y() >= ymin)
        {
            if (Math.abs(p.x() - xmax) < Math.abs(p.x() - xmin))
            {
                return Math.abs(p.x() - xmax);
            }
            else
            {
                return Math.abs(p.x() - xmin);
            }
        }
        else if (p.x() < xmin)
        {
            if (p.y() < ymin)
            {
                double dx = p.x() - xmin;
                double dy = p.y() - ymin;
                return Math.sqrt(dx*dx + dy*dy);
            }
            else if (p.y() > ymax)
            {
                double dx = p.x() - xmin;
                double dy = p.y() - ymax;
                return Math.sqrt(dx*dx + dy*dy);
            }
        }
        else if (p.x() > xmax)
        {
            if (p.y() < ymin)
            {
                double dx = p.x() - xmax;
                double dy = p.y() - ymin;
                return Math.sqrt(dx*dx + dy*dy);
            }
            else if(p.y() > ymax)
            {
                double dx = p.x() - xmax;
                double dy = p.y() - ymax;
                return Math.sqrt(dx*dx + dy*dy);
            }
        }
        throw new IllegalArgumentException(xmin +" "+xmax +" "+ymin +" "+ymax+"   "+p.x()+" "+p.y());
    }
}
