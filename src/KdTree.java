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
            root = new Node(p, true);
        }
        else
        {
            boolean inserted = false;
            Node current = root;
            while (!inserted) // loop until inserted
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
                            current.left = new Node(p, false);
                            inserted = true;
                            size++;
                        }
                    }
                    else
                    {
                        if (current.right != null)
                        {
                            current = current.right;
                        }
                        else
                        {
                            if (current.point.compareTo(p) != 0)
                            {
                                current.right = new Node(p, false);
                                size++;
                            }
                            inserted = true;
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
                            current.left = new Node(p, true);
                            inserted = true;
                            size++;
                        }
                    }
                    else
                    {
                        if (current.right != null)
                        {
                            current = current.right;
                        }
                        else
                        {
                            if (current.point.compareTo(p) != 0)
                            {
                                current.right = new Node(p, true);
                                size++;
                            }
                            inserted = true;
                        }
                    }
                }
            }
        }
    }
    public boolean contains(Point2D p)            // does the set contain point p?
    {
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
                    if (current.right != null)
                    {
                        current = current.right;
                    }
                    else
                    {
                        return current.point.compareTo(p) == 0;
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
                    if (current.right != null)
                    {
                        current = current.right;
                    }
                    else
                    {
                        return current.point.compareTo(p) == 0;
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
        
    }
    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {
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
        if (current.left != null)
        {
            drawthrow(current.left);
        }
        if (current.right != null)
        {
            drawthrow(current.right);
        }
    }
}
